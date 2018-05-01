package com.example.carlos.appsgp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by carlos on 29/04/18.
 */

public class UserActivity extends AppCompatActivity{
    ArrayAdapter<String> adaptadorColeccion;
    ListView listaUsuario;
    ArrayList<String> coleccion = new ArrayList<String>();
    Button buttonAgregar;
    Button buttonEliminar;
    Button buttonEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_usuario);
        Intent intent = getIntent();
        coleccion = intent.getStringArrayListExtra("Coleccion");
        listaUsuario = (ListView) findViewById(R.id.listViewLista);
        adaptadorColeccion = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,coleccion);
        listaUsuario.setAdapter(adaptadorColeccion);
        buttonAgregar = (Button)findViewById(R.id.buttonAgregar);
        buttonEliminar = (Button)findViewById(R.id.buttonEliminar);
        buttonEditar = (Button)findViewById(R.id.buttonEditar);
        buttonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registarUser();

            }
        });
        buttonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarUser();

            }
        });
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarUser();

            }
        });


    }
    public void registarUser(){
        Intent intentA = new Intent(this, AgregarUserActivity.class);
        startActivity(intentA);
    }

    public void eliminarUser(){
        Intent intentA = new Intent(this, EliminarUserActivity.class);
        startActivity(intentA);
    }
    public void editarUser(){
        Intent intentA = new Intent(this, EditarUserActivity.class);
        startActivity(intentA);
    }
}
