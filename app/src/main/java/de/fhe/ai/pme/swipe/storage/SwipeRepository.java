package de.fhe.ai.pme.swipe.storage;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import de.fhe.ai.pme.swipe.model.Card;
import de.fhe.ai.pme.swipe.model.Folder;
import de.fhe.ai.pme.swipe.model.Page;

public class SwipeRepository {

    public static final String LOG_TAG = "SwipeRepository";

    private SwipeDao swipeDao;

    public SwipeRepository(Context context) {
        SwipeDatabase db = SwipeDatabase.getDatabase( context );
        this.swipeDao = db.swipeDao();
    }


    // Repository Instance

    private static SwipeRepository INSTANCE;

    public static SwipeRepository getRepository( Application application )
    {
        if( INSTANCE == null ) {
            synchronized ( SwipeRepository.class ) {
                if( INSTANCE == null ) {
                    INSTANCE = new SwipeRepository( application );
                }
            }
        }

        return INSTANCE;
    }


    /*
        Query-methods for Database connection
     */
    private LiveData<List<Folder>> queryLiveDataFolder(Callable<LiveData<List<Folder>>> query )
    {
        try {
            return SwipeDatabase.executeWithReturn( query );
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return new MutableLiveData<>(Collections.emptyList());
    }

    private LiveData<List<Card>> queryLiveDataCard(Callable<LiveData<List<Card>>> query )
    {
        try {
            return SwipeDatabase.executeWithReturn( query );
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return new MutableLiveData<>(Collections.emptyList());
    }

    private LiveData<List<Page>> queryLiveDataPage(Callable<LiveData<List<Page>>> query )
    {
        try {
            return SwipeDatabase.executeWithReturn( query );
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return new MutableLiveData<>(Collections.emptyList());
    }

    /*
        Insert-, Update-methods
     */
    public void update(Folder folder) {
        folder.setModified( System.currentTimeMillis() );

        SwipeDatabase.execute( () -> swipeDao.update( prepareFolderForWriting(folder) ) );
    }

    public void update(Card card) {
        card.setModified( System.currentTimeMillis() );

        SwipeDatabase.execute( () -> swipeDao.update( prepareCardForWriting(card) ) );
    }

    public void update(Page page) {
        page.setModified( System.currentTimeMillis() );

        SwipeDatabase.execute( () -> swipeDao.update( this.preparePageForWriting(page) ) );
    }

    public void insert(Folder folder) {
        folder.setCreated( System.currentTimeMillis() );
        folder.setModified( folder.getCreated() );

        SwipeDatabase.execute( () -> swipeDao.insert( this.prepareFolderForWriting(folder) ) );
    }

    public void insert(Card card) {
        card.setCreated( System.currentTimeMillis() );
        card.setModified( card.getCreated() );

        SwipeDatabase.execute( () -> swipeDao.insert( this.prepareCardForWriting(card) ) );
    }

    public void insert(Page page) {
        page.setCreated( System.currentTimeMillis() );
        page.setModified( page.getCreated() );

        SwipeDatabase.execute( () -> swipeDao.insert( this.preparePageForWriting(page) ) );
    }

    private Folder prepareFolderForWriting( Folder folder ) {

        if( folder.getCreated() < 0 )
            folder.setCreated( System.currentTimeMillis() );

        folder.setModified( folder.getCreated() );

        return folder;
    }

    private Card prepareCardForWriting( Card card ) {

        if( card.getCreated() < 0 )
            card.setCreated( System.currentTimeMillis() );

        card.setModified( card.getCreated() );

        return card;
    }

    private Page preparePageForWriting( Page page ) {

        if( page.getCreated() < 0 )
            page.setCreated( System.currentTimeMillis() );

        page.setModified( page.getCreated() );

        return page;
    }


    /*
        Folder-methods
     */
    public LiveData<List<Folder>> getFoldersByUserOrder(int parentFolderID)
    {
        return this.queryLiveDataFolder( () -> this.swipeDao.getFoldersByUserOrder(parentFolderID) );
    }

    public LiveData<List<Folder>> getFoldersByNameAsc(int parentFolderID)
    {
        return this.queryLiveDataFolder( () -> this.swipeDao.getFoldersByNameAsc(parentFolderID) );
    }

    public LiveData<List<Folder>> getFoldersByNameDesc(int parentFolderID)
    {
        return this.queryLiveDataFolder( () -> this.swipeDao.getFoldersByNameDesc(parentFolderID) );
    }

    public LiveData<List<Folder>> getFoldersByUpdateAsc(int parentFolderID)
    {
        return this.queryLiveDataFolder( () -> this.swipeDao.getFoldersByUpdateAsc(parentFolderID) );
    }

    public LiveData<List<Folder>> getFoldersByUpdateDesc(int parentFolderID)
    {
        return this.queryLiveDataFolder( () -> this.swipeDao.getFoldersByUpdateDesc(parentFolderID) );
    }

    public LiveData<List<Folder>> getFoldersByColorAsc(int parentFolderID)
    {
        return this.queryLiveDataFolder( () -> this.swipeDao.getFoldersByColorAsc(parentFolderID) );
    }

    public LiveData<List<Folder>> getFoldersByColorDesc(int parentFolderID)
    {
        return this.queryLiveDataFolder( () -> this.swipeDao.getFoldersByColorDesc(parentFolderID) );
    }

    /*
        Card-methods
     */
    public LiveData<List<Card>> getCardsByUserOrder(int parentFolderID)
    {
        return this.queryLiveDataCard( () -> this.swipeDao.getCardsByUserOrder(parentFolderID) );
    }

    public LiveData<List<Card>> getCardsByUpdateAsc(int parentFolderID)
    {
        return this.queryLiveDataCard( () -> this.swipeDao.getCardsByUpdateAsc(parentFolderID) );
    }

    public LiveData<List<Card>> getCardsByUpdateDesc(int parentFolderID)
    {
        return this.queryLiveDataCard( () -> this.swipeDao.getCardsByUpdateDesc(parentFolderID) );
    }

//    public List<Card> shuffleCards(int parentFolderID)
//    {
//        List<Card> shuffledList = this.swipeDao.getCardsByUpdateAsc(parentFolderID);
//        Collections.shuffle(shuffledList);
//        return this.queryCard( () ->  shuffledList);
//    }


    /*
        Page-methods
     */
    public LiveData<List<Page>> getFrontPage(int cardID) {
        return this.queryLiveDataPage( () -> this.swipeDao.getFrontPage(cardID));
    }

    public LiveData<List<Page>> getBackPage(int cardID) {
        return this.queryLiveDataPage( () -> this.swipeDao.getBackPage(cardID));
    }
}
