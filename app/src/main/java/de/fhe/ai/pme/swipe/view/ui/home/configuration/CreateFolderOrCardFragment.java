package de.fhe.ai.pme.swipe.view.ui.home.configuration;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import de.fhe.ai.pme.swipe.R;
import de.fhe.ai.pme.swipe.view.ui.core.BaseFragment;


public class CreateFolderOrCardFragment extends BaseFragment {

    private CreateFolderOrCardViewModel createFolderOrCardViewModel;


    private final View.OnClickListener ClickAddFolder = v -> {

    //Redirect to add folder
    };

    private final View.OnClickListener ClickAddCard = v -> {
    //Redirect to add folder
    };

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        createFolderOrCardViewModel = this.getViewModel(CreateFolderOrCardViewModel.class);


        View root = inflater.inflate(R.layout.fragment_create_folder_or_card, container, false);

        return root;
    }
}