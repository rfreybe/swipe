package de.fhe.ai.pme.swipe.view.ui.home.configuration.card;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.security.Key;

import de.fhe.ai.pme.swipe.R;
import de.fhe.ai.pme.swipe.model.Card;
import de.fhe.ai.pme.swipe.model.Folder;
import de.fhe.ai.pme.swipe.model.Page;
import de.fhe.ai.pme.swipe.storage.KeyValueStore;
import de.fhe.ai.pme.swipe.view.ui.core.BaseFragment;


//opens after you decided for adding a Card


public class CardConfigurationFragment extends BaseFragment {

    private CardConfigurationViewModel cardConfigurationViewModel;
    private KeyValueStore keyValueStore;

    private EditText CardNameField;
    private EditText CardQuestionField;
    private EditText CardAnswerField;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        cardConfigurationViewModel = this.getViewModel(CardConfigurationViewModel.class);
        keyValueStore = new KeyValueStore(getActivity().getApplication());

        View root = inflater.inflate(R.layout.fragment_card_configuration, container, false);

        this.CardNameField = root.findViewById(R.id.et_name_of_card);
        this.CardQuestionField = root.findViewById(R.id.et_question);
        this.CardAnswerField = root.findViewById(R.id.et_answer);

        //ASSIGN BUTTON THE FUNCTION
        Button saveBtn = root.findViewById(R.id.btn_save_card);
        saveBtn.setOnClickListener(this.saveCardButtonClickListener);

        return root;
    }

    public void ClickAddFile(View view){

    }

    public void ClickSubmitChanges(View view){

    }
    // Redirect to CreateFolderOrCard Fragment
    private final View.OnClickListener saveCardButtonClickListener= v -> {

        //Create Front Page
        Page frontPage = new Page(true);
        frontPage.setText(CardQuestionField.getText().toString());
        long frontPageID = cardConfigurationViewModel.savePage(frontPage);

        //Create Back Page
        Page backPage = new Page(false);
        backPage.setText(CardAnswerField.getText().toString());
        long backPageID = cardConfigurationViewModel.savePage(backPage);

        //Create Card
        //TODO: ID's of Front Page & Back Page still 0
        Card newCard = new Card(CardNameField.getText().toString(), keyValueStore.getValueLong("currentFolderID"), frontPageID, backPageID);
        cardConfigurationViewModel.saveCard(newCard);



        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        navController.navigate(R.id.navigation_create_folder_or_card);

    };


}