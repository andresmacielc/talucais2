package com.example.carlos.appsgp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editTextEmail, editTextPass;
    Button buttonIngresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        editTextEmail= findViewById(R.id.editTextEmail);
        editTextPass= findViewById(R.id.editTextPass);
        buttonIngresar= findViewById(R.id.buttonIngresar);

        buttonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consumirServicio();

            }
        });
    }
    public void consumirServicio(){
        // ahora ejecutaremos el hilo creado
        String email= editTextEmail.getText().toString();
        String pass= editTextPass.getText().toString();

        ServicioTask servicioTask= new ServicioTask(this,
                "http://localhost:8080/Sgpis2/webresources/spis2.entities.usuario/login",email,pass);
        servicioTask.execute();
    }

}
