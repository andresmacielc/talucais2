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

public class CrearUserHistoryActivity extends AppCompatActivity {

    Button buttonCrearUserHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_user_history_crear);
        buttonCrearUserHistory= (Button)findViewById(R.id.buttonCrearUserHistory);
        buttonCrearUserHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicioCrearUserHistory();
            }
        });
    }

    @SuppressLint("WrongConstant")
    private void servicioCrearUserHistory() {


        EditText editTextIdHistory = (EditText) findViewById(R.id.editTextIdHistory);
        EditText editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        EditText editTextDescripcionUserHistory = (EditText) findViewById(R.id.editTextDescripcionUserHistory);
        EditText editTextIdSprint = (EditText) findViewById(R.id.editTextIdSprint);
        EditText editTextFecha_creacion = (EditText) findViewById(R.id.editTextFecha_creacion);
        EditText editTextAgregarEstado = (EditText) findViewById(R.id.editTextAgregarEstado);

        int message;
        JSONObject loginParams = new JSONObject();

        try {

            loginParams.put("id_history", editTextIdHistory.getText().toString());
            loginParams.put("nombre", editTextNombre.getText().toString());
            loginParams.put("fecha_creacion", editTextFecha_creacion.getText().toString());
            loginParams.put("status", editTextAgregarEstado.getText().toString());
            loginParams.put("descripcion", editTextDescripcionUserHistory.getText().toString());
            loginParams.put("id_sprint", editTextIdSprint.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            message = ServicioActivity.postSinRespuesta(this,"http://"+ServicioActivity.ip+"/Sgpis2/webresources/spis2.entities.user_history", loginParams.toString());
            if (message != 204){
                Toast.makeText(this,"Ocurrio un problema al registar el user history "+message, 5).show();
                return;
            }
            Toast.makeText(this,"Se ha creado el user history", 5).show();
        }
        catch(NullPointerException e){
            Toast.makeText(this,"No se pudo conectar con el servidor", 5).show();
        }

    }
}
