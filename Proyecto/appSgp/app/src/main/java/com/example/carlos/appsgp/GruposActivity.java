package com.example.carlos.appsgp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class GruposActivity extends AppCompatActivity {

    ArrayAdapter<String> adaptadorColeccionGrupo;
    ListView listaGrupo;
    ArrayList<String> coleccionGrupo = new ArrayList<String>();


    Button buttonGrupoAgregar;
    Button buttonGrupoEditar;
    Button buttonGrupoEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_grupos);

        Intent intentGrupo = getIntent();
        coleccionGrupo = intentGrupo.getStringArrayListExtra("Coleccion");
        listaGrupo = (ListView) findViewById(R.id.listViewUserHistory);
        adaptadorColeccionGrupo = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coleccionGrupo);
        listaGrupo.setAdapter(adaptadorColeccionGrupo);
        buttonGrupoAgregar = (Button) findViewById(R.id.buttonGrupoAgregar);
        buttonGrupoEliminar = (Button) findViewById(R.id.buttonGrupoEliminar);
        buttonGrupoEditar = (Button) findViewById(R.id.buttonGrupoEditar);

        buttonGrupoEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarGrupo();
            }
        });
        buttonGrupoAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarGrupo();
            }
        });
        buttonGrupoEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarGrupo();
            }
        });
    }

    private void agregarGrupo() {
        Intent intentAG = new Intent(this, AgregarGruposActivity.class);
        startActivity(intentAG);
    }

    private void editarGrupo() {
        Intent intentEG = new Intent(this, EditarGruposActivity.class);
        startActivity(intentEG);
    }

    private void eliminarGrupo() {
        Intent intentDG = new Intent(this, EliminarGrupoActivity.class);
        startActivity(intentDG);
    }

}
