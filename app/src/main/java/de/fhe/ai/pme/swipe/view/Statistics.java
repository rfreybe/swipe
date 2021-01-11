package de.fhe.ai.pme.swipe.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

import de.fhe.ai.pme.swipe.R;



public class Statistics extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        // Assign Variable

        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void ClickMenu(View view){
        //Opens drawer
        MainActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        //Closes the drawer
        MainActivity.closeDrawer(drawerLayout);
    }

    public void ClickFolder(View view){
        //Redirect activity to Folder Settings
        MainActivity.redirectActivity(this,Folder.class);
    }

//    public void ClickSettings(View view){
//        //Redirect activity Settings
//        MainActivity.redirectActivity(this,Settings.class);
//    }

    public void ClickFolderSettings(View view){
        //Redirect activity to Folder Settings
        MainActivity.redirectActivity(this,FolderSettings.class);
    }

    public void ClickStatistics(View view){
        //recreate activity
        recreate();
    }

    protected void onPause(){
        super.onPause();
        //Close drawer
        MainActivity.closeDrawer(drawerLayout);
    }

}