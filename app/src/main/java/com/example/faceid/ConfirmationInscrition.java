package com.example.faceid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ConfirmationInscrition extends AppCompatActivity {
    private ImageButton retourview;
    private ImageButton suivant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_inscrition);

        retourview=findViewById(R.id.confirmationinscription_retour);
        suivant=findViewById(R.id.confirmationinscription_plus);

        retourview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent retour = new Intent(ConfirmationInscrition.this, Phototaking.class);
                startActivity(retour);
                overridePendingTransition(0, 0);
            }
        });

        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent suivant = new Intent(ConfirmationInscrition.this,MainActivity.class);
                startActivity(suivant);
                overridePendingTransition(0, 0);
            }
        });
    }
}
