package de.fhe.ai.pme.swipe.storage;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import de.fhe.ai.pme.swipe.model.Card;
import de.fhe.ai.pme.swipe.model.Folder;
import de.fhe.ai.pme.swipe.model.Page;

//TODO: Make repetitive functions generic
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
    private <T> LiveData<T> queryLiveData( Callable<LiveData<T>> query )
    {
        try {
            return SwipeDatabase.executeWithReturn( query );
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return new MutableLiveData<>();
    }

    private List<Folder> queryFolderList(Callable<List<Folder>> query) {
        try {
            return SwipeDatabase.executeWithReturn( query );
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<Card> queryCardList(Callable<List<Card>> query) {
        try {
            return SwipeDatabase.executeWithReturn( query );
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<Page> queryPageList(Callable<List<Page>> query) {
        try {
            return SwipeDatabase.executeWithReturn( query );
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Folder querySingleFolder(Callable<Folder> query )
    {
        try {
            return SwipeDatabase.executeWithReturn( query );
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return new Folder();
    }

    private Card querySingleCard(Callable<Card> query )
    {
        try {
            return SwipeDatabase.executeWithReturn( query );
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Page querySinglePage(Callable<Page> query )
    {
        try {
            return SwipeDatabase.executeWithReturn( query );
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return new Page();
    }

    /*
        Insert-, Update-methods
     */
    public void update(Folder folder) {
        SwipeDatabase.execute( () -> swipeDao.update( prepareFolderForWriting(folder) ) );
    }

    public void update(Card card) {
        SwipeDatabase.execute( () -> swipeDao.update( prepareCardForWriting(card) ) );
    }

    public void update(Page page) {
        SwipeDatabase.execute( () -> swipeDao.update( this.preparePageForWriting(page) ) );
    }

    public void insert(Folder folder) {
        SwipeDatabase.execute( () -> swipeDao.insert( this.prepareFolderForWriting(folder) ) );
    }

    public void insert(Card card) {
        SwipeDatabase.execute( () -> swipeDao.insert( this.prepareCardForWriting(card) ) );
    }

    //TODO: this shit is weird
    public void insert(Page page) {
        SwipeDatabase.execute( () -> this.swipeDao.insert( this.preparePageForWriting(page) ) );
    }

    public void delete(Folder folder) {
        SwipeDatabase.execute( () -> swipeDao.delete(folder));
    }

    public void delete(Card card) {
        SwipeDatabase.execute( () -> swipeDao.delete(card));
    }

    public void delete(Page page) {
        SwipeDatabase.execute( () -> swipeDao.delete(page));
    }

    private Folder prepareFolderForWriting( Folder folder ) {

        if( folder.getCreated() == 0 )
            folder.setCreated( System.currentTimeMillis() );

        folder.setModified( System.currentTimeMillis() );

        return folder;
    }

    private Card prepareCardForWriting( Card card ) {

        if( card.getCreated() == 0 )
            card.setCreated( System.currentTimeMillis() );

        card.setModified( System.currentTimeMillis() );

        return card;
    }

    private Page preparePageForWriting( Page page ) {

        if( page.getCreated() == 0 )
            page.setCreated( System.currentTimeMillis() );

        page.setModified( System.currentTimeMillis() );

        return page;
    }


    /*
        Folder-methods
     */
    public Folder getFirstFolder(long folderID)
    {
        return this.querySingleFolder( () -> this.swipeDao.getFirstFolder(folderID));
    }

    public Folder getFirstFolderByUserOrder(long parentFolderID, long manualOrderID)
    {
        return this.querySingleFolder( () -> this.swipeDao.getFirstFolderByUserOrder(parentFolderID, manualOrderID));
    }

    public Folder getFolderWithID(long folderID) {
        return this.querySingleFolder( () -> this.swipeDao.getFolderWithID(folderID));
    }

    public List<Folder> getFoldersActualValue(long parentFolderID) {
        return this.queryFolderList( () -> this.swipeDao.getFoldersActualValue(parentFolderID));
    }

    public LiveData<List<Folder>> getFoldersByUserOrder(long parentFolderID)
    {
        return this.queryLiveData( () -> this.swipeDao.getFoldersByUserOrder(parentFolderID) );
    }

    public LiveData<List<Folder>> getFoldersByNameAsc(long parentFolderID)
    {
        return this.queryLiveData( () -> this.swipeDao.getFoldersByNameAsc(parentFolderID) );
    }

    public LiveData<List<Folder>> getFoldersByNameDesc(long parentFolderID)
    {
        return this.queryLiveData( () -> this.swipeDao.getFoldersByNameDesc(parentFolderID) );
    }

    public LiveData<List<Folder>> getFoldersByUpdateAsc(long parentFolderID)
    {
        return this.queryLiveData( () -> this.swipeDao.getFoldersByUpdateAsc(parentFolderID) );
    }

    public LiveData<List<Folder>> getFoldersByUpdateDesc(long parentFolderID)
    {
        return this.queryLiveData( () -> this.swipeDao.getFoldersByUpdateDesc(parentFolderID) );
    }

    /*
        Card-methods
     */
    public Card getCardByCardID(long cardID) {
        return this.querySingleCard( () -> this.swipeDao.getCardByCardID(cardID));
    }

    public Card getFirstCardByUserOrder(long parentFolderID, long manualOrderID)
    {
        return this.querySingleCard( () -> this.swipeDao.getFirstCardByUserOrder(parentFolderID, manualOrderID));
    }

    public List<Card> getCardsActualValue(long parentFolderID) {
        return this.queryCardList( () -> this.swipeDao.getCardsActualValue(parentFolderID));
    }

    public LiveData<List<Card>> getCardsByUserOrder(long parentFolderID)
    {
        return this.queryLiveData( () -> this.swipeDao.getCardsByUserOrder(parentFolderID) );
    }

    public LiveData<List<Card>> getCardsByNameAsc(long parentFolderID)
    {
        return this.queryLiveData( () -> this.swipeDao.getCardsByUpdateAsc(parentFolderID) );
    }

    public LiveData<List<Card>> getCardsByNameDesc(long parentFolderID)
    {
        return this.queryLiveData( () -> this.swipeDao.getCardsByNameDesc(parentFolderID) );
    }

    public LiveData<List<Card>> getCardsByUpdateAsc(long parentFolderID)
    {
        return this.queryLiveData( () -> this.swipeDao.getCardsByNameAsc(parentFolderID) );
    }

    public LiveData<List<Card>> getCardsByUpdateDesc(long parentFolderID)
    {
        return this.queryLiveData( () -> this.swipeDao.getCardsByUpdateDesc(parentFolderID) );
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
    public List<Page> getPages() {
        return this.queryPageList( () -> this.swipeDao.getPagesActualValue());
    }

    public Page getPageByID(long pageID) {
        return this.querySinglePage( () -> this.swipeDao.getPageByID(pageID));
    }
}
