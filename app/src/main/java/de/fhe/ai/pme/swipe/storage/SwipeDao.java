package de.fhe.ai.pme.swipe.storage;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import de.fhe.ai.pme.swipe.model.Card;
import de.fhe.ai.pme.swipe.model.Folder;
import de.fhe.ai.pme.swipe.model.Page;


/*
    Data Access Object definition for Models.
 */
@Dao
public interface SwipeDao {
    /*
        Basic insert-, update-, delete-methods
     */
    @Insert
    void insert(Folder... folders);

    @Update
    void update(Folder... folders);

    @Delete
    void delete(Folder... folders);

    @Insert
    void insert(Card... cards);

    @Update
    void update(Card... cards);

    @Delete
    void delete(Card... cards);

    @Insert
    void insert(Page... page);

    @Update
    void update(Page... page);

    @Delete
    void delete(Page... page);

    /*
        Select-statements for Folder
     */
    @Query("DELETE FROM Folder WHERE folderID LIKE :folderID OR parentFolderID LIKE :folderID")
    void deleteFolder(int folderID);

    @Query("SELECT count(*) FROM Folder")
    int count();

    @Query("SELECT * FROM Folder WHERE parentFolderID LIKE :folderID ORDER BY manualOrderID")
    LiveData<List<Folder>> getFoldersByUserOrder(int folderID);

    @Query("SELECT * FROM Folder WHERE parentFolderID LIKE :folderID ORDER BY name ASC")
    LiveData<List<Folder>> getFoldersByNameAsc(int folderID);

    @Query("SELECT * FROM Folder WHERE parentFolderID LIKE :folderID ORDER BY name DESC")
    LiveData<List<Folder>> getFoldersByNameDesc(int folderID);

    @Query("SELECT * FROM Folder WHERE parentFolderID LIKE :folderID ORDER BY modified ASC")
    LiveData<List<Folder>> getFoldersByUpdateAsc(int folderID);

    @Query("SELECT * FROM Folder WHERE parentFolderID LIKE :folderID ORDER BY modified DESC")
    LiveData<List<Folder>> getFoldersByUpdateDesc(int folderID);

    @Query("SELECT * FROM Folder WHERE parentFolderID LIKE :folderID ORDER BY color, modified ASC")
    LiveData<List<Folder>> getFoldersByColorAsc(int folderID);

    @Query("SELECT * FROM Folder WHERE parentFolderID LIKE :folderID ORDER BY color, modified DESC")
    LiveData<List<Folder>> getFoldersByColorDesc(int folderID);

    /*
        Select-statements for Card
     */
    @Query("SELECT * FROM Card WHERE parentFolderID LIKE :folderID ORDER BY manualOrderID")
    LiveData<List<Card>> getCardsByUserOrder(int folderID);

    @Query("SELECT * FROM Card WHERE parentFolderID LIKE :folderID ORDER BY modified ASC")
    LiveData<List<Card>> getCardsByUpdateAsc(int folderID);

    @Query("SELECT * FROM Card WHERE parentFolderID LIKE :folderID ORDER BY modified DESC")
    LiveData<List<Card>> getCardsByUpdateDesc(int folderID);

    /*
        Select-statements for Page
     */
    @Query("SELECT * FROM Page WHERE cardID LIKE :cardID AND isFrontpage = 1")
    LiveData<List<Page>> getFrontPage(int cardID);

    @Query("SELECT * FROM Page WHERE cardID LIKE :cardID AND isFrontpage = 0")
    LiveData<List<Page>> getBackPage(int cardID);
}

