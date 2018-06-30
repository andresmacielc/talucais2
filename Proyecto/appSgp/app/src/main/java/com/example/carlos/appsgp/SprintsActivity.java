package com.example.carlos.appsgp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SprintsActivity extends AppCompatActivity {

    ArrayAdapter<String> adaptadorColeccionSprint;
    ListView listaSprint;
    ArrayList<String> coleccionSprint = new ArrayList<String>();


    Button buttonSprintCrear;
    Button buttonSprintEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_sprint);

        Intent intentSprint = getIntent();
        coleccionSprint = intentSprint.getStringArrayListExtra("Coleccion");
        listaSprint = (ListView) findViewById(R.id.listViewSprint);
        adaptadorColeccionSprint = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coleccionSprint);
        listaSprint.setAdapter(adaptadorColeccionSprint);
        buttonSprintCrear = (Button) findViewById(R.id.buttonSprintCrear);
        buttonSprintEditar = (Button) findViewById(R.id.buttonSprintEditar);

        buttonSprintEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarSprint();
            }
        });
        buttonSprintCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearSprint();
            }
        });
    }

    private void crearSprint() {
        Intent intentT = new Intent(this, CrearSprintsActivity.class);
        startActivity(intentT);
    }

    private void editarSprint() {
        Intent intentT = new Intent(this, EditarSprintsActivity.class);
        startActivity(intentT);
    }
}

