package com.example.carlos.appsgp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class TareasActivity extends AppCompatActivity {

    ArrayAdapter<String> adaptadorColeccionTarea;
    ListView listaTarea;
    ArrayList<String> coleccionTarea = new ArrayList<String>();


    Button buttonTareaAgregar;
    Button buttonTareaEditar;
    Button buttonTareaEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_tareas);

        Intent intentTarea = getIntent();
        coleccionTarea = intentTarea.getStringArrayListExtra("ColeccionTarea");
        listaTarea = (ListView) findViewById(R.id.listViewTarea);
        adaptadorColeccionTarea = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coleccionTarea);
        listaTarea.setAdapter(adaptadorColeccionTarea);
        buttonTareaAgregar = (Button) findViewById(R.id.buttonTareaAgregar);
        buttonTareaEliminar = (Button) findViewById(R.id.buttonTareaEliminar);
        buttonTareaEditar = (Button) findViewById(R.id.buttonTareaEditar);



    }
}