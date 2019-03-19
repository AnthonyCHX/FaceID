package com.example.faceid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button inscriptionBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inscriptionBtn=findViewById(R.id.buttonInsciption);

        inscriptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inscriptionacivity = new Intent(MainActivity.this, Inscription.class);
                startActivity(inscriptionacivity);
                overridePendingTransition(0, 0);
            }
        });
    }


}
