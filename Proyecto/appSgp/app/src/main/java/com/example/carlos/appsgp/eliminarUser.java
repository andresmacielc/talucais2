package com.example.carlos.appsgp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by carlos on 29/04/18.
 */

public class eliminarUser extends AppCompatActivity {
    Button botonEliminar;
    EditText editEliminarEmail;
    int id;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eliminar_user);
        botonEliminar = (Button) findViewById(R.id.buttonEliminaUser);
        editEliminarEmail = (EditText) findViewById(R.id.editTextEliminarEmail);
        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminar();
            }
        });
    }
    @SuppressLint("WrongConstant")
    public void eliminar(){
        String url = "http://192.168.43.57:8080/Sgpis2/webresources/spis2.entities.usuario";
        JSONArray usuariosJSON = DisplayMenuActivity.executeGet(url);
        for(int i=0; i<usuariosJSON.length(); i++)
        {
            try {
                JSONObject obj = usuariosJSON.getJSONObject(i);
                if (obj.getString("email").equalsIgnoreCase(editEliminarEmail.toString())){
                    id = obj.getInt("idUsuario");
                    ejecucionEliminar("http://192.168.43.57:8080/Sgpis2/webresources/spis2.entities.usuario/"+
                    id);
                    Toast.makeText(this,"Eliminacion Exitosa", 5).show();
                    break;
                }else {
                    Toast.makeText(this,"No existe User "+editEliminarEmail.toString(), 5).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
    @SuppressLint("WrongConstant")
    public void ejecucionEliminar(String targetURL) {
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
            rd.close();

        } catch (Exception e) {
            Toast.makeText(this,"Ocurrio un Problema", 10).show();
            e.printStackTrace();
        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}


