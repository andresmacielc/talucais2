package com.example.carlos.appsgp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Created by carlos on 29/04/18.
 */

public class EliminarUserActivity extends AppCompatActivity {
    Button botonEliminar;
    EditText editEliminarId;
    int id;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_usuario_eliminar);
        botonEliminar = (Button) findViewById(R.id.buttonEliminarUser);
        editEliminarId = (EditText) findViewById(R.id.editTextEliminarId);
        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminar();
            }
        });
    }
    @SuppressLint("WrongConstant")
    public void eliminar(){
        int resp;
        String url = "http://"+ServicioActivity.ip+"/Sgpis2/webresources/spis2.entities.usuario/"+editEliminarId.getText().toString();
        resp = ServicioActivity.delete(this,url);
        if (resp != 204){
            Toast.makeText(this,"El user no existe!!!", 5).show();
            return;
        }
        Toast.makeText(this,"Eliminacion exitosa", 5).show();

    }
}


