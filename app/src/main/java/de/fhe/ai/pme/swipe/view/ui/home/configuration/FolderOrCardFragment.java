package de.fhe.ai.pme.swipe.view.ui.home.configuration;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import de.fhe.ai.pme.swipe.R;
import de.fhe.ai.pme.swipe.view.ui.core.BaseFragment;
import de.fhe.ai.pme.swipe.view.ui.home.configuration.folder.FolderConfigurationFragment;

public class FolderOrCardFragment extends BaseFragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_create_folder_or_card, container, false);

        return root;
    }

    // Redirect to Folder Configuration Fragment
    private final View.OnClickListener addFolder= v -> {

        NavController navController = Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment);

        navController.navigate(R.id.navigation_folder_configuration);
    };

    private final View.OnClickListener addCard= v -> {

        NavController navController = Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment);

        navController.navigate(R.id.navigation_folder_configuration);
    };

}