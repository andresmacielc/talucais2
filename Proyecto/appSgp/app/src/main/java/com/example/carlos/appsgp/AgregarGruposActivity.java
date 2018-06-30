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


public class AgregarGruposActivity extends AppCompatActivity {

    Button buttonAgregarGrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_grupos_agregar);
        buttonAgregarGrupo= (Button)findViewById(R.id.buttonAgregarGrupo);
        buttonAgregarGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicioAgregarGrupo();
            }
        });
    }

    @SuppressLint("WrongConstant")
    private void servicioAgregarGrupo() {

        EditText editTextIdGrupo = (EditText) findViewById(R.id.editTextIdGrupo);
        EditText editTextIdProyecto = (EditText) findViewById(R.id.editTextIdProyecto);
        EditText editTextDescripcionGrupo = (EditText) findViewById(R.id.editTextDescripcionGrupo);
        int message;
        JSONObject loginParams = new JSONObject();

        try {

            loginParams.put("id_grupo", editTextIdGrupo.getText().toString());
            loginParams.put("descripcion", editTextDescripcionGrupo.getText().toString());
            loginParams.put("id_proyecto", editTextIdProyecto.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            message = ServicioActivity.postSinRespuesta(this,"http://"+ServicioActivity.ip+"/Sgpis2/webresources/spis2.entities.sprint", loginParams.toString());
            if (message != 204){
                Toast.makeText(this,"Ocurrio un problema al registar el grupo "+message, 5).show();
                return;
            }
            Toast.makeText(this,"Se ha Registrado el grupo", 5).show();
        }
        catch(NullPointerException e){
            Toast.makeText(this,"No se pudo conectar con el servidor", 5).show();
        }

    }
}
