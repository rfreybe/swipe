package de.fhe.ai.pme.swipe.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.swipe.R;


//Class for the green add button on down right side



public class CreateFolderOrCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_folder_or_card);
        // Assign Variable

    }


    public void ClickAddCard(View view){
        MainActivity.redirectActivity(this,CardConfiguration.class);
    }

    public void ClickAddFolder(View view){


    }

}