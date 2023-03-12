package com.example.doliphone30;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class post extends Thread {

    private String key;
    private String IP;
    private String objet;
    private String montant;

    public post(String key,String IP,String objet,String montant){

        this.key=key;
        this.IP=IP;
        this.objet=objet;
        this.montant=montant;
    }

    public void run()
    {
        URL url = null;//créer une variable pour l'url


        try {

            url = new URL("http://"+IP+"/dolibarr/api/index.php/bankaccounts/1/lines");
            HttpURLConnection connexionNote = (HttpURLConnection) url.openConnection();//créer un objet de connexion
            connexionNote.setRequestMethod("POST");//sélectionne la méthode à utiliser
            connexionNote.setConnectTimeout(4000);//donne un délai de connexion
            connexionNote.setReadTimeout(5000);//donne un délai de lecture


            connexionNote.setRequestProperty("Accept","application/json");//ajoute du contenu dans le header
            connexionNote.setRequestProperty("Content-Type","application/json");
            connexionNote.setRequestProperty("DOLAPIKEY",key);//ajoute du contenu dans le header


            String label="Demande de remboursement : "+objet;


            String body=("{\"date\":\"1665489087\",\"label\":\""+label+"\",\"amount\":\""+montant+"\",\"type\":\"CB\",\"category\":\"2\"}");


            connexionNote.setDoOutput (true);
            OutputStream os =connexionNote.getOutputStream();
            byte[] input = body.getBytes("utf-8");
            os.write(input,0, input.length);

            connexionNote.connect();

            BufferedReader buff = new BufferedReader(new InputStreamReader(connexionNote.getInputStream()));


            String result ="";
            String ligne = buff.readLine();
            while (ligne!=null){
                result += ligne + "\n";
                ligne = buff.readLine();
            }


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("marche po");

        }
    }

}
