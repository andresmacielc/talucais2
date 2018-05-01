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

    ArrayList<String> Coleccion = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_inicio);
        botonUsuarios = (Button) findViewById(R.id.buttonUser);
        botonUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarListaUser(view);

            }
        });
    }

    public void cargarListaUser(View view) {
        String url = "http://192.168.43.57:8080/Sgpis2/webresources/spis2.entities.usuario";
        JSONArray usuariosJSON = ServicioActivity.get(url);
        String userLista;
        for(int i=0; i<usuariosJSON.length(); i++)
        {
            JSONObject obj = null;
            try {
                obj = usuariosJSON.getJSONObject(i);
                userLista = "\nID: "+obj.getString("idUsuario");
                userLista += "\nNombre: "+obj.getString("nombre");
                userLista += "\nApellido: "+obj.getString("apellido");
                userLista += "\nEmail: "+obj.getString("email");
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
