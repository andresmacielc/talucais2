package com.example.carlos.appsgp;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarGrupoActivity extends AppCompatActivity {

    Button botonEliminarGrupo;
    EditText editTextEliminarIdGrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_grupo_eliminar);

        botonEliminarGrupo = (Button) findViewById(R.id.buttonEliminarGrupo);
        editTextEliminarIdGrupo = (EditText) findViewById(R.id.editTextEliminarIdGrupo);
        botonEliminarGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarGrupo();
            }
        });

    }

    @SuppressLint("WrongConstant")
    private void eliminarGrupo() {

        int resp;
        String url = "http://"+ServicioActivity.ip+"/Sgpis2/webresources/spis2.entities.sprint/"+editTextEliminarIdGrupo.getText().toString();
        resp = ServicioActivity.delete(this,url);
        if (resp != 204){
            Toast.makeText(this,"El grupo no existe, verifique ID ingresado", 5).show();
            return;
        }
        Toast.makeText(this,"Se ha eliminado el grupo exitosamente", 5).show();

    }
}
