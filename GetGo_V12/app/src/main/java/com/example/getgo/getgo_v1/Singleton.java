package com.example.getgo.getgo_v1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.net.URLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.example.getgo.getgo_v1.CoursesActivity.*;
import static java.lang.System.load;


/**
 * Created by Siham on 2018-02-17.
 *
 * Singleton: to load all the database information needed at one instance and
 * be placed into classes for easy access.
 *
 * Need these Libraries:
 *  - gson-2.7.jar
 *  - org.json.jar
 */

public class Singleton extends AsyncTask <String,Void, JsonObject>{

    String inputLine;
    private ArrayAdapter<String> adapter_classes;

    public Singleton(ArrayAdapter<String> aa){
        this.adapter_classes = aa;
    }

    /*
     * doInBackground: This is where the requests is, because it is under AsyncTask
     *                 It will handle concurrency for our application.
     */
    @Override
    protected JsonObject doInBackground(String... params) {
        URLConnection con;

        /* Getting the URL connection: change to specific IP port and not localhost*/
        try {
            URL url = new URL("http://172.20.10.2/getGophp/tmp.php");
            con =  url.openConnection();

        } catch (Exception e) {
            Log.d("Singleton", "\n-------- ERROR: connecting to port and host.-------  " + e.getLocalizedMessage());

            return null;
        }

        /* Extracting the json string*/
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            inputLine = in.readLine();
            System.out.print(inputLine);
            Log.d("Singleton", "INPUT1: " + in.readLine());

        } catch (Exception e) {
                Log.d("Singleton", "\n--------Error: extracting the Json from the Location.-------  " +  e );
        }

        /*
         *   Using the Gson Library to store the returned json string into the
         *   pre-made classes.
         */
        Gson g = new GsonBuilder().serializeSpecialFloatingPointValues()
                .serializeNulls()
                .setPrettyPrinting()
                .setLenient()
                .create();

        // Loading the string to the class, if you get an error here then class doesn't match string
        JsonObject jsonObject = g.fromJson(inputLine, JsonObject.class);
        return  jsonObject;
    }

    /* Once done, Loads the adapter with the string*/
    public void onPostExecute(JsonObject result) {
        Log.d("Singleton", "INPUT3: " + result.getNames());
        adapter_classes.addAll(result.getNames());
        return;
    }
}
