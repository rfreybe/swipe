package de.fhe.ai.pme.swipe.view.ui.home.configuration.folder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import de.fhe.ai.pme.swipe.R;
import de.fhe.ai.pme.swipe.model.Folder;
import de.fhe.ai.pme.swipe.view.ui.core.BaseFragment;


//opens after you decided for adding a Card

public class FolderConfigurationFragment extends BaseFragment {

    private FolderConfigurationViewModel folderConfigurationViewModel;
    private EditText foldernameField;


    private final View.OnClickListener saveButtonClickListener = v -> {

        if( v.getId() == R.id.btn_save_folder)
        {
            Folder newFolder = new Folder(foldernameField.getText().toString(),1);

            //String returnValue = folderConfigurationViewModel.saveFolder(newFolder);

            hideKeyboard( this.requireContext(), v );
            //Snackbar.make(v, returnValue, Snackbar.LENGTH_SHORT).show();
        }
    };


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        folderConfigurationViewModel = this.getViewModel(FolderConfigurationViewModel.class);

        View root = inflater.inflate(R.layout.fragment_folder_configuration, container, false);

        this.foldernameField = root.findViewById(R.id.et_name_of_folder);

       //ASSIGN BUTTON THE FUNCTION
        Button saveBtn = root.findViewById(R.id.btn_save_folder);
        saveBtn.setOnClickListener(this.saveButtonClickListener);

        return root;
    }

}