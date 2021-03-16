package de.fhe.ai.pme.swipe.view.ui.home;

import android.content.ClipData;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import de.fhe.ai.pme.swipe.R;
import de.fhe.ai.pme.swipe.model.Folder;
import de.fhe.ai.pme.swipe.storage.SwipeRepository;
import de.fhe.ai.pme.swipe.view.ui.core.BaseFragment;
import de.fhe.ai.pme.swipe.view.ui.core.RecyclerViewClickListener;

public class FolderFragment extends BaseFragment {

    private FolderViewModel folderViewModel;
    private FolderAdapter adapter;
    private Spinner filterDropdown;
    private static int currentFolderID;

    //TODO: Bug - Sometimes shuffles Folders randomly when swapping them
    // Only after trying to Drag and Drop with other filter then manual order
    RecyclerViewClickListener itemListener = new RecyclerViewClickListener() {
        @Override
        public void folderClick(View v, int position) {
            Folder clickedFolder = folderViewModel.getSingleFolderByManualOrder(currentFolderID, position);
            currentFolderID = clickedFolder.getFolderID();
            folderViewModel.getFolders(currentFolderID, filterDropdown.getSelectedItemPosition()).observe(requireActivity(), adapter::setFolders);
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        currentFolderID = 0;

        // Create Layout/Views
        folderViewModel = this.getViewModel(FolderViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Get View Model
        FolderViewModel folderViewModel = this.getViewModel(FolderViewModel.class);

        // Get RecyclerView Reference
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view_folders);
        // Divider Item Decoration to differ between folders
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(root.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        // Get FragmentActivity Reference
        FragmentActivity fragmentActivity = this.requireActivity();

        // Create Adapter
        adapter = new FolderAdapter(fragmentActivity, itemListener);

        // Configure RecyclerView with Adapter and LayoutManager
        recyclerView.setAdapter( adapter );
        recyclerView.setLayoutManager( new LinearLayoutManager(this.requireActivity() ) );
        //recyclerView.setLayoutManager( new GridLayoutManager(this.requireActivity(), 2) );

        // Create Spinner Dropdown for Filters
        filterDropdown = root.findViewById(R.id.filters_folder);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.array_folder_dropdown, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(R.layout.item_dropdown);
        filterDropdown.setAdapter(arrayAdapter);

        // Create Listener
        filterDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Observe the LiveData Contact List in our View Model - if something changes, we
                // update the list in our Adapter
                folderViewModel.getFolders(currentFolderID, position).observe(fragmentActivity, adapter::setFolders);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                folderViewModel.getFolders(currentFolderID, 0).observe(fragmentActivity, adapter::setFolders);
            }

        });

        // Configure Item Touch Helper for Drag & Drop Function
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                ItemTouchHelper.START | ItemTouchHelper.END, 0) {

            //TODO: Quick and Dirty Fix for shuffling Bug. OnMove is called 2 times each Drag???
            int lastFromPosition = 0;
            int lastToPosition = 0;
            @Override
            //TODO: Make manualOrderID related to parentFolderID -> unique manualOrder inside every Folder
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                if (!filterDropdown.getSelectedItem().equals("Manual Order")) {
                    Toast.makeText(getContext(), "Manuelle Reihenfolge für Drag & Drop auswählen!", Toast.LENGTH_SHORT).show();
                }
                else {
                    int fromPosition = viewHolder.getAdapterPosition();
                    int toPosition = target.getAdapterPosition();
                    if(lastFromPosition != fromPosition || lastToPosition != toPosition) {
                        Folder fromFolder = folderViewModel.getSingleFolderByManualOrder(currentFolderID, fromPosition);
                        Folder toFolder = folderViewModel.getSingleFolderByManualOrder(currentFolderID, toPosition);

                        fromFolder.setManualOrderID(toPosition);
                        toFolder.setManualOrderID(fromPosition);
                        folderViewModel.updateFolder(fromFolder);
                        folderViewModel.updateFolder(toFolder);

                        adapter.swapFolders(fromPosition, toPosition);
                        folderViewModel.getFolders(currentFolderID, 0).observe(fragmentActivity, adapter::setFolders);

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

        NavController navController = Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment);

        navController.navigate(R.id.navigation_create_folder_or_card);

    };
}
