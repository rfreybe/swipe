package de.fhe.ai.pme.swipe.view.ui.home.configuration.card;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import de.fhe.ai.pme.swipe.model.Card;
import de.fhe.ai.pme.swipe.model.Folder;
import de.fhe.ai.pme.swipe.model.Page;
import de.fhe.ai.pme.swipe.storage.SwipeRepository;

public class  CardConfigurationViewModel extends AndroidViewModel {

    private final SwipeRepository swipeRepository;

    public CardConfigurationViewModel(Application application)
    {
        super(application);
        this.swipeRepository = SwipeRepository.getRepository(application);
    }

    public void saveCard(Card card)
    {
        this.swipeRepository.insert(card);

        // If not already, set containsCards for Parent Folder
        long parentFolderID = card.getParentFolderID();
        if(parentFolderID != 0) {
            Folder parentFolder = swipeRepository.getFolderWithID(parentFolderID);
            parentFolder.setContainsCards(true);
            swipeRepository.update(parentFolder);
        }
    }

    public void savePage(Page page) {
        this.swipeRepository.insert(page);
    }

    public long getLastCreatedPageID() {
        List<Page> pageList = this.swipeRepository.getPages();
        int pageListSize = pageList.size();
        long pageID = pageList.get(pageListSize - 1).getPageID();
        return pageID;
    }
}

