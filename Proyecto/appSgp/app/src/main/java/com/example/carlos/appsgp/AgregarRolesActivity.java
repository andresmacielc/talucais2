package com.example.carlos.appsgp;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class AgregarRolesActivity extends AppCompatActivity {


    Button buttonAgregarRol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_roles_agregar);
        buttonAgregarRol = (Button) findViewById(R.id.buttonAgregarRol);
        buttonAgregarRol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicioAgregarRol();
            }
        });
    }

    @SuppressLint("WrongConstant")
    public void servicioAgregarRol() {
        short checked = 0;
        EditText editTextIdRol = (EditText) findViewById(R.id.editAgregarRol);
        EditText editTextDescripcionRol = (EditText) findViewById(R.id.editTextDescripcionRol);
        Switch sEstado = (Switch) findViewById(R.id.agregarSwitchEstado);
        int message;
        JSONObject loginParams = new JSONObject();
        if (sEstado.isChecked()) {
            checked = 1;
        }
        ;
        try {
            loginParams.put("id_rol", editTextIdRol.getText().toString());
            loginParams.put("descripion", editTextDescripcionRol.getText().toString());
            loginParams.put("status", checked);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            message = ServicioActivity.postSinRespuesta(this, "http://" + ServicioActivity.ip + "/Sgpis2/webresources/spis2.entities.rol", loginParams.toString());
            if (message != 204) {
                Toast.makeText(this, "Ocurrio un problema al registar el rol " + message, 5).show();
                return;
            }
            Toast.makeText(this, "Registro exitoso", 5).show();
        } catch (NullPointerException e) {
            Toast.makeText(this, "No se pudo conectar con el servidor", 5).show();
        }

    }
}