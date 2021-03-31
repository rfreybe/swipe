package de.fhe.ai.pme.swipe.view.ui.home.configuration.card_view;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import de.fhe.ai.pme.swipe.model.Card;
import de.fhe.ai.pme.swipe.model.Page;
import de.fhe.ai.pme.swipe.storage.SwipeRepository;

public class CardViewViewModel extends AndroidViewModel {
    private final SwipeRepository swipeRepository;

    public CardViewViewModel(Application application) {
        super(application);
        swipeRepository = SwipeRepository.getRepository(application);
    }

    public Card getCardByID(long cardID) {
        return this.swipeRepository.getCardByCardID(cardID);
    }

    public Page getFrontPage(long cardID) {
        Card currentlyViewedCard = this.swipeRepository.getCardByCardID(cardID);
        long frontPageID = currentlyViewedCard.getFrontPageID();
        Page frontPage = this.swipeRepository.getPageByID(frontPageID);

        return frontPage;
    }

    public Page getBackPage(long cardID) {
        Card currentlyViewedCard = this.swipeRepository.getCardByCardID(cardID);
        long backPageID = currentlyViewedCard.getBackPageID();
        Page backPage = this.swipeRepository.getPageByID(backPageID);

        return backPage;
    }
}
