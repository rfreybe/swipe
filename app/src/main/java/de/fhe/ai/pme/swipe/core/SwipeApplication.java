package de.fhe.ai.pme.swipe.core;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.provider.SyncStateContract;

import com.github.javafaker.Faker;

import java.security.Key;

import de.fhe.ai.pme.swipe.model.Folder;
import de.fhe.ai.pme.swipe.storage.KeyValueStore;
import de.fhe.ai.pme.swipe.storage.SwipeRepository;

public class SwipeApplication extends Application {
    private KeyValueStore store;

    @Override
    public void onCreate() {
        super.onCreate();

        //apply theme
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean darkModeEnabled = sharedPreferences.getBoolean(Constants.PREF_DARK_MODE, false);
        SwipeRepository repository = new SwipeRepository(this);

        Faker faker = new Faker();

        repository.insert( new Folder(faker.chuckNorris().fact()));
        repository.insert( new Folder(faker.chuckNorris().fact(), 1));
    }

    public KeyValueStore getStore() {
        if (this.store == null ) {
            this.store = new KeyValueStore(this);
        }
        return this.store;
    }
}


