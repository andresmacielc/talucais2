package com.example.carlos.appsgp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class InicioActivity extends AppCompatActivity {
    Button botonUsuarios;
    Button botonTareas;
    Button buttonTareaDetalle;

    Button buttonRol;
    Button buttonGrupos;
    Button buttonSprint;
    Button buttonUserHistory;

    ArrayList<String> Coleccion = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_inicio);
        botonUsuarios = (Button) findViewById(R.id.buttonUser);
        botonTareas = (Button) findViewById(R.id.buttonTareas);
        buttonTareaDetalle = (Button) findViewById(R.id.buttonTareaDetalle);

        buttonRol = (Button) findViewById(R.id.buttonRol);
        buttonGrupos = (Button) findViewById(R.id.buttonGrupos);
        buttonSprint = (Button) findViewById(R.id.buttonSprint);
        buttonUserHistory = (Button) findViewById(R.id.buttonUserHistory);

        botonUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarListaUser(view);

            }
        });

        botonTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarListaTarea(view);

            }
        });

        buttonTareaDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verTareaDetalle(view);

            }
        });


        buttonRol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gestionRol(v);
            }
        });


        buttonGrupos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gestionGrupos(v);
            }
        });


        buttonSprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gestionSprint(v);
            }
        });

        buttonUserHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gestionUserHistory(v);
            }
        });

    }

    private void gestionUserHistory(View v) {
        Intent intentUH = new Intent(this, UserHistoryActivity.class);
        startActivity(intentUH);
    }

    private void gestionSprint(View v) {

        String url = "http://" + ServicioActivity.ip + "/Sgpis2/webresources/spis2.entities.sprint";
        JSONArray tareasJSON = ServicioActivity.get(this, url);
        String tareaLista;
        if (tareasJSON != null) {
            for (int i = 0; i < tareasJSON.length(); i++) {
                JSONObject obj = null;
                try {
                    obj = tareasJSON.getJSONObject(i);
                    tareaLista = "\nID Tarea: " + obj.getString("idSprint");
                    tareaLista += "\nNombre: " + obj.getString("nombre");
                    tareaLista += "\nDescripcion: " + obj.getString("descripcion");
                    tareaLista += "\nFecha Inicio: " + obj.getString("fechaInicio");
                    tareaLista += "\nFecha Fin: " + obj.getString("fechaFin");
                    Coleccion.add(tareaLista);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Intent intentS = new Intent(this, SprintsActivity.class);
            intentS.putExtra("Coleccion", Coleccion);
            startActivity(intentS);
        }
    }

    private void gestionGrupos(View v) {
        Intent intentG = new Intent(this, GruposActivity.class);
        startActivity(intentG);
    }

    private void gestionRol(View v) {
        Intent intentR = new Intent(this, RolesActivity.class);
        startActivity(intentR);
    }

    private void verTareaDetalle(View view) {

        Intent intentTD = new Intent(this, DetalleProyectoActivity.class);
        startActivity(intentTD);

    }

    public void cargarListaUser(View view) {
        String url = "http://" + ServicioActivity.ip + "/Sgpis2/webresources/spis2.entities.usuario";
        JSONArray usuariosJSON = ServicioActivity.get(this, url);
        String userLista;
        if (usuariosJSON != null) {
            for (int i = 0; i < usuariosJSON.length(); i++) {
                JSONObject obj = null;
                try {
                    obj = usuariosJSON.getJSONObject(i);
                    userLista = "\nID: " + obj.getString("idUsuario");
                    userLista += "\nNombre: " + obj.getString("nombre");
                    userLista += "\nApellido: " + obj.getString("apellido");
                    userLista += "\nEmail: " + obj.getString("email");
                    Coleccion.add(userLista);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Intent intent = new Intent(this, UserActivity.class);
            intent.putExtra("Coleccion", Coleccion);
            startActivity(intent);
        }
    }

    public void cargarListaTarea(View view) {
        String url = "http://" + ServicioActivity.ip + "/Sgpis2/webresources/spis2.entities.sprint";
        JSONArray tareasJSON = ServicioActivity.get(this, url);
        String tareaLista;
        if (tareasJSON != null) {
            for (int i = 0; i < tareasJSON.length(); i++) {
                JSONObject obj = null;
                try {
                    obj = tareasJSON.getJSONObject(i);
                    tareaLista = "\nID Tarea: " + obj.getString("idSprint");
                    tareaLista += "\nNombre: " + obj.getString("nombre");
                    tareaLista += "\nDescripcion: " + obj.getString("descripcion");
                    tareaLista += "\nFecha Inicio: " + obj.getString("fechaInicio");
                    tareaLista += "\nFecha Fin: " + obj.getString("fechaFin");
                    Coleccion.add(tareaLista);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            Intent intent = new Intent(this, TareasActivity.class);
            intent.putExtra("Coleccion", Coleccion);
            startActivity(intent);

        }

    }
}
