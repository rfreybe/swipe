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
    private long cardID;

    @NonNull
    @ColumnInfo(name = "manualOrderID")
    private long manualOrderID;

    @NonNull
    @ColumnInfo(name = "frontPage")
    private long frontPageID;

    @NonNull
    @ColumnInfo(name = "backPage")
    private long backPageID;

    @ColumnInfo(name = "rating")
    private Rating rating;

    @NonNull
    @ColumnInfo(name = "parentFolderID")
    private long parentFolderID;

    @NonNull
    @ColumnInfo(name = "created")
    private long created;

    @ColumnInfo(name = "modified")
    private long modified;

    public Card(@NonNull String name, @NonNull long parentFolderID, @NonNull long frontPageID, @NonNull long backPageID){
        this.frontPageID = frontPageID;
        this.backPageID = backPageID;
        this.manualOrderID = this.cardID;
        this.parentFolderID = parentFolderID;
        this.name = name;
        this.rating = Rating.RED;
    }

    public long getCardID() {
        return cardID;
    }

    public void setCardID(long cardID) { this.cardID = cardID; }

    public long getManualOrderID() { return manualOrderID; }

    public void setManualOrderID(long manualOrderID ) {
        this.manualOrderID = manualOrderID;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public long getFrontPageID() { return this.frontPageID; }

    @NonNull
    public long getBackPageID() { return this.backPageID; }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public long getParentFolderID() { return parentFolderID; }

    public void setParentFolderID(long parentFolderID) {
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