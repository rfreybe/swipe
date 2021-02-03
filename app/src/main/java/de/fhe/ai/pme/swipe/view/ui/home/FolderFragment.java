package de.fhe.ai.pme.swipe.view.ui.home;

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
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.fhe.ai.pme.swipe.R;
import de.fhe.ai.pme.swipe.view.ui.core.BaseFragment;

public class FolderFragment extends BaseFragment {

    private FolderViewModel folderViewModel;
    private int currentFolderID;

    //Redirect to CreateFolderOrCard fragment
    private final View.OnClickListener addFolderOrCardClickListener= v -> {


    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // TODO: relate parentFolderID to currently selected Folder
        /* Temporary */
        currentFolderID = 0;

        // Create Layout/Views
        folderViewModel = this.getViewModel(FolderViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Get View Model
        FolderViewModel folderViewModel = this.getViewModel(FolderViewModel.class);

        // Get RecyclerView Reference
        RecyclerView folderView = root.findViewById(R.id.recycler_view_folders);

        // Get FragmentActivity Reference
        FragmentActivity fragmentActivity = this.requireActivity();

        // Create Adapter
        final FolderAdapter adapter = new FolderAdapter(fragmentActivity);

        // Configure RecyclerView with Adapter and LayoutManager
        folderView.setAdapter( adapter );
        //folderView.setLayoutManager( new LinearLayoutManager(this.requireActivity() ) );
        folderView.setLayoutManager( new GridLayoutManager(this.requireActivity(), 2) );

        // Create Spinner Dropdown for Filters
        Spinner filterDropdown = (Spinner) root.findViewById(R.id.filters_folder);
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

        return root;



    }
}
