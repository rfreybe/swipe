package de.fhe.ai.pme.swipe.view.ui.core;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import de.fhe.ai.pme.swipe.R;


public class CreateFolderOrCard extends BaseFragment {


    private final View.OnClickListener ClickAddFolder = v -> {

    //Redirect to add folder
    };

    private final View.OnClickListener ClickAddCard = v -> {
    //Redirect to add folder
    };

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_create_folder_or_card, container, false);

        return root;
    }
}