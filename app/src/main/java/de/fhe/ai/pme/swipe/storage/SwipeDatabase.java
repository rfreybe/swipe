package de.fhe.ai.pme.swipe.storage;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.github.javafaker.Faker;

import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.fhe.ai.pme.swipe.model.Card;
import de.fhe.ai.pme.swipe.model.Folder;
import de.fhe.ai.pme.swipe.model.Page;


/*
    Database Management Class

    List of classes represent entities (-> models)
    !Changes in the database must result in a higher version number!
 */
@Database( entities = {Folder.class, Card.class, Page.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class SwipeDatabase extends RoomDatabase {

    private static final String LOG_TAG_DB = "SwipeDB";

    /*
        DAO reference, will be filled by Android
    */
    public abstract SwipeDao swipeDao();

    /*
    Executor service to perform database operations asynchronous and independent from UI thread
 */
    private static final int NUMBER_OF_THREADS = 4;
    private static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool( NUMBER_OF_THREADS );

    /*
        Singleton Instance
     */
    private static volatile SwipeDatabase INSTANCE;

    /*
    Helper methods to ease external usage of ExecutorService
    e.g. perform async database operations
 */
    public static <T> T executeWithReturn(Callable<T> task)
            throws ExecutionException, InterruptedException
    {
        return databaseWriteExecutor.invokeAny( Collections.singletonList( task ) );
    }

    public static void execute( Runnable runnable )
    {
        databaseWriteExecutor.execute( runnable );
    }

    /*
    Singleton 'getInstance' method to create database instance thereby opening and, if not
    already done, init the database. Note the 'createCallback'.
 */
    static SwipeDatabase getDatabase(final Context context) {
        Log.i( LOG_TAG_DB, "getDatabase() called" );
        if (INSTANCE == null) {
            synchronized (SwipeDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SwipeDatabase.class, "swipe_db")
                            //.addCallback(createCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback createCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            Log.i( LOG_TAG_DB, "onCreate() called" );
        }
    };


}
