package de.fhe.ai.pme.swipe.view.ui.home.configuration.folder;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import de.fhe.ai.pme.swipe.model.Folder;
import de.fhe.ai.pme.swipe.storage.SwipeRepository;

public class FolderConfigurationViewModel extends AndroidViewModel {

    private final SwipeRepository swipeRepository;

    public FolderConfigurationViewModel(Application application)
    {
        super(application);
        swipeRepository = SwipeRepository.getRepository(application);
    }

    public void saveFolder(Folder folder)
    {
        swipeRepository.insert(folder);

        // If not already, set containsFolders for Parent Folder
        long parentFolderID = folder.getParentFolderID();
        if(parentFolderID != 0) {
            Folder parentFolder = swipeRepository.getFolderWithID(parentFolderID);
            parentFolder.setContainsFolders(true);
            swipeRepository.update(parentFolder);
        }
    }

    public int getNextManualOrderID (long parentFolderID) {
        List<Folder> folderList = swipeRepository.getFoldersActualValue(parentFolderID);
        if(folderList == null) {
            return 0;
        }
        return folderList.size();
    }

}
