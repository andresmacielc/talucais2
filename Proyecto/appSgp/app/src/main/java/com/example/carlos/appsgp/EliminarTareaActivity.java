package com.example.carlos.appsgp;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarTareaActivity extends AppCompatActivity {

    Button botonEliminarTarea;
    EditText editTextEliminarIdTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_tarea_eliminar);

        botonEliminarTarea = (Button) findViewById(R.id.buttonEliminarTarea);
        editTextEliminarIdTarea = (EditText) findViewById(R.id.editTextEliminarIdTarea);
        botonEliminarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarTarea();
            }
        });

    }

    @SuppressLint("WrongConstant")
    private void eliminarTarea() {

        int resp;
        String url = "http://"+ServicioActivity.ip+"/Sgpis2/webresources/spis2.entities.sprint/"+editTextEliminarIdTarea.getText().toString();
        resp = ServicioActivity.delete(this,url);
        if (resp != 204){
            Toast.makeText(this,"La Tarea no existe, verifique ID ingresado", 5).show();
            return;
        }
        Toast.makeText(this,"Se ha eliminado la tarea exitosamente", 5).show();

    }
}
