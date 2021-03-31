package de.fhe.ai.pme.swipe.view.ui.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import de.fhe.ai.pme.swipe.model.Card;
import de.fhe.ai.pme.swipe.model.Folder;
import de.fhe.ai.pme.swipe.storage.SwipeRepository;

public class HomeViewModel extends AndroidViewModel {

    private final SwipeRepository swipeRepository;

    public HomeViewModel(Application application) {
        super(application);
        swipeRepository = SwipeRepository.getRepository(application);
    }

    // Switch Case decides which Repository Method to use according to filter
    public LiveData<List<Folder>> getFolders(long parentFolderID, int filter)
    {
        switch(filter){
            default:
                return swipeRepository.getFoldersByUserOrder(parentFolderID);
            case 1:
                return swipeRepository.getFoldersByNameAsc(parentFolderID);
            case 2:
                return swipeRepository.getFoldersByNameDesc(parentFolderID);
            case 3:
                return swipeRepository.getFoldersByUpdateAsc(parentFolderID);
            case 4:
                return swipeRepository.getFoldersByUpdateDesc(parentFolderID);

        }
    }

    public LiveData<List<Card>> getCards(long parentFolderID, int filter)
    {
        switch(filter){
            default:
                return swipeRepository.getCardsByUserOrder(parentFolderID);
            case 1:
                return swipeRepository.getCardsByNameAsc(parentFolderID);
            case 2:
                return swipeRepository.getCardsByNameDesc(parentFolderID);
            case 3:
                return swipeRepository.getCardsByUpdateAsc(parentFolderID);
            case 4:
                return swipeRepository.getCardsByUpdateDesc(parentFolderID);
        }
    }

    // Methods for Drag & Drop function
    public Folder getSingleFolderByManualOrder(long parentFolderID, long manualOrderID)
    {
        return swipeRepository.getFirstFolderByUserOrder(parentFolderID, manualOrderID);
    }

    public Card getSingleCardByManualOrder(long parentFolderID, long manualOrderID)
    {
        return swipeRepository.getFirstCardByUserOrder(parentFolderID, manualOrderID);
    }


    public Folder getFolderWithID(long folderID) {
        return swipeRepository.getFolderWithID(folderID);
    }

    public void updateFolder(Folder folder) {
        swipeRepository.update(folder);
    }

    public void updateCard(Card card) {
        swipeRepository.update(card);
    }
}