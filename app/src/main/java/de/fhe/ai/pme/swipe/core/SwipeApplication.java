package de.fhe.ai.pme.swipe.core;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.provider.SyncStateContract;

import com.github.javafaker.Faker;

import org.apache.commons.lang3.mutable.Mutable;

import java.security.Key;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import de.fhe.ai.pme.swipe.model.Folder;
import de.fhe.ai.pme.swipe.storage.KeyValueStore;
import de.fhe.ai.pme.swipe.storage.SwipeRepository;

public class SwipeApplication extends Application {
    private KeyValueStore store;
    private SwipeRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        this.repository = new SwipeRepository(this);

        // Apply theme
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean darkModeEnabled = sharedPreferences.getBoolean(Constants.PREF_DARK_MODE, false);
    }

}




