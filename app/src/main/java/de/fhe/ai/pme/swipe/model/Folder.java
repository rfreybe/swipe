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
    private int folderID;

    @NonNull
    @ColumnInfo(name = "manualOrderID")
    private int manualOrderID;

    @NonNull
    @ColumnInfo(name = "color")
    private Color color;

    @ForeignKey(entity = Folder.class, parentColumns = "parentFolderID", childColumns = "folderID")
    @ColumnInfo(name = "parentFolderID")
    private int parentFolderID;
    
    @ColumnInfo(name = "containsCards")
    private boolean containsCards;

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
    }

    public Folder(@NonNull String name, int parentFolderID){
        this.manualOrderID = 0;
        this.parentFolderID = parentFolderID;
        this.name = name;
        this.color = Color.GREY;
        this.containsCards = false;
    }

    public int getFolderID() {
        return folderID;
    }

    public void setFolderID(int folderID) { this.folderID = folderID; }

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

    @NonNull
    public Color getColor() {
        return color;
    }

    public void setColor(@NonNull Color color) {
        this.color = color;
    }

    public int getParentFolderID() {
        return parentFolderID;
    }

    public void setParentFolderID(int parentFolderID) {
        this.parentFolderID = parentFolderID;
    }
    
    public void setContainsCards(boolean containsCards) { this.containsCards = containsCards; }

    public boolean getContainsCards() { return containsCards; }

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
