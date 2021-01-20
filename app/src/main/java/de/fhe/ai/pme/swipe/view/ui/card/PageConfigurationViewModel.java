package de.fhe.ai.pme.swipe.view.ui.card;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import de.fhe.ai.pme.swipe.model.Card;
import de.fhe.ai.pme.swipe.model.Page;
import de.fhe.ai.pme.swipe.storage.SwipeRepository;

public class PageConfigurationViewModel extends AndroidViewModel {

    private final SwipeRepository swipeRepository;

    public PageConfigurationViewModel(Application application)
    {
        super(application);
        this.swipeRepository = SwipeRepository.getRepository(application);
    }


    //Insert Card
    public void savePage(Page page)
    {
        this.swipeRepository.insert(page);
    }
}

