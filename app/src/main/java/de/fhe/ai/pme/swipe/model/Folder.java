package de.fhe.ai.pme.swipe.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Folder extends Item{
    public enum Color {
        GREY, BLUE, RED, GREEN, ORANGE, YELLOW
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "folderID")
    private long folderID;

    @NonNull
    @ColumnInfo(name = "manualOrderID")
    private long manualOrderID;

    @NonNull
    @ColumnInfo(name = "color")
    private Color color;

    @ForeignKey(entity = Folder.class, parentColumns = "parentFolderID", childColumns = "folderID")
    @ColumnInfo(name = "parentFolderID")
    private long parentFolderID;
    
    @ColumnInfo(name = "containsCards")
    private boolean containsCards;

    @ColumnInfo(name = "containsFolders")
    private boolean containsFolders;

    @NonNull
    @ColumnInfo(name = "created")
    private long created;

    @ColumnInfo(name = "modified")
    private long modified;

    public Folder() {}

    public Folder(@NonNull String name){
        this.manualOrderID = 0;
        this.parentFolderID = 0;
        this.name = name;
        this.color = Color.GREY;
        this.containsCards = false;
        this.containsFolders = false;
    }

    public Folder(@NonNull String name, long parentFolderID){
        this.manualOrderID = 0;
        this.parentFolderID = parentFolderID;
        this.name = name;
        this.color = Color.GREY;
        this.containsCards = false;
        this.containsFolders = false;
    }

    public long getFolderID() {
        return folderID;
    }

    public void setFolderID(long folderID) { this.folderID = folderID; }

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
    public Color getColor() {
        return color;
    }

    public void setColor(@NonNull Color color) {
        this.color = color;
    }

    public long getParentFolderID() {
        return parentFolderID;
    }

    public void setParentFolderID(long parentFolderID) {
        this.parentFolderID = parentFolderID;
    }
    
    public void setContainsCards(boolean containsCards) { this.containsCards = containsCards; }

    public boolean getContainsCards() { return containsCards; }

    public void setContainsFolders(boolean containsFolders) { this.containsFolders = containsFolders; }

    public boolean getContainsFolders() { return containsFolders; }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) { this.created = created; }

    public long getModified() {
        return modified;
    }

    public void setModified(long modified) {
        this.modified = modified;
    }

    public String toString() {
        return "Folder " + this.getName() + ", with ParentFolderID: " + this.getParentFolderID() +
                ", color: " + this.getColor().toString();
    }
}
