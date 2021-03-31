package de.fhe.ai.pme.swipe.storage;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class KeyValueStore {
    private static final String KEY_VALUE_STORE_FILE_NAME = "swipe_app_kv_store";
    private static final boolean DEFAULT_BOOLEAN_VALUE = false;
    private static final long DEFAULT_LONG_VALUE = 0;
    private static final int DEFAULT_INT_VALUE = 0;

    private final Application app;

    public KeyValueStore(Application application) {this.app = application; }

    private SharedPreferences getPreferences()
    {
        return this.app.getSharedPreferences(KEY_VALUE_STORE_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void editValueBool(String key, boolean value)
    {
        this.getPreferences().edit().putBoolean(key, value).apply();
    }

    public void editValueLong(String key, long value)
    {
        this.getPreferences().edit().putLong(key, value).apply();
    }

    public void editValueInt(String key, int value)
    {
        this.getPreferences().edit().putInt(key, value).apply();
    }

    public boolean getValueBool(String key)
    {
        return this.getPreferences().getBoolean(key, DEFAULT_BOOLEAN_VALUE);
    }

    public long getValueLong(String key) {
        return this.getPreferences().getLong(key, DEFAULT_LONG_VALUE);
    }

    public int getValueInt(String key) {
        return this.getPreferences().getInt(key, DEFAULT_INT_VALUE);
    }

}
