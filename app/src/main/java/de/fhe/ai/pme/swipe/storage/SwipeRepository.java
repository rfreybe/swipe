package de.fhe.ai.pme.swipe.storage;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import de.fhe.ai.pme.swipe.model.Card;
import de.fhe.ai.pme.swipe.model.Folder;

public class SwipeRepository {

    public static final String LOG_TAG = "SwipeRepository";

    private SwipeDao swipeDao;

    public SwipeRepository(Context context) {
        SwipeDatabase db = SwipeDatabase.getDatabase( context );
        this.swipeDao = db.swipeDao();
    }

    /*
        Query-methods for Database connection
     */
    private List<Folder> queryFolder( Callable<List<Folder>> query )
    {
        try {
            return SwipeDatabase.query( query );
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    private List<Card> queryCard(Callable<List<Card>> query )
    {
        try {
            return SwipeDatabase.query( query );
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    /*
        Insert-, Update-methods
     */
    public void update(Folder folder) {
        folder.setModified( System.currentTimeMillis() );

        SwipeDatabase.execute( () -> swipeDao.update( folder ) );
    }

    public void update(Card card) {
        card.setModified( System.currentTimeMillis() );

        SwipeDatabase.execute( () -> swipeDao.update( card ) );
    }

    public void insert(Folder folder) {
        folder.setCreated( System.currentTimeMillis() );
        folder.setModified( folder.getCreated() );

        SwipeDatabase.execute( () -> swipeDao.insert( folder ) );
    }

    public void insert(Card card) {
        card.setCreated( System.currentTimeMillis() );
        card.setModified( card.getCreated() );

        SwipeDatabase.execute( () -> swipeDao.insert( card ) );
    }

    /*
        Folder-methods
     */
    public List<Folder> getFoldersByUserOrder(int parentFolderID)
    {
        return this.queryFolder( () -> this.swipeDao.getFoldersByUserOrder(parentFolderID) );
    }

    public List<Folder> getFoldersByNameAsc(int parentFolderID)
    {
        return this.queryFolder( () -> this.swipeDao.getFoldersByNameAsc(parentFolderID) );
    }

    public List<Folder> getFoldersByNameDesc(int parentFolderID)
    {
        return this.queryFolder( () -> this.swipeDao.getFoldersByNameDesc(parentFolderID) );
    }

    public List<Folder> getFoldersByUpdateAsc(int parentFolderID)
    {
        return this.queryFolder( () -> this.swipeDao.getFoldersByUpdateAsc(parentFolderID) );
    }

    public List<Folder> getFoldersByUpdateDesc(int parentFolderID)
    {
        return this.queryFolder( () -> this.swipeDao.getFoldersByUpdateDesc(parentFolderID) );
    }

    public List<Folder> getFoldersByColorAsc(int parentFolderID)
    {
        return this.queryFolder( () -> this.swipeDao.getFoldersByColorAsc(parentFolderID) );
    }

    public List<Folder> getFoldersByColorDesc(int parentFolderID)
    {
        return this.queryFolder( () -> this.swipeDao.getFoldersByColorDesc(parentFolderID) );
    }

    /*
        Card-methods
     */
    public List<Card> getCardsByUserOrder(int parentFolderID)
    {
        return this.queryCard( () -> this.swipeDao.getCardsByUserOrder(parentFolderID) );
    }

    public List<Card> getCardsByUpdateAsc(int parentFolderID)
    {
        return this.queryCard( () -> this.swipeDao.getCardsByUpdateAsc(parentFolderID) );
    }

    public List<Card> getCardsByUpdateDesc(int parentFolderID)
    {
        return this.queryCard( () -> this.swipeDao.getCardsByUpdateDesc(parentFolderID) );
    }

    public List<Card> shuffleCards(int parentFolderID)
    {
        List<Card> shuffledList = this.swipeDao.getCardsByUpdateAsc(parentFolderID);
        Collections.shuffle(shuffledList);
        return this.queryCard( () ->  shuffledList);
    }
}
