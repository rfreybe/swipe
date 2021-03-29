package de.fhe.ai.pme.swipe.storage;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
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
    void insert(Folder folders);

    @Update
    void update(Folder folders);

    @Delete
    void delete(Folder folders);

    @Insert
    void insert(Card cards);

    @Update
    void update(Card cards);

    @Delete
    void delete(Card cards);

    @Insert
    void insert(Page page);

    @Update
    void update(Page page);

    @Delete
    void delete(Page page);

    @Query("DELETE FROM Folder WHERE folderID LIKE :folderID OR parentFolderID LIKE :folderID")
    void deleteFolder(long folderID);

    /* Function Methods Folder */
    @Query("SELECT * FROM Folder WHERE parentFolderID LIKE :folderID ORDER BY manualOrderID")
    Folder getFirstFolder(long folderID);

    @Query("SELECT * FROM Folder WHERE parentFolderID LIKE :folderID AND manualOrderID LIKE :manualOrderID")
    Folder getFirstFolderByUserOrder(long folderID, long manualOrderID);

    @Query("SELECT * FROM Folder WHERE folderID LIKE :folderID ORDER BY manualOrderID")
    Folder getFolderWithID(long folderID);

    @Query("SELECT * FROM Folder WHERE parentFolderID LIKE :folderID ORDER BY manualOrderID")
    List<Folder> getFoldersActualValue(long folderID);

    /* Filter Methods Folder */
    @Query("SELECT * FROM Folder WHERE parentFolderID LIKE :folderID ORDER BY manualOrderID")
    LiveData<List<Folder>> getFoldersByUserOrder(long folderID);

    @Query("SELECT * FROM Folder WHERE parentFolderID LIKE :folderID ORDER BY name ASC")
    LiveData<List<Folder>> getFoldersByNameAsc(long folderID);

    @Query("SELECT * FROM Folder WHERE parentFolderID LIKE :folderID ORDER BY name DESC")
    LiveData<List<Folder>> getFoldersByNameDesc(long folderID);

    @Query("SELECT * FROM Folder WHERE parentFolderID LIKE :folderID ORDER BY modified ASC")
    LiveData<List<Folder>> getFoldersByUpdateAsc(long folderID);

    @Query("SELECT * FROM Folder WHERE parentFolderID LIKE :folderID ORDER BY modified DESC")
    LiveData<List<Folder>> getFoldersByUpdateDesc(long folderID);

    @Query("SELECT * FROM Folder WHERE parentFolderID LIKE :folderID ORDER BY color, modified ASC")
    LiveData<List<Folder>> getFoldersByColorAsc(long folderID);

    @Query("SELECT * FROM Folder WHERE parentFolderID LIKE :folderID ORDER BY color, modified DESC")
    LiveData<List<Folder>> getFoldersByColorDesc(long folderID);

    /*
        Function Methods Card
    */
    @Query("SELECT * FROM Card WHERE parentFolderID LIKE :folderID AND manualOrderID LIKE :manualOrderID")
    Card getFirstCardByUserOrder(long folderID, long manualOrderID);

     /*
        Select-statements for Card
     */
    @Query("SELECT * FROM Card WHERE parentFolderID LIKE :folderID ORDER BY manualOrderID")
    LiveData<List<Card>> getCardsByUserOrder(long folderID);

    @Query("SELECT * FROM Card WHERE parentFolderID LIKE :folderID ORDER BY name ASC")
    LiveData<List<Card>> getCardsByNameAsc(long folderID);

    @Query("SELECT * FROM Card WHERE parentFolderID LIKE :folderID ORDER BY name DESC")
    LiveData<List<Card>> getCardsByNameDesc(long folderID);

    @Query("SELECT * FROM Card WHERE parentFolderID LIKE :folderID ORDER BY modified ASC")
    LiveData<List<Card>> getCardsByUpdateAsc(long folderID);

    @Query("SELECT * FROM Card WHERE parentFolderID LIKE :folderID ORDER BY modified DESC")
    LiveData<List<Card>> getCardsByUpdateDesc(long folderID);

    /*
        Select-statements for Page
     */
    @Query("SELECT * FROM Page")
    List<Page> getAllPages();

    @Query("SELECT * FROM Page WHERE pageID LIKE :pageID")
    Page getPageByID(long pageID);
}

