package de.fhe.ai.pme.swipe.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.swipe.R;

public class Folder extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);
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
        //recreate activity
        recreate();
    }

    public void ClickAddButton(View view){
        //checks if you are in a folder if non folder exists you cant create a card
        //add folder or add card
        //Creates a Folder
        MainActivity.redirectActivity(this,CreateFolderOrCard.class);
    }


//    public void ClickSettings(View view){
//        //Redirect activity Settings
//        MainActivity.redirectActivity(this,FolderSettings.class);
//    }

    public void ClickFolderSettings(View view){
        //Redirect activity to Folder Settings
        MainActivity.redirectActivity(this,FolderSettings.class);
    }

    public void ClickStatistics(View view){
        //Redirect activity to Folder Settings
        MainActivity.redirectActivity(this,Statistics.class);
    }

    protected void onPause(){
        super.onPause();
        //Close drawer
        MainActivity.closeDrawer(drawerLayout);
    }

 }