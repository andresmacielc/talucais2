package com.example.carlos.appsgp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class UserHistoryActivity extends AppCompatActivity {

    ArrayAdapter<String> adaptadorColeccionUserHistory;
    ListView listaUserHistory;
    ArrayList<String> coleccionUserHistory = new ArrayList<String>();


    Button buttonUserHistoryCrear;
    Button buttonUserHistoryEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_user_history);

        Intent intentUserHistory = getIntent();
        coleccionUserHistory = intentUserHistory.getStringArrayListExtra("Coleccion");
        listaUserHistory = (ListView) findViewById(R.id.listViewUserHistory);
        adaptadorColeccionUserHistory = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coleccionUserHistory);
        listaUserHistory.setAdapter(adaptadorColeccionUserHistory);
        buttonUserHistoryCrear = (Button) findViewById(R.id.buttonUserHistoryCrear);
        buttonUserHistoryEditar = (Button) findViewById(R.id.buttonUserHistoryEditar);

        buttonUserHistoryEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarUserHistory();
            }
        });
        buttonGrupoCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearUserHistory();
            }
        });
    }

    private void crearUserHistory() {
        Intent intentT = new Intent(this, CrearUserHistoryActivity.class);
        startActivity(intentT);
    }

    private void editarUserHistory() {
        Intent intentT = new Intent(this, EditarUserHistoryActivity.class);
        startActivity(intentT);
    }
}
