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

    private List<Folder> queryFolders(Callable<List<Folder>> query )
    {
        try {
            return SwipeDatabase.executeWithReturn( query );
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return new List<Folder>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(@Nullable Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<Folder> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] a) {
                return null;
            }

            @Override
            public boolean add(Folder folder) {
                return false;
            }

            @Override
            public boolean remove(@Nullable Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends Folder> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, @NonNull Collection<? extends Folder> c) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Folder get(int index) {
                return null;
            }

            @Override
            public Folder set(int index, Folder element) {
                return null;
            }

            @Override
            public void add(int index, Folder element) {

            }

            @Override
            public Folder remove(int index) {
                return null;
            }

            @Override
            public int indexOf(@Nullable Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(@Nullable Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<Folder> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<Folder> listIterator(int index) {
                return null;
            }

            @NonNull
            @Override
            public List<Folder> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
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

    public void insert(Page page) {
        SwipeDatabase.execute( () -> swipeDao.insert( this.preparePageForWriting(page) ) );
    }

    public void delete(Folder folder) {
        int folderID = folder.getFolderID();
        if(swipeDao.getFirstFolder(folderID) != null) {
            List<Folder> folders = swipeDao.getFoldersByUserOrder(folderID).getValue();
            for(Folder f : folders) {
                swipeDao.delete(f);
            }
        }
    }

    private Folder prepareFolderForWriting( Folder folder ) {

        if( folder.getCreated() < 0 )
            folder.setCreated( System.currentTimeMillis() );

        folder.setModified( System.currentTimeMillis() );

        return folder;
    }

    private Card prepareCardForWriting( Card card ) {

        if( card.getCreated() < 0 )
            card.setCreated( System.currentTimeMillis() );

        card.setModified( System.currentTimeMillis() );

        return card;
    }

    private Page preparePageForWriting( Page page ) {

        if( page.getCreated() < 0 )
            page.setCreated( System.currentTimeMillis() );

        page.setModified( System.currentTimeMillis() );

        return page;
    }


    /*
        Folder-methods
     */
    public Folder getFirstFolder(int folderID)
    {
        return this.querySingleFolder( () -> this.swipeDao.getFirstFolder(folderID));
    }

    public List<Folder> getFolders(int folderID)
    {
        return this.queryFolders( () -> this.swipeDao.getFolders(folderID));
    }

    public Folder getFirstFolderByUserOrder(int parentFolderID, int manualOrderID)
    {
        return this.querySingleFolder( () -> this.swipeDao.getFirstFolderByUserOrder(parentFolderID, manualOrderID));
    }

    public LiveData<List<Folder>> getFoldersByUserOrder(int parentFolderID)
    {
        return this.queryLiveData( () -> this.swipeDao.getFoldersByUserOrder(parentFolderID) );
    }

    public LiveData<List<Folder>> getFoldersByNameAsc(int parentFolderID)
    {
        return this.queryLiveData( () -> this.swipeDao.getFoldersByNameAsc(parentFolderID) );
    }

    public LiveData<List<Folder>> getFoldersByNameDesc(int parentFolderID)
    {
        return this.queryLiveData( () -> this.swipeDao.getFoldersByNameDesc(parentFolderID) );
    }

    public LiveData<List<Folder>> getFoldersByUpdateAsc(int parentFolderID)
    {
        return this.queryLiveData( () -> this.swipeDao.getFoldersByUpdateAsc(parentFolderID) );
    }

    public LiveData<List<Folder>> getFoldersByUpdateDesc(int parentFolderID)
    {
        return this.queryLiveData( () -> this.swipeDao.getFoldersByUpdateDesc(parentFolderID) );
    }

    public LiveData<List<Folder>> getFoldersByColorAsc(int parentFolderID)
    {
        return this.queryLiveData( () -> this.swipeDao.getFoldersByColorAsc(parentFolderID) );
    }

    public LiveData<List<Folder>> getFoldersByColorDesc(int parentFolderID)
    {
        return this.queryLiveData( () -> this.swipeDao.getFoldersByColorDesc(parentFolderID) );
    }

    /*
        Card-methods
     */
    public LiveData<List<Card>> getCardsByUserOrder(int parentFolderID)
    {
        return this.queryLiveData( () -> this.swipeDao.getCardsByUserOrder(parentFolderID) );
    }

    public LiveData<List<Card>> getCardsByUpdateAsc(int parentFolderID)
    {
        return this.queryLiveData( () -> this.swipeDao.getCardsByUpdateAsc(parentFolderID) );
    }

    public LiveData<List<Card>> getCardsByUpdateDesc(int parentFolderID)
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
    public LiveData<List<Page>> getFrontPage(int cardID) {
        return this.queryLiveData( () -> this.swipeDao.getFrontPage(cardID));
    }

    public LiveData<List<Page>> getBackPage(int cardID) {
        return this.queryLiveData( () -> this.swipeDao.getBackPage(cardID));
    }
}
