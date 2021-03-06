
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

public class EditarUserHistoryActivity extends AppCompatActivity {

    Button buttonEditarUserHistory;
    Button buttonEditarGuardarUserHistory;

    EditText editTextIdHistory;
    EditText editTextNombre;
    EditText editTextDescripcionUserHistory;
    EditText editTextIdProyecto;
    EditText editTextAgregarEstado;
    EditText editTextFecha_creacion;

    JSONObject objeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_user_history_editar);

        editTextIdHistory = (EditText) findViewById(R.id.editTextIdHistory);
        editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        editTextDescripcionUserHistory = (EditText) findViewById(R.id.editTextDescripcionUserHistory);
        editTextIdProyecto = (EditText) findViewById(R.id.editTextIdProyecto);
        editTextAgregarEstado = (EditText) findViewById(R.id.editTextAgregarEstado);
        editTextFecha_creacion = (EditText) findViewById(R.id.editTextFecha_creacion);

        buttonEditarUserHistory = (Button)findViewById(R.id.buttonEditarUserHisory);
        buttonEditarGuardarUserHistory= (Button)findViewById(R.id.buttonEditarGuardarUserHistory);

        buttonEditarUserHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listarInfoUserHistory();
                buttonEditarGuardarUserHistory.setEnabled(true);
            }
        });

        buttonEditarGuardarUserHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editUserHistory();
            }
        });

    }

    @SuppressLint("WrongConstant")
    public void listarInfoUserHistory(){
        String mensaje;

        String url = "http://"+ServicioActivity.ip+"/Sgpis2/webresources/spis2.entities.user_history/"+editTextIdHistory.getText().toString();
        mensaje = ServicioActivity.getId(url);
        if(mensaje != null) {
            try {
                editTextIdHistory.setText(objeto.getString("id_history"));
                editTextNombre.setText(objeto.getString("nombre"));
                editTextDescripcionUserHistory.setText(objeto.getString("descripcion"));
                editTextIdProyecto.setText(objeto.getString("id_proyecto"));
                editTextAgregarEstado.setText(objeto.getString("status"));
                editTextFecha_creacion.setText(objeto.getString("fecha_creacion"));


                Toast.makeText(this,"MODIFIQUE LOS DATOS QUE DESEA Y PULSE GUARDAR", 2500).show();
                buttonEditarGuardarUserHistory.setEnabled(true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this,"El id NO EXISTE!!", 5).show();
        }
    }

    @SuppressLint("WrongConstant")
    public void editUserHistory(){
        String url = "http://"+ServicioActivity.ip+"/Sgpis2/webresources/spis2.entities.user_history/"+editTextIdHistory.getText().toString();
        int resp;

        JSONObject loginParams = new JSONObject();

        try {
            loginParams.put("id_history", editTextIdHistory.getText().toString());
            loginParams.put("nombre", editTextNombre.getText().toString());
            loginParams.put("fecha_creacion", editTextFecha_creacion.getText().toString());
            loginParams.put("status", editTextAgregarEstado.getText().toString());
            loginParams.put("descripcion", editTextDescripcionUserHistory.getText().toString());
            loginParams.put("id_proyecto", editTextIdProyecto.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        resp = ServicioActivity.put(this,url,loginParams.toString());
        if (resp != 204){
            Toast.makeText(this,"No se pudo editar, ocurrio un problema", 5).show();
            return;
        }
        Toast.makeText(this,"Edicion exitosa", 5).show();
    }
}





