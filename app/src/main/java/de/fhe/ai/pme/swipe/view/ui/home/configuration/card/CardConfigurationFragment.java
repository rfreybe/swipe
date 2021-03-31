package de.fhe.ai.pme.swipe.view.ui.home.configuration.card;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
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
    private static final int REQUEST_IMAGE_PICK = 1338;

    private String currentPhotoPath;
    private CardConfigurationViewModel cardConfigurationViewModel;
    private KeyValueStore keyValueStore;
    private EditText CardNameField;
    private EditText CardQuestionField;
    private EditText CardAnswerField;
    private boolean filesOk;
    private String camQuestionFilePath;
    private String camAnswerFilePath;
    private String galleryQuestionFilePath;
    private String galleryAnswerFilePath;


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


    private final View.OnClickListener openGalleryClickListenerQuestion = v -> {
        Intent getPictureIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        getPictureIntent.setType("image/*");
        startActivityForResult(getPictureIntent, REQUEST_IMAGE_PICK);

    };

    private final View.OnClickListener openGalleryClickListenerAnswer = v -> {
        Intent getPictureIntent = new Intent(Intent.ACTION_PICK);
        getPictureIntent.setType("image/*");
        startActivityForResult(getPictureIntent, REQUEST_IMAGE_PICK);

    };

    private final View.OnClickListener openCamClickListenerQuestion = v -> {
        Context context = getActivity();
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //Ensure there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null){
            //Create the file where the photo should go
            File photoFile= null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Toast.makeText(context, "Datei konnte nicht erstellt werden", Toast.LENGTH_SHORT);
            }
            //Continue only, if the file was sucessfully created
            if (photoFile != null){
                Uri photoURI = FileProvider.getUriForFile(context,
                        "de.fhe.ai.pme.swipe.fileprovider", photoFile
                );
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
            camQuestionFilePath = photoFile.getPath();
        }
    };

    private final View.OnClickListener openCamClickListenerAnswer = v -> {
        Context context = getActivity();
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //Ensure there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null){
            //Create the file where the photo should go
            File photoFile= null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Toast.makeText(context, "Datei konnte nicht erstellt werden", Toast.LENGTH_SHORT);
            }
            //Continue only, if the file was sucessfully created
            if (photoFile != null){
                Uri photoURI = FileProvider.getUriForFile(context,
                        "de.fhe.ai.pme.swipe.fileprovider", photoFile
                );
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
            camAnswerFilePath = photoFile.getPath();
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && (requestCode == REQUEST_IMAGE_CAPTURE || requestCode == REQUEST_IMAGE_PICK)) {
            filesOk = true;
            Toast.makeText(getContext(), "Foto erfolgreich hinzugefügt!", Toast.LENGTH_SHORT).show();
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
        this.filesOk = false;

        // Save Card
        Button saveBtn = root.findViewById(R.id.btn_save_card);
        saveBtn.setOnClickListener(this.saveCardButtonClickListener);

        // Cambuttons
        ImageView QuestionCam = root.findViewById(R.id.QuestionCam);
        QuestionCam.setOnClickListener(this.openCamClickListenerQuestion);

        ImageView AnswerCam= root.findViewById(R.id.AnswerCam);
        AnswerCam.setOnClickListener(this.openCamClickListenerAnswer);

        // Picbuttons
        ImageView QuestionPic= root.findViewById(R.id.QuestionPic);
        QuestionPic.setOnClickListener(this.openGalleryClickListenerQuestion);

        ImageView AnswerPic = root.findViewById(R.id.AnswerPic);
        AnswerPic.setOnClickListener(this.openGalleryClickListenerAnswer);


        // On Click Listener Back Button
        ImageView BackBtn = getActivity().findViewById(R.id.back_button);
        BackBtn.setOnClickListener(this.backBtnListener);

        return root;
    }

    public void ClickSubmitChanges(View view){

    }
    // Redirect to CreateFolderOrCard Fragment
    private final View.OnClickListener saveCardButtonClickListener= v -> {

        //Create Front Page
        Page frontPage = new Page(true);
        frontPage.setText(CardQuestionField.getText().toString());
        if(filesOk && camQuestionFilePath != null) {
            frontPage.setFile(camQuestionFilePath);
        }
        cardConfigurationViewModel.savePage(frontPage);
        long frontPageID = cardConfigurationViewModel.getLastCreatedPageID();

        //Create Back Page
        Page backPage = new Page(false);
        backPage.setText(CardAnswerField.getText().toString());
        if(filesOk && camAnswerFilePath != null) {
            backPage.setFile(camAnswerFilePath);
        }
        cardConfigurationViewModel.savePage(backPage);
        long backPageID = cardConfigurationViewModel.getLastCreatedPageID();

        //Create Card
        long currentFolderID = keyValueStore.getValueLong("currentFolderID");
        Card newCard = new Card(CardNameField.getText().toString(), currentFolderID, frontPageID, backPageID);
        newCard.setManualOrderID(cardConfigurationViewModel.getNextManualOrderID(newCard.getParentFolderID()));
        cardConfigurationViewModel.saveCard(newCard);

        keyValueStore.editValueBool("currentFolderContainsCards", true);

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.navigation_create_folder_or_card);

    };

    private final View.OnClickListener backBtnListener= v -> {

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.navigation_create_folder_or_card);
    };


}