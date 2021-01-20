package de.fhe.ai.pme.swipe.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.fhe.ai.pme.swipe.R;
import de.fhe.ai.pme.swipe.model.Folder;
import de.fhe.ai.pme.swipe.view.ui.foldersettings.FolderSettings;
import de.fhe.ai.pme.swipe.view.ui.statistics.Statistics;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);

    }

    public void ClickMenu(View view){
        //Opens drawer
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        //Opens drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
        //Close drawer
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //Closes drawer layout
        //Check condition
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            //When drawer is open
            //Close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }


    //PARENT FOLDER ID INCLUDE TODO
    public static void redirectActivity(Activity activity, Class aClass) {
        //Redirect activity
        //Initialize intent
        Intent intent = new Intent(activity,aClass);
        //Set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Start Activity
        activity.startActivity(intent);
    }

    //Redirects Fragment !!!!





    //Redirects Activity !!!!

    public void ClickFolder(View view){
        //Redirect Activity to Folder tab
        redirectActivity(this, MainActivity.class);
    }
    public void ClickFolderSettings(View view){
        //Redirect Activity to Settings tab
        redirectActivity(this, FolderSettings.class);
    }

    public void ClickStatistics(View view){
        //Redirect Activity to Statistics tab
        redirectActivity(this, Statistics.class);
    }

    @Override
    protected void onPause(){
        super.onPause();
        //Close drawer
        closeDrawer(drawerLayout);
    }


}