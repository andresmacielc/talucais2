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

public class AgregarTareasActivity extends AppCompatActivity {

    Button buttonAgregarTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_tareas_agregar);
        buttonAgregarTarea= (Button)findViewById(R.id.buttonAgregarTarea);
        buttonAgregarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicioAgregarTarea();
            }
        });
    }

    @SuppressLint("WrongConstant")
    private void servicioAgregarTarea() {


        EditText editTextNombreTarea = (EditText) findViewById(R.id.editTextNombreTarea);
        EditText editTextDescripcionTarea = (EditText) findViewById(R.id.editTextDescripcionTarea);
        EditText editTextAgregarFechaIniTarea = (EditText) findViewById(R.id.editTextAgregarFechaIniTarea);
        EditText editTextAgregarFechaFinTarea = (EditText) findViewById(R.id.editTextAgregarFechaFinTarea);
        EditText editTextIdProject = (EditText) findViewById(R.id.editTextIdProject);
        int message;
        JSONObject loginParams = new JSONObject();

        try {


            loginParams.put("nombre", editTextNombreTarea.getText().toString());
            loginParams.put("descripcion", editTextDescripcionTarea.getText().toString());
            loginParams.put("fechaInicio", editTextAgregarFechaIniTarea.getText().toString() + "T00:00:00-03:00");
            loginParams.put("fechaFin", editTextAgregarFechaFinTarea.getText().toString() + "T00:00:00-03:00");
            loginParams.put("id_project", editTextIdProject.getText());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            message = ServicioActivity.postSinRespuesta(this,"http://"+ServicioActivity.ip+"/Sgpis2/webresources/spis2.entities.sprint", loginParams.toString());
            if (message != 204){
                Toast.makeText(this,"Ocurrio un problema al registar la Tarea "+message, 5).show();
                return;
            }
            Toast.makeText(this,"Se ha Registrado la Tarea", 5).show();
        }
        catch(NullPointerException e){
            Toast.makeText(this,"No se pudo conectar con el servidor", 5).show();
        }

    }
}
