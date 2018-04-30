package com.example.carlos.appsgp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    //se utilizara como clave para el valor a enviar a traves del intent
    public static final String EXTRA_MESSAGE = "com.example.carlos.appsgp.MESSAGE";

    Button buttonIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        buttonIngresar= (Button)findViewById(R.id.buttonIngresar);
        buttonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarLogin();

            }
        });
    }

    /*Metodo que sera llamado cuando el usuario haga click en el boton login*/
    @SuppressLint("WrongConstant")
    public void validarLogin(){
        //recupera los valores ingresados por el usuario
        Intent intent = new Intent(this, DisplayMenuActivity.class);
        EditText editTextUserName = (EditText) findViewById(R.id.editTextEmail);
        EditText editTextPassword = (EditText) findViewById(R.id.editTextPass);
        String message;
        //crea el objeto json que se enviara con la peticion
        JSONObject loginParams = new JSONObject();

        try {
            loginParams.put("email", editTextUserName.getText().toString());
            loginParams.put("password", editTextPassword.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            message = executePost("http://192.168.43.57:8080/Sgpis2/webresources/spis2.entities.usuario/login", loginParams.toString());
            if (message.equals("")){
                Toast.makeText(this,"Login fallido", 5).show();
                return;
            }
            if(message.equalsIgnoreCase("false")){
                Toast.makeText(this,"Password o Email Incorrecto", 25000).show();

            }else{
                startActivity(intent);
            }

        }
        catch(NullPointerException e){
            Toast.makeText(this,"No se pudo conectar con el servidor", 5).show();
        }

    }
    /*Metodo que realiza la conexion con el servidor y solicita el servicio post del login*/
    @SuppressLint("WrongConstant")
    public String executePost(String targetURL, String urlParameters) {
        int timeout=15000;
        URL url;
        HttpURLConnection connection = null;
        try {
            //establece la conexion

            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);

            //envia la peticion
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            // obtiene la respuesta
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
            rd.close();
            return response.toString();

        } catch (Exception e) {
            Toast.makeText(this,"Error de conexión "+e.getMessage(), 10).show();
            e.printStackTrace();
        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }
}
