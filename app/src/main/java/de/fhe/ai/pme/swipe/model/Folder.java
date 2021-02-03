package de.fhe.ai.pme.swipe.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Folder {
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
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "color")
    private Color color;

    @ForeignKey(entity = Folder.class, parentColumns = "parentFolderID", childColumns = "folderID")
    @ColumnInfo(name = "parentFolderID")
    private int parentFolderID;

    @NonNull
    @ColumnInfo(name = "created")
    private long created;

    @ColumnInfo(name = "modified")
    private long modified;

    @Ignore
    public Folder(@NonNull String name){
        this.manualOrderID = this.folderID;
        this.name = name;
        this.color = Color.GREY;
    }

    public Folder(@NonNull String name, int parentFolderID){
        this.manualOrderID = this.folderID;
        this.parentFolderID = parentFolderID;
        this.name = name;
        this.color = Color.GREY;
        this.folderID++;
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
}
