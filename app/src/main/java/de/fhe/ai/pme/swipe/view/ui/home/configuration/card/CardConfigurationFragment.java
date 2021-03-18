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

import de.fhe.ai.pme.swipe.R;
import de.fhe.ai.pme.swipe.model.Card;
import de.fhe.ai.pme.swipe.model.Folder;
import de.fhe.ai.pme.swipe.model.Page;
import de.fhe.ai.pme.swipe.view.ui.core.BaseFragment;


//opens after you decided for adding a Card


public class CardConfigurationFragment extends BaseFragment {

    private CardConfigurationViewModel cardConfigurationViewModel;
    private PageConfigurationViewModel pageConfigurationViewModel;


    private EditText CardNameField;
    private EditText CardQuestionField;
    private EditText CardAnswerField;
    private Button SaveButton;

    private final View.OnClickListener saveButtonClickListener = v -> {

        if( v.getId() == R.id.btn_save_card)
        {
            Card newCard = new Card(CardNameField.getText().toString(),1);
            Page newFrontPage = new Page(newCard.getCardID(),true);
            Page newBackPage = new Page(newCard.getCardID(),false);

            newFrontPage.setText(CardQuestionField.getText().toString());
            newBackPage.setText(CardAnswerField.getText().toString());

            cardConfigurationViewModel.saveCard(newCard);
            pageConfigurationViewModel.savePage(newFrontPage);
            pageConfigurationViewModel.savePage(newBackPage);

        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        cardConfigurationViewModel = this.getViewModel(CardConfigurationViewModel.class);
        pageConfigurationViewModel = this.getViewModel(PageConfigurationViewModel.class);

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

        Card newCard = new Card(
                CardNameField.getText().toString(),
                0);
        cardConfigurationViewModel.saveCard(newCard);

        int cardID = newCard.getCardID();   //not working maybe try to get with modified


        Page newFrontPage = new Page(
                newCard.getCardID(),
                true);
        newFrontPage.setText(CardQuestionField.getText().toString());

        pageConfigurationViewModel.savePage(newFrontPage);

        Page newBackPage = new Page(
                newCard.getCardID(),
        false);

        newBackPage.setText(CardAnswerField.getText().toString());

        pageConfigurationViewModel.savePage(newBackPage);

        NavController navController = Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment);

        navController.navigate(R.id.navigation_create_folder_or_card);

    };


}