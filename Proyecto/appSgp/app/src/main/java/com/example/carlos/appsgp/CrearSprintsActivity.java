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

public class CrearSprintsActivity extends AppCompatActivity {

    Button buttonCrearSprint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_sprints_crear);
        buttonCrearSprint= (Button)findViewById(R.id.buttonCrearSprint);
        buttonCrearSprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicioCrearSprint();
            }
        });
    }

    @SuppressLint("WrongConstant")
    private void servicioCrearSprint() {

        EditText editTextIdSprint = (EditText) findViewById(R.id.editTextIdSprint);
        EditText editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        EditText editTextFecha_inicio = (EditText) findViewById(R.id.editTextFecha_inicio);
        EditText editTextFecha_fin = (EditText) findViewById(R.id.editTextFecha_fin);
        EditText editTextDescripcionSprint = (EditText) findViewById(R.id.editTextDescripcionSprint);
        EditText editTextIdProyecto = (EditText) findViewById(R.id.editTextIdProyecto);
        int message;
        JSONObject loginParams = new JSONObject();

        try {

            loginParams.put("id_sprint", editTextIdSprint.getText().toString());
            loginParams.put("nombre", editTextNombre.getText().toString());
            loginParams.put("fecha_inicio", editTextFecha_inicio.getText().toString() + "T00:00:00-03:00");
            loginParams.put("fecha_fin", editTextFecha_fin.getText().toString() + "T00:00:00-03:00");
            loginParams.put("descripcion", editTextDescripcionSprint.getText().toString());
            loginParams.put("id_proyecto", editTextIdProyecto.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            message = ServicioActivity.postSinRespuesta(this,"http://"+ServicioActivity.ip+"/Sgpis2/webresources/spis2.entities.sprint", loginParams.toString());
            if (message != 204){
                Toast.makeText(this,"Ocurrio un problema al registar el sprint "+message, 5).show();
                return;
            }
            Toast.makeText(this,"Se ha Registrado el sprint", 5).show();
        }
        catch(NullPointerException e){
            Toast.makeText(this,"No se pudo conectar con el servidor", 5).show();
        }

    }
}