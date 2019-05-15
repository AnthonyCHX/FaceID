package com.example.faceid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button inscriptionBtn;
    private Button verificationBtn;
    private Button authentificationBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inscriptionBtn=findViewById(R.id.buttonInsciption);
        verificationBtn=findViewById(R.id.buttonVerification);
        authentificationBtn=findViewById(R.id.buttonAuthentification);

        inscriptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inscriptionacivity = new Intent(MainActivity.this, Inscription.class);
                startActivity(inscriptionacivity);
                overridePendingTransition(0, 0);
            }
        });

        verificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent verificationacivity = new Intent(MainActivity.this, Verification.class);
                startActivity(verificationacivity);
                overridePendingTransition(0, 0);
            }
        });


        authentificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent authentificationacivity = new Intent(MainActivity.this, Authentification.class);
                startActivity(authentificationacivity);
                overridePendingTransition(0, 0);
            }
        });


    }


}
