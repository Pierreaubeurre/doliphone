package com.example.doliphone30;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class threadListe extends Thread {

    @Override
    public void run() {

        try
        {
            URL url = new URL("http://192.168.1.91/dolibarr/api/index.php/bankaccounts/1/lines");//créer une variable pour l'url
            HttpURLConnection connexion = (HttpURLConnection) url.openConnection();//créer un objet de connexion
            connexion.setRequestMethod("GET");//sélectionne la méthode à utiliser
            connexion.setConnectTimeout(4000);//donne un délai de connexion
            connexion.setReadTimeout(5000);//donne un délai de lecture
            connexion.setRequestProperty("DOLAPIKEY","Token");

            connexion.setRequestProperty("Accept","application/json");

            String result= "";

            BufferedReader buff = new BufferedReader(new InputStreamReader(connexion.getInputStream()));

            String ligne = buff.readLine();
            while (ligne!=null){
                result += ligne + "\n";
                ligne = buff.readLine();
            }

            line(result);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("marche po");
        }
    }

    private void line(String result){

        try
        {


            JSONArray JSONresult = new JSONArray(result);//converti le string JSON en JSONObject
            String test = JSONresult.getJSONObject(0).getString("id");

            System.out.println(test);

            /*
            JSONObject Dolikey = JSONresult.getJSONObject("1");//Navigue dans la JSON
            String premier = Dolikey.getString("id");
            System.out.println(premier);
            */


            //table(JSONresult);

        }
        catch (JSONException e)
        {
            e.printStackTrace();//renvoie les erreurs
            System.out.println("Erreur dans la conversion en JSON");
        }
    }


    private void table(JSONObject JSONresult){



    }

}


