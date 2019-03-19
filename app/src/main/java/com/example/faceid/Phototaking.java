package com.example.faceid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Phototaking extends AppCompatActivity {
    private ImageButton retour;
    private ImageButton suivant;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phototaking);


        retour=findViewById(R.id.phototaking_retour);
        suivant=findViewById(R.id.phototaking_suivant);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent retouravant=new Intent(Phototaking.this,Inscription.class);
                startActivity(retouravant);
                overridePendingTransition(0, 0);


            }
        });
        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent suivantapres=new Intent(Phototaking.this,ConfirmationInscrition.class);
                startActivity(suivantapres);
                overridePendingTransition(0, 0);

            }
        });
    }
}
