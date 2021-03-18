package de.fhe.ai.pme.swipe.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Card extends Item{
    public enum Rating {
        GREEN, YELLOW, RED
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cardID")
    private int cardID;

    @NonNull
    @ColumnInfo(name = "manualOrderID")
    private int manualOrderID;

    @ColumnInfo(name = "rating")
    private Rating rating;

    @NonNull
    @ForeignKey(entity = Folder.class, parentColumns = "parentFolderID", childColumns = "parentFolderID")
    @ColumnInfo(name = "parentFolderID")
    private int parentFolderID;

    @NonNull
    @ColumnInfo(name = "created")
    private long created;

    @ColumnInfo(name = "modified")
    private long modified;

    public Card(){}

    public Card(@NonNull String name, @NonNull int parentFolderID){
        this.manualOrderID = this.cardID;
        this.parentFolderID = parentFolderID;
        this.name = name;
        this.rating = Rating.RED;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) { this.cardID = cardID; }

    public int getManualOrderID() { return manualOrderID; }

    public void setManualOrderID(int manualOrderID ) {
        this.manualOrderID = manualOrderID;
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

    public int getParentFolderID() { return parentFolderID; }

    public void setParentFolderID(int parentFolderID) {
        this.parentFolderID = parentFolderID;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) { this.created = created; }

    public long getModified() {
        return modified;
    }

    public void setModified(long modified) { this.modified = modified; }
}

//TODO created