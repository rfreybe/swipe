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


        //TODO: Temporary
        /* Set this True to reset Test Data */
        boolean resetTestData = true;

//        if(resetTestData) {
//            List<Folder> folderList = repository.getFoldersActualValue(0);
//            if(folderList != null) {
//                for(Folder f : folderList) {
//                    repository.delete(f);
//                }
//            }
//        }

//        if(this.repository.getFirstFolder(0) == null) {
//            Folder OOP = new Folder("OOP");
//            OOP.setManualOrderID(0);
//            Folder GKP = new Folder("GKP");
//            GKP.setManualOrderID(1);
//            Folder SWT1 = new Folder("SWT1");
//            SWT1.setManualOrderID(2);
//            Folder THI = new Folder("THI");
//            THI.setManualOrderID(3);
//            Folder BVME = new Folder("BVME");
//            BVME.setManualOrderID(4);
//            Folder THI2 = new Folder("THI2");
//            THI2.setManualOrderID(5);
//            Folder MA1 = new Folder("MA1");
//            MA1.setManualOrderID(6);
//            Folder MA2 = new Folder("MA2");
//            MA2.setManualOrderID(7);
//            Folder ES1 = new Folder("ES1");
//            ES1.setManualOrderID(8);
//            Folder ES2 = new Folder("ES2");
//            ES2.setManualOrderID(9);
//
//            repository.insert( OOP );
//            repository.insert( GKP );
//            repository.insert( SWT1 );
//            repository.insert( THI );
//            repository.insert( BVME );
//            repository.insert( THI2 );
//            repository.insert( MA1 );
//            repository.insert( MA2 );
//            repository.insert( ES1 );
//            repository.insert( ES2 );
//
//            Folder Unterordner1;
//            Folder Unterordner2;
//            Folder Unterordner3;
//
//            for (int i = 1; i < 11; i++) {
//                Unterordner1 = new Folder("Unterordner1", i);
//                Unterordner1.setManualOrderID(0);
//                Unterordner2 = new Folder("Unterordner2", i);
//                Unterordner2.setManualOrderID(1);
//                Unterordner3 = new Folder("Unterordner3", i);
//                Unterordner3.setManualOrderID(2);
//
//                repository.insert(Unterordner1);
//                repository.insert(Unterordner2);
//                repository.insert(Unterordner3);
//            }
//        }

        // Apply theme
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean darkModeEnabled = sharedPreferences.getBoolean(Constants.PREF_DARK_MODE, false);
    }

}




