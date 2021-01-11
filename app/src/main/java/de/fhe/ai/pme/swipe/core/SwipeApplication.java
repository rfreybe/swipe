package de.fhe.ai.pme.swipe.core;

import android.app.Application;
import android.preference.SwitchPreference;

import com.github.javafaker.Faker;

import de.fhe.ai.pme.swipe.model.Folder;
import de.fhe.ai.pme.swipe.storage.SwipeRepository;


public class SwipeApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SwipeRepository repository = new SwipeRepository(this);

        Faker faker = new Faker();

        repository.insert( new Folder(faker.chuckNorris().fact()));
        repository.insert( new Folder(faker.chuckNorris().fact(), 1));
    }
}
