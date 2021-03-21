package de.fhe.ai.pme.swipe.view.ui.home.configuration.folder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;

import java.security.Key;

import de.fhe.ai.pme.swipe.R;
import de.fhe.ai.pme.swipe.model.Folder;
import de.fhe.ai.pme.swipe.storage.KeyValueStore;
import de.fhe.ai.pme.swipe.view.ui.core.BaseFragment;


//opens after you decided for adding a Card

public class FolderConfigurationFragment extends BaseFragment {

    private FolderConfigurationViewModel folderConfigurationViewModel;
    private KeyValueStore keyValueStore;
    private EditText folderNameField;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        folderConfigurationViewModel = this.getViewModel(FolderConfigurationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_folder_configuration, container, false);
        keyValueStore = new KeyValueStore(getActivity().getApplication());

        folderNameField = root.findViewById(R.id.et_name_of_folder);

        //Set OnClickListener
        Button saveBtn = root.findViewById(R.id.btn_save_folder);
        saveBtn.setOnClickListener(this.saveFolderButtonClickListener);

        return root;
    }

    private final View.OnClickListener saveFolderButtonClickListener= v -> {

        //Create new Folder and insert it
        long currentFolderID = keyValueStore.getValueLong("currentFolderID");
        Folder newFolder = new Folder ( folderNameField.getText().toString(), currentFolderID);
        newFolder.setManualOrderID(folderConfigurationViewModel.getNextManualOrderID(currentFolderID));
        folderConfigurationViewModel.saveFolder( newFolder );

        //Redirect to CreateFolderOrCardFragment so the User can add additional Items
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.navigation_create_folder_or_card);
    };

}