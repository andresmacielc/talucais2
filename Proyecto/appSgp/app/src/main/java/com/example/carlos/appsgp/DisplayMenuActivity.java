package com.example.carlos.appsgp;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class DisplayMenuActivity extends AppCompatActivity {
    Button botonUsuarios;

    ArrayList<String> Coleccion = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostar_datos);
        botonUsuarios = (Button) findViewById(R.id.buttonUser);
        botonUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarListaUser(view);

            }
        });
    }

    public void cargarListaUser(View view) {
        String url = "http://192.168.43.57:8080/Sgpis2/webresources/spis2.entities.usuario";
        JSONArray usuariosJSON = executeGet(url);
        String userLista;
        for(int i=0; i<usuariosJSON.length(); i++)
        {
            JSONObject obj = null;
            try {
                obj = usuariosJSON.getJSONObject(i);
                userLista = "\nID: "+obj.getString("idUsuario");
                userLista += "\nNombre: "+obj.getString("nombre");
                userLista += "\nApellido: "+obj.getString("apellido");
                userLista += "\nEmail: "+obj.getString("email");
                Coleccion.add(userLista);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Intent intent = new Intent(this, UserActivity.class);
        intent.putExtra("Coleccion", Coleccion);
        startActivity(intent);

    }
    @SuppressLint("WrongConstant")
    public static JSONArray executeGet(String targetURL) {

        URL url;
        HttpURLConnection connection = null;
        try {
            //establece la conexion
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // obtiene la respuesta
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\n');
            }
            rd.close();
            JSONArray respJSON = new JSONArray(response.toString());
            return respJSON;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }
}
