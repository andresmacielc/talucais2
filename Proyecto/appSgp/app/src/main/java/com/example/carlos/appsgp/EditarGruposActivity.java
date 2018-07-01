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

public class EditarGruposActivity extends AppCompatActivity {

    Button buttonEditarGrupos;
    Button buttonEditarGuardarGrupo;

    EditText editTextEditarIdGrupos;
    EditText editTextEditarIdProyecto;
    EditText editTextDescripcionGrupos;
    JSONObject objeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_grupos_editar);

        editTextEditarIdGrupos = (EditText) findViewById(R.id.editTextEditarIdGrupos);
        editTextEditarIdProyecto = (EditText) findViewById(R.id.editTextEditarIdProyecto);
        editTextDescripcionGrupos = (EditText) findViewById(R.id.editTextDescripcionGrupo);

        buttonEditarGrupos = (Button)findViewById(R.id.buttonEditarGrupos);
        buttonEditarGuardarGrupo= (Button)findViewById(R.id.buttonEditarGuardarGrupo);

        buttonEditarGrupos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listarInfoGrupos();
                buttonEditarGuardarGrupo.setEnabled(true);
            }
        });

        buttonEditarGuardarGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarGrupos();
            }
        });

    }

    @SuppressLint("WrongConstant")
    private void editarGrupos() {

        String url = "http://"+ServicioActivity.ip+"/Sgpis2/webresources/spis2.entities.sprint/"+editTextEditarIdGrupos.getText().toString();
        int resp;

        JSONObject loginParams = new JSONObject();

        try {
            loginParams.put("id_grupo", editTextEditarIdGrupos.getText().toString());
            loginParams.put("descripcion", editTextDescripcionGrupos.getText().toString());
            loginParams.put("id_proyecto", editTextEditarIdProyecto.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        resp = ServicioActivity.put(this,url,loginParams.toString());
        if (resp != 204){
            Toast.makeText(this,"No se pudo editar el grupo, ocurrio un problema", 5).show();
            return;
        }
        Toast.makeText(this,"Edicion exitosa del grupo", 5).show();
    }

    @SuppressLint("WrongConstant")
    private void listarInfoGrupos() {

        String mensaje;
        String url = "http://"+ServicioActivity.ip+"/Sgpis2/webresources/spis2.entities.sprint/"+editTextEditarIdGrupos.getText().toString();
        mensaje = ServicioActivity.getId(url);
        if(mensaje != null) {
            try {
                objeto = new JSONObject(mensaje);
                editTextEditarIdGrupos.setText(objeto.getString("id_grupo"));
                editTextDescripcionGrupos.setText(objeto.getString("descripcion"));
                editTextEditarIdProyecto.setText(objeto.getString("id_proyecto"));
                Toast.makeText(this,"Modificar y pulsar Guardar", 2500).show();
                buttonEditarGuardarGrupo.setEnabled(true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this,"El ID no se encuentra disponible!", 5).show();
        }

    }
}
