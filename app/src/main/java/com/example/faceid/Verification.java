package com.example.faceid;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;

public class Verification extends AppCompatActivity implements View.OnClickListener{
    public static final String UPLOAD_URL = "https://wad97.000webhostapp.com/test.php";
    public static final String UPLOAD_KEY = "image";

    private int PICK_IMAGE_REQUEST = 1;
    //Declaring views
    private Button buttonChoose;
    private Button buttonUpload;
    private ImageView imageView;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private ImageButton retourview;
    private TextView pourcentage;
    private TextView validation;
    private TextView ressemblance;


    private Bitmap bitmap;
    private Uri filePath;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        init();

        retourview=findViewById(R.id.retourview);
        retourview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent retour = new Intent(Verification.this, MainActivity.class);
                startActivity(retour);
                overridePendingTransition(0, 0);
            }
        });
    }
    void init(){
        buttonChoose = findViewById(R.id.btnChoose);
        buttonUpload = findViewById(R.id.btnUpload);

        imageView = findViewById(R.id.imageView);

        pourcentage = findViewById(R.id.pourcentage);
        validation = findViewById(R.id.validation);
        ressemblance = findViewById(R.id.ressemblance);

        editText1 = (EditText) findViewById(R.id.editTextNom);
        editText2 = (EditText) findViewById(R.id.editTextPrenom);
        editText3 = (EditText) findViewById(R.id.editTextAge);

        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
    }


    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage(){
        class UploadImage extends AsyncTask<Bitmap,Void,String>{
            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Verification.this, "Comparaison des visages", "Veuillez patienter...",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSONObject response = null;
                try {
                    response = new JSONObject(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String confidence = null;
                try {
                    confidence = response.getString("confidence");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                pourcentage.setText(confidence+" %");

                float valid = Float.parseFloat(confidence);
                if (valid > 80) {
                    ressemblance.setText("Ressemblance :");
                    validation.setText("✔️");
                }
                else {
                    ressemblance.setText("Ressemblance :");
                    validation.setText("❌");
                }

            }

            @Override
            protected String doInBackground(Bitmap... params) {

                String nom = editText1.getText().toString().trim();
                String prenom = editText2.getText().toString().trim();
                String age = editText3.getText().toString().trim();

                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);

                HashMap<String,String> data = new HashMap<>();
                data.put(UPLOAD_KEY, uploadImage);
                data.put("name",getFileName(filePath));
                data.put("nom",nom);
                data.put("prenom",prenom);
                data.put("age",age);


                String result = rh.postRequest(UPLOAD_URL,data);
                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonChoose) {
            showFileChooser();
        }
        if(v == buttonUpload){
            if(filePath!=null && editText1.getText().toString()!="" && editText2.getText().toString()!="" && editText3.getText().toString()!="") {
                uploadImage();
            } else {
                Toast.makeText(Verification.this,"Informations incompletes",Toast.LENGTH_LONG).show();
            }
        }
    }
    String getFileName(Uri uri){
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
}
