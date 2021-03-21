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
    private long pageID;

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

    @NonNull
    @ColumnInfo(name = "modified")
    private long modified;

    public Page() {}

    public Page(@NonNull boolean isFrontPage) {
        this.isFrontPage = isFrontPage;
    }

    public long getPageID() {
        return pageID;
    }

    public void setPageID(long pageID) { this.pageID = pageID; }

    public void setIsFrontPage(boolean isFrontPage) { this.isFrontPage = isFrontPage; }

    public boolean isFrontPage() {
        return isFrontPage;
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

    public void setCreated(long created) { this.created = created; }

    public long getModified() {
        return modified;
    }

    public void setModified(long modified) { this.modified = modified; }
}

