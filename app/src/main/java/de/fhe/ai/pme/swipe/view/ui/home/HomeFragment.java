package de.fhe.ai.pme.swipe.view.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.fhe.ai.pme.swipe.R;
import de.fhe.ai.pme.swipe.model.Card;
import de.fhe.ai.pme.swipe.model.Folder;
import de.fhe.ai.pme.swipe.storage.KeyValueStore;
import de.fhe.ai.pme.swipe.view.ui.core.BaseFragment;
import de.fhe.ai.pme.swipe.view.ui.core.RecyclerViewClickListener;

public class HomeFragment extends BaseFragment {

    private HomeViewModel homeViewModel;
    private KeyValueStore keyValueStore;
    private HomeAdapter adapter;
    private Spinner filterDropdown;
    private ArrayAdapter<CharSequence> arrayAdapterFolder;
    private ArrayAdapter<CharSequence> arrayAdapterCard;
    private static long currentFolderID = 0;
    private static boolean currentFolderContainsCards = false;
    private static boolean currentFolderContainsFolders = false;

    //TODO: Bug - Sometimes shuffles Folders randomly when swapping them
    // Only after trying to Drag and Drop with other filter then manual order
    RecyclerViewClickListener itemListener = new RecyclerViewClickListener() {
        @Override
        public void itemClick(View v, int position) {
            if(!currentFolderContainsCards) {
                Folder clickedFolder = homeViewModel.getSingleFolderByManualOrder(currentFolderID, position);

                // Set Information about current Folder for Fragment and KeyValueStore
                currentFolderID = clickedFolder.getFolderID();
                keyValueStore.editValueLong("currentFolderID", currentFolderID);
                currentFolderContainsCards = clickedFolder.getContainsCards();
                keyValueStore.editValueBool("currentFolderContainsCards", currentFolderContainsCards);
                currentFolderContainsFolders = clickedFolder.getContainsFolders();
                keyValueStore.editValueBool("currentFolderContainsFolders", currentFolderContainsFolders);

                //TODO: Show cards
//                if(!currentFolderContainsCards) {
                    // Set Folder Spinner
                    filterDropdown.setAdapter(arrayAdapterFolder);

                    homeViewModel.getFolders(currentFolderID, filterDropdown.getSelectedItemPosition()).observe(requireActivity(), adapter::setFolders);
//                }
//                else {
//                    // Set Card Spinner
//                    filterDropdown.setAdapter(arrayAdapterCard);
//
//                    homeViewModel.getCards(currentFolderID, filterDropdown.getSelectedItemPosition()).observe(requireActivity(), adapter::setCards);
//                }
            }
            else {
                //TODO: ViewPager for Cards
            }
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Create Layout/Views
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Get View Model
        homeViewModel = this.getViewModel(HomeViewModel.class);

        // Get KeyValueStore
        keyValueStore = new KeyValueStore(getActivity().getApplication());

        // Get RecyclerView Reference
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view_home);
        // Divider Item Decoration to differ between folders
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(root.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        // Get FragmentActivity Reference
        FragmentActivity fragmentActivity = this.requireActivity();

        // Create Adapter
        adapter = new HomeAdapter(fragmentActivity, itemListener);

        // Configure RecyclerView with Adapter and LayoutManager
        recyclerView.setAdapter( adapter );
        recyclerView.setLayoutManager( new LinearLayoutManager(this.requireActivity() ) );
        //recyclerView.setLayoutManager( new GridLayoutManager(this.requireActivity(), 2) );

        // Create Spinner Dropdown for Filters
        filterDropdown = root.findViewById(R.id.filters_folder);

        // Array Adapter for Dropdown displayed on Folder View
        arrayAdapterFolder = ArrayAdapter.createFromResource(getContext(),
                R.array.array_folder_dropdown, android.R.layout.simple_spinner_item);
        arrayAdapterFolder.setDropDownViewResource(R.layout.item_dropdown);
        // Array Adapter for Dropdown displayed on Card View
        arrayAdapterCard = ArrayAdapter.createFromResource(getContext(),
                R.array.array_card_dropdown, android.R.layout.simple_spinner_item);
        arrayAdapterCard.setDropDownViewResource(R.layout.item_dropdown);
        // Set initial Dropdown on Folder View
        filterDropdown.setAdapter(arrayAdapterFolder);

        // Create Listener
        filterDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Observe the LiveData Contact List in our View Model - if something changes, we
                // update the list in our Adapter
                if(!currentFolderContainsCards) {
                    homeViewModel.getFolders(currentFolderID, position).observe(fragmentActivity, adapter::setFolders);
                }
                else {
                    homeViewModel.getCards(currentFolderID, position).observe(fragmentActivity, adapter::setCards);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                homeViewModel.getFolders(currentFolderID, 0).observe(fragmentActivity, adapter::setFolders);
            }

        });

        // Configure Item Touch Helper for Drag & Drop Function
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                ItemTouchHelper.START | ItemTouchHelper.END, 0) {

            //TODO: Quick and Dirty Fix for shuffling Bug. OnMove is called 2 times each Drag???
            int lastFromPosition = 0;
            int lastToPosition = 0;
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                if (!filterDropdown.getSelectedItem().equals("Manual Order")) {
                    Toast.makeText(getContext(), "Manuelle Reihenfolge für Drag & Drop auswählen!", Toast.LENGTH_SHORT).show();
                }
                else {
                    int fromPosition = viewHolder.getAdapterPosition();
                    int toPosition = target.getAdapterPosition();
                    if(lastFromPosition != fromPosition || lastToPosition != toPosition) {
                        if(!currentFolderContainsCards) {
                            Folder fromFolder = homeViewModel.getSingleFolderByManualOrder(currentFolderID, fromPosition);
                            Folder toFolder = homeViewModel.getSingleFolderByManualOrder(currentFolderID, toPosition);

                            fromFolder.setManualOrderID(toPosition);
                            toFolder.setManualOrderID(fromPosition);
                            homeViewModel.updateFolder(fromFolder);
                            homeViewModel.updateFolder(toFolder);

                            adapter.swapFolders(fromPosition, toPosition);
                            homeViewModel.getFolders(currentFolderID, 0).observe(fragmentActivity, adapter::setFolders);
                        }
                        else {
                            Card fromCard = homeViewModel.getSingleCardByManualOrder(currentFolderID, fromPosition);
                            Card toCard = homeViewModel.getSingleCardByManualOrder(currentFolderID, toPosition);

                            fromCard.setManualOrderID(toPosition);
                            toCard.setManualOrderID(fromPosition);
                            homeViewModel.updateCard(fromCard);
                            homeViewModel.updateCard(toCard);

                            adapter.swapCards(fromPosition, toPosition);
                            homeViewModel.getCards(currentFolderID, 0).observe(fragmentActivity, adapter::setCards);
                        }

                        lastFromPosition = fromPosition;
                        lastToPosition = toPosition;
                    }
                }

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }
        };

        // Item Touch Helper
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        ImageView AddFolderOrCardBtn = root.findViewById(R.id.btn_add_folder_or_card);
        AddFolderOrCardBtn.setOnClickListener(this.addFolderOrCardClickListener);

        return root;

    }

    //Redirect to CreateFolderOrCard Fragment
    private final View.OnClickListener addFolderOrCardClickListener= v -> {

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        navController.navigate(R.id.navigation_create_folder_or_card);

    };
}
