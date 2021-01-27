package de.fhe.ai.pme.swipe.core;

import android.app.Application;
import android.preference.SwitchPreference;

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

//        SwipeRepository repository = new SwipeRepository(this);
//
//        repository.insert( new Folder("OOP"));
//        repository.insert( new Folder("GKP"));
//        repository.insert( new Folder("SWT1"));
//        repository.insert( new Folder("THI"));
//        repository.insert( new Folder("BVME"));
//        repository.insert( new Folder("THI2"));
//        repository.insert( new Folder("MA1"));
//        repository.insert( new Folder("MA2"));
//        repository.insert( new Folder("ES1"));
//        repository.insert( new Folder("ES2"));
//        repository.insert( new Folder("SWT2"));
//        repository.insert( new Folder("DB1"));
//        repository.insert( new Folder("DB2"));
//        repository.insert( new Folder("ALG"));
//        repository.insert( new Folder("Graphentheorie"));
//        repository.insert( new Folder("Java"));
//        repository.insert( new Folder("Webentwicklung"));
    }

    public KeyValueStore getStore() {
        if (this.store == null ) {
            this.store = new KeyValueStore(this);
        }
        return this.store;
    }
}


