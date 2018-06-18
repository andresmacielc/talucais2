package com.example.carlos.appsgp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by carlos on 29/04/18.
 */

public class AgregarUserActivity extends AppCompatActivity {
    Button buttonAgregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_usuario_agregar);
        buttonAgregar= (Button)findViewById(R.id.agregarButton);
        buttonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servicioUser();
            }
        });
    }
    @SuppressLint("WrongConstant")
    public void servicioUser(){
        short checked = 0;
        EditText editTextUserName = (EditText) findViewById(R.id.editAgregarNombre);
        EditText editTextUserApellido = (EditText) findViewById(R.id.editAgregarApellido);
        EditText editTextUserEmail = (EditText) findViewById(R.id.editAgregarEmail);
        EditText editTextUserPassword = (EditText) findViewById(R.id.editAgregarPassword);
        Switch sEstado = (Switch) findViewById(R.id.agregarSwitchEstado);
        int message;
        JSONObject loginParams = new JSONObject();
        if(sEstado.isChecked()){
            checked = 1;
        };
        try {
            loginParams.put("nombre", editTextUserName.getText().toString());
            loginParams.put("apellido", editTextUserApellido.getText().toString());
            loginParams.put("email", editTextUserEmail.getText().toString());
            loginParams.put("password", editTextUserPassword.getText().toString());
            loginParams.put("fechaCreacionUsuario", "2018-03-12T00:00:00-03:00");
            loginParams.put("status", checked);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            message = ServicioActivity.postSinRespuesta(this,"http://"+ServicioActivity.ip+"/Sgpis2/webresources/spis2.entities.usuario", loginParams.toString());
            if (message != 204){
                Toast.makeText(this,"Ocurrio un problema al registar User "+message, 5).show();
                return;
            }
            Toast.makeText(this,"Registro exitoso", 5).show();
        }
        catch(NullPointerException e){
            Toast.makeText(this,"No se pudo conectar con el servidor", 5).show();
        }

    }
}



