package de.fhe.ai.pme.swipe.view.ui.home.configuration;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import de.fhe.ai.pme.swipe.storage.SwipeRepository;

public class CreateFolderOrCardViewModel extends AndroidViewModel {

    private final SwipeRepository swipeRepository;

    public CreateFolderOrCardViewModel(Application application)
    {
        super(application);
        this.swipeRepository = SwipeRepository.getRepository(application);
    }



}



