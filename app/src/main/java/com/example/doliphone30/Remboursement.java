package com.example.doliphone30;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Remboursement extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remboursement);

        Button buttonPost = findViewById(R.id.post);

        Button camera = findViewById(R.id.camera);

        Button liste = findViewById(R.id.liste);

        Intent actvitéListe = new Intent(this, Liste.class);



        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent camera_intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE);
            }
        });

                Intent intent = getIntent();


        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String key = "";
                if (intent.hasExtra("dolKey")){
                    key = intent.getStringExtra("dolKey"); }


                String IP = "";
                if (intent.hasExtra("IP")){
                    IP = intent.getStringExtra("IP"); }


                EditText zoneProduit = findViewById(R.id.zoneProduit);
                EditText zoneMontant = findViewById(R.id.zoneMontant);


                String objet= zoneProduit.getText().toString();
                String montant= zoneMontant.getText().toString();


                post paiement = new post(key,IP,objet,montant);
                paiement.start();
            }
        });

        liste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(actvitéListe);
            }
        });


}}