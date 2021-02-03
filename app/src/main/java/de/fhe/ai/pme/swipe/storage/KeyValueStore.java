package de.fhe.ai.pme.swipe.storage;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class KeyValueStore {
    private static final String KEY_VALUE_STORE_FILE_NAME = "swipe_app_kv_store";
    private static final boolean DEFAULT_BOOLEAN_VALUE = true;

    private final Application app;

    public KeyValueStore(Application application) {this.app = application; }

    private SharedPreferences getPreferences()
    {
        return this.app.getSharedPreferences(KEY_VALUE_STORE_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void editValue(String key, boolean value)
    {
        this.getPreferences().edit().putBoolean(key, value).apply();
    }

    public boolean getValue(String key)
    {
        return this.getPreferences().getBoolean(key, DEFAULT_BOOLEAN_VALUE);
    }
}
