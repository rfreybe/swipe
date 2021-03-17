package de.fhe.ai.pme.swipe.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

// Abstract class enables generic ViewHolder in HomeAdapter
public abstract class Item {
    @NonNull
    @ColumnInfo(name = "name")
    protected String name;

    public String getName() { return this.name; }
    public void setName(String name) {}
}
