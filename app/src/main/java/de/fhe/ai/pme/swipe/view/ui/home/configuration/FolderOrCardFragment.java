package de.fhe.ai.pme.swipe.view.ui.home.configuration;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import de.fhe.ai.pme.swipe.R;
import de.fhe.ai.pme.swipe.view.ui.core.BaseFragment;

public class FolderOrCardFragment extends BaseFragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_create_folder_or_card, container, false);

        return root;
    }

}