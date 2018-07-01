package com.example.carlos.appsgp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
// import android.widget.ArrayAdapter;
import android.widget.Button;
// import android.widget.ListView;
// import java.util.ArrayList;

public class RolesActivity extends AppCompatActivity {

    // ArrayAdapter<String> adaptadorColeccionRol;
    // ListView listaRol;
    // ArrayList<String> coleccionRol = new ArrayList<String>();


    Button buttonRolAgregar;
    Button buttonRolEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_roles);

        // por si queremos listar los roles disponibles

        // Intent intentRol = getIntent();
        // coleccionRol = intentRol.getStringArrayListExtra("Coleccion");
        // listaRol = (ListView) findViewById(R.id.listViewRol);
        // adaptadorColeccionRol = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coleccionRol);
        // listaRol.setAdapter(adaptadorColeccionRol);

        buttonRolAgregar = (Button) findViewById(R.id.buttonRolAgregar);
        buttonRolEliminar = (Button) findViewById(R.id.buttonRolEliminar);


        buttonRolAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarRol();
            }
        });

        buttonRolEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarRol();
            }
        });
    }

    private void agregarRol() {
        Intent intentAR = new Intent(this, AgregarRolesActivity.class);
        startActivity(intentAR);
    }


    private void eliminarRol() {
        Intent intentER = new Intent(this, EliminarRolActivity.class);
        startActivity(intentER);
    }

}


