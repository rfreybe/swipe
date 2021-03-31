package de.fhe.ai.pme.swipe.view.ui.home.configuration;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import de.fhe.ai.pme.swipe.R;
import de.fhe.ai.pme.swipe.model.Folder;
import de.fhe.ai.pme.swipe.storage.KeyValueStore;
import de.fhe.ai.pme.swipe.view.ui.core.BaseFragment;
import de.fhe.ai.pme.swipe.view.ui.home.HomeViewModel;


public class CreateFolderOrCardFragment extends BaseFragment {

    private KeyValueStore keyValueStore;
    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_create_folder_or_card, container, false);
        keyValueStore = new KeyValueStore(getActivity().getApplication());
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        LinearLayout add_folder = root.findViewById(R.id.add_folder);
        add_folder.setOnClickListener(this.addFolder);

        LinearLayout add_card = root.findViewById(R.id.add_card);
        add_card.setOnClickListener(this.addCard);

        // On Click Listener Back Button
        ImageView BackBtn = getActivity().findViewById(R.id.back_button);
        BackBtn.setOnClickListener(this.backBtnListener);

        return root;
    }

    // Redirect to Folder Configuration Fragment
    //TODO: Look out for currentFolderValues when deleting contents
    private final View.OnClickListener addFolder= v -> {
        if(keyValueStore.getValueBool("currentFolderContainsCards")) {
            Toast.makeText(getContext(), "Ordner ohne Karteikarten auswählen um Ordner hinzuzufügen!", Toast.LENGTH_SHORT).show();
        }
        else {
            navController.navigate(R.id.navigation_folder_configuration);
        }
    };

    // Redirect to Card Configuration Fragment
    private final View.OnClickListener addCard= v -> {

        if(keyValueStore.getValueBool("currentFolderContainsFolders") || keyValueStore.getValueLong("currentFolderID") == 0) {
            Toast.makeText(getContext(), "Ordner ohne weitere Ordner auswählen um Karteikarten hinzuzufügen!", Toast.LENGTH_SHORT).show();
        }
        else {
            navController.navigate(R.id.navigation_card_configuration);
        }

    };

    private final View.OnClickListener backBtnListener= v -> {

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        navController.navigate(R.id.navigation_home);
    };

}