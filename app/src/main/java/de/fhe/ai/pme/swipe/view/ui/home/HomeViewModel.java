package de.fhe.ai.pme.swipe.view.ui.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import de.fhe.ai.pme.swipe.model.Card;
import de.fhe.ai.pme.swipe.model.Folder;
import de.fhe.ai.pme.swipe.model.Page;
import de.fhe.ai.pme.swipe.storage.KeyValueStore;
import de.fhe.ai.pme.swipe.storage.SwipeRepository;

public class HomeViewModel extends AndroidViewModel {

    private final SwipeRepository swipeRepository;
    private final KeyValueStore keyValueStore;

    public HomeViewModel(Application application) {
        super(application);
        swipeRepository = SwipeRepository.getRepository(application);
        keyValueStore = new KeyValueStore(getApplication());
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

    public void deleteFolder(Folder folder) {

        if(folder.getContainsCards()) {
            List<Card> cardList = swipeRepository.getCardsActualValue(folder.getFolderID());
            for(Card c : cardList) {
                Page frontPage = swipeRepository.getPageByID(c.getFrontPageID());
                Page backPage = swipeRepository.getPageByID(c.getBackPageID());

                swipeRepository.delete(frontPage);
                swipeRepository.delete(backPage);
                swipeRepository.delete(c);
            }
        }
        else {
            List<Folder> folderList = swipeRepository.getFoldersActualValue(folder.getFolderID());
            for(Folder f : folderList) {
                deleteFolder(f);
            }
        }
        swipeRepository.delete(folder);

        List<Folder> folderListInParentFolder = swipeRepository.getFoldersActualValue(folder.getParentFolderID());
        long currentManualOrderID = folder.getManualOrderID();

        for(Folder pf : folderListInParentFolder) {
            if(pf.getManualOrderID() > currentManualOrderID) {
                long newManualOrder = pf.getManualOrderID() - 1;
                pf.setManualOrderID(newManualOrder);
                swipeRepository.update(pf);
            }
        }

        if(folder.getParentFolderID() != 0) {
            if(folderListInParentFolder.isEmpty()) {
                Folder parentFolder = swipeRepository.getFolderWithID(folder.getParentFolderID());
                parentFolder.setContainsFolders(false);
                keyValueStore.editValueBool("currentFolderContainsFolders", false);
            }
        }
        else {
            if (folderListInParentFolder.isEmpty()) {
                keyValueStore.editValueBool("currentFolderContainsFolders", false);
            }
        }
    }

    public void deleteCard(Card card) {

        List<Card> cardListInParentFolder = swipeRepository.getCardsActualValue(card.getParentFolderID());
        long currentManualOrderID = card.getManualOrderID();

        Page frontPage = swipeRepository.getPageByID(card.getFrontPageID());
        Page backPage = swipeRepository.getPageByID(card.getBackPageID());

        swipeRepository.delete(frontPage);
        swipeRepository.delete(backPage);
        swipeRepository.delete(card);

        for(Card pf : cardListInParentFolder) {
            if(pf.getManualOrderID() > currentManualOrderID) {
                long newManualOrder = pf.getManualOrderID() - 1;
                pf.setManualOrderID(newManualOrder);
                swipeRepository.update(pf);
            }
        }

        if(cardListInParentFolder.isEmpty()) {
            keyValueStore.editValueBool("currentFolderContainsCards", false);
        }
    }
}