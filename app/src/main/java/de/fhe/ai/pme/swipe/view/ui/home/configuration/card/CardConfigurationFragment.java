package de.fhe.ai.pme.swipe.view.ui.home.configuration.card;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.security.Key;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.fhe.ai.pme.swipe.R;
import de.fhe.ai.pme.swipe.model.Card;
import de.fhe.ai.pme.swipe.model.Folder;
import de.fhe.ai.pme.swipe.model.Page;
import de.fhe.ai.pme.swipe.storage.KeyValueStore;
import de.fhe.ai.pme.swipe.view.ui.core.BaseFragment;

import static android.app.Activity.RESULT_OK;
import static java.io.File.createTempFile;


//opens after you decided for adding a Card




public class CardConfigurationFragment extends BaseFragment {

    private static final int REQUEST_IMAGE_CAPTURE = 1337;
    private String currentPhotoPath;
    ImageView imageView;
    Button btnOpenCam;

    private CardConfigurationViewModel cardConfigurationViewModel;
    private KeyValueStore keyValueStore;

    private EditText CardNameField;
    private EditText CardQuestionField;
    private EditText CardAnswerField;

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String imageFileName = "PME_SA_" + timeStamp + "_";
        File storageDir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        currentPhotoPath = "file://" + image.getAbsolutePath();

        Log.i("TakingPictures", currentPhotoPath);

        return image;
    }


    private final View.OnClickListener openCamClickListener = v -> {
        Context context = getActivity();
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //Ensure there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null){
            //Create the file where the photo should go
            File photoFile= null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Toast.makeText(context, "Could not create file for image", Toast.LENGTH_SHORT);
            }
            //Continue only, if the file was sucessfully created
            if (photoFile != null){
                Uri photoURI = FileProvider.getUriForFile(context,
                        "de.fhe.ai.pme.swipe.fileprovider", photoFile
                );
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            //übergebe das bild an die karte
            //this.vorderseite.setImageURI( URI.parse(currentPhotoPath));
        }
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        cardConfigurationViewModel = this.getViewModel(CardConfigurationViewModel.class);
        keyValueStore = new KeyValueStore(getActivity().getApplication());

        View root = inflater.inflate(R.layout.fragment_card_configuration, container, false);

        this.CardNameField = root.findViewById(R.id.et_name_of_card);
        this.CardQuestionField = root.findViewById(R.id.et_question);
        this.CardAnswerField = root.findViewById(R.id.et_answer);

        //ASSIGN BUTTON THE FUNCTION
        Button saveBtn = root.findViewById(R.id.btn_save_card);
        saveBtn.setOnClickListener(this.saveCardButtonClickListener);

        // Cambutton
        Button openCam = root.findViewById(R.id.btnOpenCam);
        openCam.setOnClickListener(this.openCamClickListener);

        return root;
    }



    public void ClickAddFile(View view){

    }

    public void ClickSubmitChanges(View view){

    }
    // Redirect to CreateFolderOrCard Fragment
    private final View.OnClickListener saveCardButtonClickListener= v -> {

        //Create Front Page
        Page frontPage = new Page(true);
        frontPage.setText(CardQuestionField.getText().toString());
        long frontPageID = cardConfigurationViewModel.savePage(frontPage);

        //Create Back Page
        Page backPage = new Page(false);
        backPage.setText(CardAnswerField.getText().toString());
        long backPageID = cardConfigurationViewModel.savePage(backPage);

        //Create Card
        //TODO: ID's of Front Page & Back Page still 0
        Card newCard = new Card(CardNameField.getText().toString(), keyValueStore.getValueLong("currentFolderID"), frontPageID, backPageID);
        cardConfigurationViewModel.saveCard(newCard);



        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        navController.navigate(R.id.navigation_create_folder_or_card);

    };


}