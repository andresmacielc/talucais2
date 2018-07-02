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
import org.json.simple.*;

public class MainActivity extends AppCompatActivity {

    Button buttonIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_login);
        buttonIngresar= (Button)findViewById(R.id.buttonIngresar);
        buttonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarLogin();

            }
        });
    }

    /*Metodo que es llamado por el onClick*/
    @SuppressLint("WrongConstant")
    public void validarLogin(){

        //recupera los valores ingresados por el usuario
        Intent intent = new Intent(this, InicioActivity.class);

        //startActivity(intent);

        EditText editTextUserName = (EditText) findViewById(R.id.editTextEmail);
        EditText editTextPassword = (EditText) findViewById(R.id.editTextPass);
        String message;

        //crea el objeto json para enviar
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
            message = ServicioActivity.postRespuesta(this,"http://"+ServicioActivity.ip+"/Sgpis2/webresources/spis2.entities.usuario/login_object",
                    loginParams.toString());
            //message = message.replaceAll("\n","");
            if (message.equals("")){
                Toast.makeText(this,"Password o Email Incorrecto", 25000).show();

            }else{
                JSONObject user = new JSONObject();
                //aca hay que convertir el objeto.
                //JSONParser parser = new JSONParser();

                startActivity(intent);
            }

        }
        catch(NullPointerException e){
            Toast.makeText(this,"No se pudo conectar con el servidor", 5).show();
        }

    }
}
