package com.example.faceid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Inscription extends AppCompatActivity {
    private ImageButton retourview;
    private ImageButton suivant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        retourview=findViewById(R.id.retourview);
        suivant=findViewById(R.id.inscription_imageAppareilPhoto);

        retourview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent retour = new Intent(Inscription.this, MainActivity.class);
                startActivity(retour);
                overridePendingTransition(0, 0);
            }
        });

        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent suivant = new Intent(Inscription.this,Phototaking.class);
                startActivity(suivant);
                overridePendingTransition(0, 0);
            }
        });


    }
}
