package de.fhe.ai.pme.swipe.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Card {

    @PrimaryKey
    @ColumnInfo(name = "cardID")
    private int cardID;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "rating")
    private Rating rating;

    @ForeignKey(entity = Folder.class, parentColumns = "folderID", childColumns = "folderID")
    @ColumnInfo(name = "folderID")
    private int folderID;

    @NonNull
    @ColumnInfo(name = "created")
    private long created;

    @ColumnInfo(name = "modified")
    private long modified;

    public Card(@NonNull String name){
        this.name = name;
        this.rating = Rating.red;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public int getFolderID() {
        return folderID;
    }

    public void setFolderID(int folderID) {
        this.folderID = folderID;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getModified() {
        return modified;
    }

    public void setModified(long modified) {
        this.modified = modified;
    }
}
