package de.fhe.ai.pme.swipe.view.ui.card;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import de.fhe.ai.pme.swipe.R;
import de.fhe.ai.pme.swipe.model.Card;
import de.fhe.ai.pme.swipe.model.Page;
import de.fhe.ai.pme.swipe.view.ui.core.BaseFragment;


//opens after you decided for adding a Card


public class CardConfiguration extends BaseFragment {

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
        saveBtn.setOnClickListener(this.saveButtonClickListener);

        return root;
    }

    public void ClickAddFile(View view){


    }

    public void ClickSubmitChanges(View view){


    }


}