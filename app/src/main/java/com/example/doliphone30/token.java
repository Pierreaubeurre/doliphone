package com.example.doliphone30;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class token  extends Thread{

    private String IP;
    private String identifiant;
    private String mdp;
    private login première;

    public token(login première,String IP,String identifiant,String mdp){

        this.IP=IP;
        this.identifiant=identifiant;
        this.mdp=mdp;
        this.première=première;

    }
    
    @Override
    public void run() {

        try
        {
            URL url = new URL("http://"+IP+"/dolibarr/api/index.php/login?login="+identifiant+"&password="+mdp);//créer une variable pour l'url
            HttpURLConnection connexion = (HttpURLConnection) url.openConnection();//créer un objet de connexion
            connexion.setRequestMethod("GET");//sélectionne la méthode à utiliser
            connexion.setConnectTimeout(4000);//donne un délai de connexion
            connexion.setReadTimeout(5000);//donne un délai de lecture


            String result= "";

            BufferedReader buff = new BufferedReader(new InputStreamReader(connexion.getInputStream()));

            String ligne = buff.readLine();
            while (ligne!=null){
                result += ligne + "\n";
                ligne = buff.readLine();
            }

            try
            {

                JSONObject JSONresult= new JSONObject(result);//converti le string JSON en JSONObject

                String Dolikey = JSONresult.getJSONObject("success").getString("token");//Navigue dans la JSON et extrait le token

                première.changement(Dolikey);

            }
            catch (JSONException e)
            {
                e.printStackTrace();//renvoie les erreurs
            }

        }

        catch (IOException e)
        {
            e.printStackTrace();//renvoie les erreurs
            System.out.println("Echec de la connexion");
            //alerte();
        }

    }


    /*

    public void alerte() {
        AlertDialog.Builder bdr = new AlertDialog.Builder(première);
        bdr.setCancelable(false) //pour ne pas supprimer l'alerte en cliquant en dehors du dialogue
                .setTitle("Echec de la connexion") // titre de l'alerte
                .setMessage("Votre identifiant et/ou votre mot de passe ne sont pas correct") // message de l'alerte


                .setPositiveButton("Recommencer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();//ferme la boîte de dialogue
                    }
                });

        AlertDialog myAlert = bdr.create(); // on crée l'alerte
        myAlert.show(); // on l'affiche
    }
    */

}
