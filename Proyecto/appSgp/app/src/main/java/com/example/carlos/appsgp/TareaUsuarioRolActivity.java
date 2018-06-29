package com.example.carlos.appsgp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


//se crea las tareas para cada usuario
public class TareaUsuarioRolActivity extends AppCompatActivity {

    ArrayAdapter<String> adapterColecTareaUserRol;
    ListView listaTareaUserRol;
    ArrayList<String> coleccionTareaUserRol = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_tarea_usuario_rol);

        Intent intentTareaUsuarioRol = getIntent();
        coleccionTareaUserRol = intentTareaUsuarioRol.getStringArrayListExtra("Coleccion");
        listaTareaUserRol = (ListView) findViewById(R.id.listViewTareaUsuarioRol);
        adapterColecTareaUserRol = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coleccionTareaUserRol);
        listaTareaUserRol.setAdapter(adapterColecTareaUserRol);

    }
}
