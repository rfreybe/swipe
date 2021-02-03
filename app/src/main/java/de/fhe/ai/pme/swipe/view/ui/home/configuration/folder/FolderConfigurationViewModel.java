package de.fhe.ai.pme.swipe.view.ui.home.configuration.folder;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import de.fhe.ai.pme.swipe.model.Folder;
import de.fhe.ai.pme.swipe.storage.SwipeRepository;

public class FolderConfigurationViewModel extends AndroidViewModel {

    //VIEWMODEL FOLDER CONFIG

    private final SwipeRepository swipeRepository;

    public FolderConfigurationViewModel(Application application)
    {
        super(application);
        this.swipeRepository = SwipeRepository.getRepository(application);
    }


    //INSERT FOLDER

    public void saveFolder(Folder folder)
    {
        this.swipeRepository.insert(folder);
    }

}
