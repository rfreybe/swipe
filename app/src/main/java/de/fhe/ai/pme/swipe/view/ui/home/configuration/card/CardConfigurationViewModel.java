package de.fhe.ai.pme.swipe.view.ui.home.configuration.card;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import de.fhe.ai.pme.swipe.model.Card;
import de.fhe.ai.pme.swipe.storage.SwipeRepository;

public class  CardConfigurationViewModel extends AndroidViewModel {

    private final SwipeRepository swipeRepository;

    public CardConfigurationViewModel(Application application)
    {
        super(application);
        this.swipeRepository = SwipeRepository.getRepository(application);
    }


    //Insert Card
    public void saveCard(Card card)
    {
       this.swipeRepository.insert(card);
    }
}

