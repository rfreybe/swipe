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


public class CreateFolderOrCardFragment extends BaseFragment {

    private CreateFolderOrCardViewModel createFolderOrCardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        createFolderOrCardViewModel = this.getViewModel(CreateFolderOrCardViewModel.class);


        View root = inflater.inflate(R.layout.fragment_create_folder_or_card, container, false);

        LinearLayout add_folder = root.findViewById(R.id.add_folder);
        add_folder.setOnClickListener(this.addFolder);

        LinearLayout add_card = root.findViewById(R.id.add_card);
        add_card.setOnClickListener(this.addCard);

        return root;
    }

    // Redirect to Folder Configuration Fragment
    private final View.OnClickListener addFolder= v -> {

        NavController navController = Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment);

        navController.navigate(R.id.navigation_folder_configuration);
    };

    // Redirect to Card Configuration Fragment
    private final View.OnClickListener addCard= v -> {

        NavController navController = Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment);

        navController.navigate(R.id.navigation_card_configuration);
    };

}