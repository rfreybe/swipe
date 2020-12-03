package de.fhe.ai.pme.swipe.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

import com.example.swipe.R;


public class FolderSettings extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_settings);
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
        //Redirect activity to home
        MainActivity.redirectActivity(this,MainActivity.class);
    }

//    public void ClickSettings(View view){
//        //recreate activity
//        recreate();
//    }

    public void ClickFolderSettings(View view){
        //recreate activity
        recreate();
    }

    public void ClickStatistics(View view){
        //Redirect activity to Statistics
        MainActivity.redirectActivity(this,Statistics.class);
    }

    protected void onPause(){
        super.onPause();
        //Close drawer
        MainActivity.closeDrawer(drawerLayout);
    }

}