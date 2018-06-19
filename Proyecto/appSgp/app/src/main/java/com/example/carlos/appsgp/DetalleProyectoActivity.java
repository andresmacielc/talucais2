package com.example.carlos.appsgp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class DetalleProyectoActivity extends AppCompatActivity {

    Button buttonTareaDetalle;
    EditText editTextIdProyecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_detalle_proyecto);

        buttonTareaDetalle = (Button) findViewById(R.id.buttonTareaProyecto);
        editTextIdProyecto = (EditText) findViewById(R.id.editTextIdProyecto);

        buttonTareaDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarIdProyecto();
            }
        });

    }

    @SuppressLint("WrongConstant")
    private void verificarIdProyecto() {
        String mensaje;
        String url = "http://"+ServicioActivity.ip+"/Sgpis2/webresources/spis2.entities.projects/"+editTextIdProyecto.getText().toString();
        mensaje = ServicioActivity.getId(url);
        if(mensaje != null) {

            //Completar

            Intent intentUTR = new Intent(this, TareaUsuarioRolActivity.class);
            startActivity(intentUTR);


        }else{
            Toast.makeText(this,"El ID del proyecto no se encuentra disponible", 10).show();
        }

    }

}
