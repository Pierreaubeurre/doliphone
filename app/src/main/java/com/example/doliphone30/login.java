package com.example.doliphone30;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button buttonConnexion = findViewById(R.id.button);

        EditText zoneIdentifiant = findViewById(R.id.zoneLogin);
        EditText zoneMdp = findViewById(R.id.zoneMot);
        EditText zoneIP = findViewById(R.id.zoneIP);


        buttonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String IP = zoneIP.getText().toString();
                String identifiant = zoneIdentifiant.getText().toString();
                String mdp = zoneMdp.getText().toString();

                token connexion = new token(login.this,IP,identifiant,mdp);

                connexion.start();

            }
        });
    }

    public void changement(String key){

        Intent remb = new Intent(this, Remboursement.class);

        EditText zoneIP = findViewById(R.id.zoneIP);
        String IP = zoneIP.getText().toString();

        remb.putExtra("dolKey",key);
        remb.putExtra("IP",IP);

        startActivity(remb);

    }


}