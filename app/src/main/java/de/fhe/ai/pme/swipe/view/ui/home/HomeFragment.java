package de.fhe.ai.pme.swipe.view.ui.home;

import android.os.Bundle;
import android.util.Log;
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

    //TODO: Bug - Sometimes shuffles Folders randomly when swapping them
    // Only after trying to Drag and Drop with other filter then manual order
    RecyclerViewClickListener itemListener = new RecyclerViewClickListener() {
        @Override
        public void itemClick(View v, int position) {

            if(!keyValueStore.getValueBool("currentFolderContainsCards")) {
                Folder clickedFolder = homeViewModel.getSingleFolderByManualOrder(keyValueStore.getValueLong("currentFolderID"), position);

                // Set Information about current Folder for Fragment and KeyValueStore
                keyValueStore.editValueLong("currentFolderID", clickedFolder.getFolderID());
                keyValueStore.editValueBool("currentFolderContainsCards", clickedFolder.getContainsCards());
                keyValueStore.editValueBool("currentFolderContainsFolders", clickedFolder.getContainsFolders());

                if(!keyValueStore.getValueBool("currentFolderContainsCards")) {
                    homeViewModel.getFolders(keyValueStore.getValueLong("currentFolderID"), filterDropdown.getSelectedItemPosition()).observe(requireActivity(), adapter::setFolders);
                }
                else {
                    // Set Card Spinner
//                    int selectedItemPosition = filterDropdown.getSelectedItemPosition();
//                    if(filterDropdown.getAdapter() != arrayAdapterCard) {
//                        filterDropdown.setAdapter(arrayAdapterCard);
//                        if(selectedItemPosition == 3 || selectedItemPosition == 4) {
//                            selectedItemPosition = 0;
//                        }
//                        else if(selectedItemPosition == 5 || selectedItemPosition == 6) {
//                            selectedItemPosition = selectedItemPosition - 2;
//                        }
//                    }

                    homeViewModel.getCards(keyValueStore.getValueLong("currentFolderID"), filterDropdown.getSelectedItemPosition()).observe(requireActivity(), adapter::setCards);
                }
            }
            else {
                //TODO: ViewPager for Cards
                long clickedCardID = homeViewModel.getSingleCardByManualOrder(keyValueStore.getValueLong("currentFolderID"), position).getCardID();
                keyValueStore.editValueLong("currentlyViewedCardID", clickedCardID);

                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

                navController.navigate(R.id.navigation_card);
            }
            Toast.makeText(getContext(), "currentFolderID: " + String.valueOf( keyValueStore.getValueLong("currentFolderID"))
                    + " currentFolderContainsCards: " + keyValueStore.getValueBool("currentFolderContainsCards")
                    + " currentFolderContainsFolders: " + keyValueStore.getValueBool("currentFolderContainsFolders"),Toast.LENGTH_SHORT).show();

        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Log.i("HomeFragment","onCreateView Called");

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

        // Array Adapter for Filter Dropdown
        ArrayAdapter<CharSequence> arrayAdapterDropdown = ArrayAdapter.createFromResource(getContext(),
                R.array.array_filter_dropdown, android.R.layout.simple_spinner_item);
        arrayAdapterDropdown.setDropDownViewResource(R.layout.item_dropdown);
        // Set initial Dropdown on Folder View
        filterDropdown.setAdapter(arrayAdapterDropdown);

        //keyValueStore.editValueLong("currentFolderID", 0);

//        if(!keyValueStore.getValueBool("currentFolderContainsCards")) {
//            homeViewModel.getFolders(keyValueStore.getValueLong("currentFolderID"), 0).observe(fragmentActivity, adapter::setFolders);
//        }
//        else {
//            homeViewModel.getCards(keyValueStore.getValueLong("currentFolderID"), 0).observe(fragmentActivity, adapter::setCards);
//        }

        Toast.makeText(getContext(), "currentFolderID: " + String.valueOf( keyValueStore.getValueLong("currentFolderID"))
                + " currentFolderContainsCards: " + keyValueStore.getValueBool("currentFolderContainsCards")
                + " currentFolderContainsFolders: " + keyValueStore.getValueBool("currentFolderContainsFolders"),Toast.LENGTH_SHORT).show();

        // Create Listener
        filterDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Observe the LiveData Contact List in our View Model - if something changes, we
                // update the list in our Adapter
                if(!keyValueStore.getValueBool("currentFolderContainsCards")) {
                    homeViewModel.getFolders(keyValueStore.getValueLong("currentFolderID"), position).observe(fragmentActivity, adapter::setFolders);
                }
                else {
                    homeViewModel.getCards(keyValueStore.getValueLong("currentFolderID"), position).observe(fragmentActivity, adapter::setCards);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if(!keyValueStore.getValueBool("currentFolderContainsCards")) {
                    homeViewModel.getFolders(keyValueStore.getValueLong("currentFolderID"), 0).observe(fragmentActivity, adapter::setFolders);
                }
                else {
                    homeViewModel.getCards(keyValueStore.getValueLong("currentFolderID"), 0).observe(fragmentActivity, adapter::setCards);
                }
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
                        if(!keyValueStore.getValueBool("currentFolderContainsCards")) {
                            Folder fromFolder = homeViewModel.getSingleFolderByManualOrder(keyValueStore.getValueLong("currentFolderID"), fromPosition);
                            Folder toFolder = homeViewModel.getSingleFolderByManualOrder(keyValueStore.getValueLong("currentFolderID"), toPosition);

                            fromFolder.setManualOrderID(toPosition);
                            toFolder.setManualOrderID(fromPosition);
                            homeViewModel.updateFolder(fromFolder);
                            homeViewModel.updateFolder(toFolder);

                            adapter.swapFolders(fromPosition, toPosition);
                            homeViewModel.getFolders(keyValueStore.getValueLong("currentFolderID"), 0).observe(fragmentActivity, adapter::setFolders);
                        }
                        else {
                            Card fromCard = homeViewModel.getSingleCardByManualOrder(keyValueStore.getValueLong("currentFolderID"), fromPosition);
                            Card toCard = homeViewModel.getSingleCardByManualOrder(keyValueStore.getValueLong("currentFolderID"), toPosition);

                            fromCard.setManualOrderID(toPosition);
                            toCard.setManualOrderID(fromPosition);
                            homeViewModel.updateCard(fromCard);
                            homeViewModel.updateCard(toCard);

                            adapter.swapCards(fromPosition, toPosition);
                            homeViewModel.getCards(keyValueStore.getValueLong("currentFolderID"), 0).observe(fragmentActivity, adapter::setCards);
                        }

                        lastFromPosition = fromPosition;
                        lastToPosition = toPosition;
                    }
                }

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                Folder swipedFolder = homeViewModel.getSingleFolderByManualOrder(keyValueStore.getValueLong("currentFolderID"), viewHolder.getAdapterPosition());
//                homeViewModel.deleteFolder(swipedFolder);
            }
        };

        // Item Touch Helper
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        // On Click Listener Add Folder or Card
        ImageView AddFolderOrCardBtn = root.findViewById(R.id.btn_add_folder_or_card);
        AddFolderOrCardBtn.setOnClickListener(this.addFolderOrCardClickListener);

        // On Click Listener Back Button
        ImageView BackBtn = getActivity().findViewById(R.id.back_button);
        BackBtn.setOnClickListener(this.backBtnListener);

        return root;
    }

    //Redirect to CreateFolderOrCard Fragment
    private final View.OnClickListener addFolderOrCardClickListener= v -> {

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        navController.navigate(R.id.navigation_create_folder_or_card);
    };

    // Change currentFolderID to ID of parentFolder
    private final View.OnClickListener backBtnListener= v -> {
        if(keyValueStore.getValueLong("currentFolderID") != 0) {
            Folder currentFolder = homeViewModel.getFolderWithID(keyValueStore.getValueLong("currentFolderID"));
            long parentFolderID = currentFolder.getParentFolderID();
            keyValueStore.editValueLong("currentFolderID", parentFolderID);

            if(keyValueStore.getValueLong("currentFolderID") != 0) {
                Folder parentFolder = homeViewModel.getFolderWithID(keyValueStore.getValueLong("currentFolderID"));
                keyValueStore.editValueBool("currentFolderContainsCards", parentFolder.getContainsCards());
                keyValueStore.editValueBool("currentFolderContainsFolders", parentFolder.getContainsFolders());

            }
            else {
                keyValueStore.editValueBool("currentFolderContainsCards", false);
                keyValueStore.editValueBool("currentFolderContainsFolders", true);
            }

            homeViewModel.getFolders(keyValueStore.getValueLong("currentFolderID"), filterDropdown.getSelectedItemPosition()).observe(getViewLifecycleOwner(), adapter::setFolders);

            Toast.makeText(v.getContext(), "currentFolderID: " + String.valueOf( keyValueStore.getValueLong("currentFolderID"))
                    + " currentFolderContainsCards: " + keyValueStore.getValueBool("currentFolderContainsCards")
                    + " currentFolderContainsFolders: " + keyValueStore.getValueBool("currentFolderContainsFolders"),Toast.LENGTH_SHORT).show();

        }
    };
}
