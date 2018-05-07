package com.example.carlos.appsgp;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class EditarTareasActivity extends AppCompatActivity {

    Button buttonEditarTareas;
    Button buttonEditarGuardarTarea;

    EditText editTextEditarIdTareas;
    EditText editTextEditarNombreTareas;
    EditText editTextDescripcionTareas;
    EditText editTextEditarTareasFechaIni;
    EditText editTextEditarTareasFechaFin;
    JSONObject objeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_tareas_editar);

        editTextEditarIdTareas = (EditText) findViewById(R.id.editTextEditarIdTareas);
        editTextEditarNombreTareas = (EditText) findViewById(R.id.editTextEditarNombreTareas);
        editTextDescripcionTareas = (EditText) findViewById(R.id.editTextDescripcionTareas);
        editTextEditarTareasFechaIni = (EditText) findViewById(R.id.editTextEditarTareasFechaIni);
        editTextEditarTareasFechaFin = (EditText) findViewById(R.id.editTextEditarTareasFechaFin);

        buttonEditarTareas= (Button)findViewById(R.id.buttonEditarTareas);
        buttonEditarGuardarTarea= (Button)findViewById(R.id.buttonEditarGuardarTarea);

        buttonEditarTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listarInfoTareas();
                buttonEditarGuardarTarea.setEnabled(true);
            }
        });

        buttonEditarGuardarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarTareas();
            }
        });

    }

    @SuppressLint("WrongConstant")
    private void editarTareas() {

        String url = "http://"+ServicioActivity.ip+"/Sgpis2/webresources/spis2.entities.sprint/"+editTextEditarIdTareas.getText().toString();
        int resp;

        JSONObject loginParams = new JSONObject();

        try {
            loginParams.put("nombre", editTextEditarNombreTareas.getText().toString());
            loginParams.put("descripcion", editTextDescripcionTareas.getText().toString());
            loginParams.put("fechaInicio", editTextEditarTareasFechaIni.getText().toString());
            loginParams.put("fechaFin", editTextEditarTareasFechaFin.getText().toString());
            loginParams.put("idSprint", objeto.getString("idSprint"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        resp = ServicioActivity.put(this,url,loginParams.toString());
        if (resp != 204){
            Toast.makeText(this,"No se pudo editar la tarea, ocurrio un problema", 5).show();
            return;
        }
        Toast.makeText(this,"Edicion exitosa de la tarea", 5).show();
    }

    @SuppressLint("WrongConstant")
    private void listarInfoTareas() {

        String mensaje;
        String url = "http://"+ServicioActivity.ip+"/Sgpis2/webresources/spis2.entities.sprint/"+editTextEditarIdTareas.getText().toString();
        mensaje = ServicioActivity.getId(url);
        if(mensaje != null) {
            try {
                objeto = new JSONObject(mensaje);
                editTextEditarNombreTareas.setText(objeto.getString("nombre"));
                editTextDescripcionTareas.setText(objeto.getString("descripcion"));
                editTextEditarTareasFechaIni.setText(objeto.getString("fechaInicio"));
                editTextEditarTareasFechaFin.setText(objeto.getString("fechaFin"));
                Toast.makeText(this,"Modificar y pulsar Guardar", 2500).show();
                buttonEditarGuardarTarea.setEnabled(true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this,"El ID no se encuentra disponible!", 5).show();
        }

    }
}
