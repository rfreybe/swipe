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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
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
import de.fhe.ai.pme.swipe.view.ui.home.configuration.FolderOrCardFragment;

public class FolderFragment extends BaseFragment {

    private FolderViewModel folderViewModel;
    private int currentFolderID;

    //Redirect to CreateFolderOrCard fragment
    private final View.OnClickListener addFolderOrCardClickListener= v -> {

    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button newGameButton = (Button) view.findViewById(R.id.btn_add_folder_or_card);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swapFragment();

            }
        });


        // TODO: relate parentFolderID to currently selected Folder
        /* Temporary */
        currentFolderID = 0;

        // Create Layout/Views
        folderViewModel = this.getViewModel(FolderViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Get View Model
        FolderViewModel folderViewModel = this.getViewModel(FolderViewModel.class);

        // Get RecyclerView Reference
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view_folders);

        // Get FragmentActivity Reference
        FragmentActivity fragmentActivity = this.requireActivity();

        // Create Adapter
        final FolderAdapter adapter = new FolderAdapter(fragmentActivity);

        // Configure RecyclerView with Adapter and LayoutManager
        recyclerView.setAdapter( adapter );
        recyclerView.setLayoutManager( new LinearLayoutManager(this.requireActivity() ) );
        //recyclerView.setLayoutManager( new GridLayoutManager(this.requireActivity(), 2) );

        // Divider Item Decoration to differ between folders
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(root.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                ItemTouchHelper.START | ItemTouchHelper.END, 0) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                Folder fromFolder = folderViewModel.getSingleFolderByManualOrder(currentFolderID, fromPosition);
                Folder toFolder = folderViewModel.getSingleFolderByManualOrder(currentFolderID, toPosition);

                fromFolder.setManualOrderID(toPosition);
                toFolder.setManualOrderID(fromPosition);
                folderViewModel.updateFolder(fromFolder);
                folderViewModel.updateFolder(toFolder);

                adapter.swapFolders(fromPosition, toPosition);

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }

        };

        // Item Touch Helper
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        // Create Spinner Dropdown for Filters
        Spinner filterDropdown = root.findViewById(R.id.filters_folder);
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

        Button AddFolderOrCardBtn = root.findViewById(R.id.btn_add_folder_or_card);
        AddFolderOrCardBtn.setOnClickListener(this.addFolderOrCardClickListener);

        return view; // root muss auch noch returnt werden

    }

    private void swapFragment() {
        FolderOrCardFragment folderOrCardFragment = new FolderOrCardFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, folderOrCardFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}
