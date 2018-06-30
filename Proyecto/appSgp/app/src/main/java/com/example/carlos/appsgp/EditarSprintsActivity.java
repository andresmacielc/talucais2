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

public class EditarSprintsActivity extends AppCompatActivity {
    Button buttonEditarSprints;
    Button buttonEditarGuardarSprint;

    EditText editTextIdSprint;
    EditText editTextNombre;
    EditText editTextFecha_inicio;
    EditText editTextFecha_fin;
    EditText editTextDescripcionSprint;
    EditText editTextIdProyecto;
    JSONObject objeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_sprints_editar);

        editTextIdSprint = (EditText) findViewById(R.id.editTextIdSprint);
        editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        editTextFecha_inicio = (EditText) findViewById(R.id.editTextFecha_inicio);
        editTextFecha_fin = (EditText) findViewById(R.id.editTextFecha_fin);
        editTextDescripcionSprint = (EditText) findViewById(R.id.editTextDescripcionSprint);
        editTextIdProyecto = (EditText) findViewById(R.id.editTextIdProyecto);

        buttonEditarSprints = (Button)findViewById(R.id.buttonEditarSprints);
        buttonEditarGuardarSprint= (Button)findViewById(R.id.buttonEditarGuardarSprint);

        buttonEditarSprints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listarInfoSprint();
                buttonEditarGuardarSprint.setEnabled(true);
            }
        });

        buttonEditarGuardarSprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarSprints();
            }
        });

    }

    @SuppressLint("WrongConstant")
    private void editarSprints() {

        String url = "http://"+ServicioActivity.ip+"/Sgpis2/webresources/spis2.entities.sprint/"+editTextIdSprint.getText().toString();
        int resp;

        JSONObject loginParams = new JSONObject();

        try {
            loginParams.put("id_sprint", editTextIdSprint.getText().toString());
            loginParams.put("nombre", editTextNombre.getText().toString());
            loginParams.put("Fecha_inicio", editTextFecha_inicio.getText().toString());
            loginParams.put("fechaFin", editTextFecha_fin.getText().toString());
            loginParams.put("descripcion", editTextDescripcionSprint.getText().toString());
            loginParams.put("id_proyecto", editTextIdProyecto.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        resp = ServicioActivity.put(this,url,loginParams.toString());
        if (resp != 204){
            Toast.makeText(this,"No se pudo editar el sprint, ocurrio un problema", 5).show();
            return;
        }
        Toast.makeText(this,"Edicion exitosa del sprint", 5).show();
    }

    @SuppressLint("WrongConstant")
    private void listarInfoSprint() {

        String mensaje;
        String url = "http://"+ServicioActivity.ip+"/Sgpis2/webresources/spis2.entities.sprint/"+editTextIdSprint.getText().toString();
        mensaje = ServicioActivity.getId(url);
        if(mensaje != null) {
            try {
                objeto = new JSONObject(mensaje);
                editTextIdSprint.setText(objeto.getString("id_sprint"));
                editTextNombre.setText(objeto.getString("nombre"));
                editTextFecha_inicio.setText(objeto.getString("fecha_inicio"));
                editTextFecha_fin.setText(objeto.getString("fecha_fin"));
                Toast.makeText(this,"Modificar y pulsar Guardar", 2500).show();
                buttonEditarGuardarSprint.setEnabled(true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this,"El ID no se encuentra disponible!", 5).show();
        }

    }
}