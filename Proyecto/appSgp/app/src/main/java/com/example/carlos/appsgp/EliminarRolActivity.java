package com.example.carlos.appsgp;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarRolActivity extends AppCompatActivity {

    Button botonEliminarRol;
    EditText editTextEliminarIdRol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_rol_eliminar);

        botonEliminarRol = (Button) findViewById(R.id.buttonEliminarRol);
        editTextEliminarIdRol = (EditText) findViewById(R.id.editTextEliminarIdRol);
        botonEliminarRol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarRol();
            }

        });
    }

    @SuppressLint("WrongConstant")
    private void eliminarRol() {

        int resp;
        String url = "http://"+ServicioActivity.ip+"/Sgpis2/webresources/spis2.entities.rol/"+editTextEliminarIdRol.getText().toString();
        resp = ServicioActivity.delete(this,url);
        if (resp != 204){
            Toast.makeText(this,"El rol no existe, verifique ID ingresado", 5).show();
            return;
        }
        Toast.makeText(this,"Se ha eliminado el rol exitosamente", 5).show();

    }
}

