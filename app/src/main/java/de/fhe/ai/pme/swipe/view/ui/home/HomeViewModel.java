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
        this.swipeRepository = SwipeRepository.getRepository(application);
    }

    // Switch Case decides which Repository Method to use according to filter
    public LiveData<List<Folder>> getFolders(int parentFolderID, int filter)
    {
        switch(filter){
            default:
                return this.swipeRepository.getFoldersByUserOrder(parentFolderID);
            case 1:
                return this.swipeRepository.getFoldersByNameAsc(parentFolderID);
            case 2:
                return this.swipeRepository.getFoldersByNameDesc(parentFolderID);
            case 3:
                return this.swipeRepository.getFoldersByColorAsc(parentFolderID);
            case 4:
                return this.swipeRepository.getFoldersByColorDesc(parentFolderID);
            case 5:
                return this.swipeRepository.getFoldersByUpdateAsc(parentFolderID);
            case 6:
                return this.swipeRepository.getFoldersByUpdateDesc(parentFolderID);
        }
    }

    public LiveData<List<Card>> getCards(int parentFolderID, int filter)
    {
        switch(filter){
            default:
                return this.swipeRepository.getCardsByUserOrder(parentFolderID);
            case 1:
                return this.swipeRepository.getCardsByNameAsc(parentFolderID);
            case 2:
                return this.swipeRepository.getCardsByNameDesc(parentFolderID);
            case 3:
                return this.swipeRepository.getCardsByUpdateAsc(parentFolderID);
            case 4:
                return this.swipeRepository.getCardsByUpdateDesc(parentFolderID);
        }
    }

    // Methods for Drag & Drop function
    public Folder getSingleFolderByManualOrder(int parentFolderID, int manualOrderID)
    {
        return this.swipeRepository.getFirstFolderByUserOrder(parentFolderID, manualOrderID);
    }

    public Card getSingleCardByManualOrder(int parentFolderID, int manualOrderID)
    {
        return this.swipeRepository.getFirstCardByUserOrder(parentFolderID, manualOrderID);
    }


    public Folder getFolderWithID(int folderID) {
        return this.swipeRepository.getFolderWithID(folderID);
    }

    public void updateFolder(Folder folder) {
        this.swipeRepository.update(folder);
    }

    public void updateCard(Card card) {
        this.swipeRepository.update(card);
    }
}