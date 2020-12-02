package de.fhe.ai.pme.swipe.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Page {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pageID")
    private int pageID;

    @ForeignKey(entity = Card.class, parentColumns = "cardID", childColumns = "cardID")
    @NonNull
    @ColumnInfo(name = "cardID")
    private int cardID;

    @NonNull
    @ColumnInfo(name = "isFrontPage")
    private boolean isFrontPage;

    @ColumnInfo(name = "text")
    private String text;

    @ColumnInfo(name = "file")
    private String file;

    @NonNull
    @ColumnInfo(name = "created")
    private long created;

    @ColumnInfo(name = "modified")
    private long modified;

    public Page() {
    }

    public int getPageID() {
        return pageID;
    }

    public void setPageID(int pageID) {
        this.pageID = pageID;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public boolean isFrontPage() {
        return isFrontPage;
    }

    public void setFrontPage(boolean frontPage) {
        isFrontPage = frontPage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
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

