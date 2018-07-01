package com.example.carlos.appsgp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by carlos on 01/05/18.
 */

public class EditarUserActivity extends AppCompatActivity {
    Button buttonEditar;
    Button buttonGuardar;
    EditText editEditarId;
    EditText editTextUserName;
    EditText editTextUserApellido;
    EditText editTextUserEmail;
    EditText editTextUserPassword;
    EditText editTextIdGrupo;
    EditText editTextIdRol;
    Switch sEstado;
    JSONObject obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_usuario_editar);
        editEditarId = (EditText) findViewById(R.id.editTextEditarId);
        editTextUserName = (EditText) findViewById(R.id.editTextEditarNombre);
        editTextUserApellido = (EditText) findViewById(R.id.editTextApellido);
        editTextUserEmail = (EditText) findViewById(R.id.editTextEditarEmail);
        editTextUserPassword = (EditText) findViewById(R.id.editTextEditarPass);
        editTextIdGrupo = (EditText) findViewById(R.id.editTextEditarGrupo);
        editTextIdRol = (EditText) findViewById(R.id.editTextEditarRol);
        sEstado = (Switch) findViewById(R.id.switchEditarEstado);
        buttonEditar= (Button)findViewById(R.id.buttonEditar);
        buttonGuardar= (Button)findViewById(R.id.buttonEditarGuardar);
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listarInfo();
                buttonGuardar.setEnabled(true);

            }
        });
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editUser();

            }
        });
    }

  @SuppressLint("WrongConstant")
  public void listarInfo(){
      String mensaje;
      Boolean estado = false;
      String url = "http://"+ServicioActivity.ip+"/Sgpis2/webresources/spis2.entities.usuario/"+editEditarId.getText().toString();
      mensaje = ServicioActivity.getId(url);
      if(mensaje != null) {
          try {
              obj = new JSONObject(mensaje);
              if(obj.getString("status").equalsIgnoreCase("1")){
                  estado = true;
              }
              editTextUserName.setText(obj.getString("nombre"));
              editTextUserApellido.setText(obj.getString("apellido"));
              editTextUserEmail.setText(obj.getString("email"));
              editTextUserPassword.setText(obj.getString("password"));
              sEstado.setChecked(estado);
     	      editTextIdGrupo.setText(obj.getString("id_group"));
              editTextIdRol.setText(obj.getString("id_rol"));
              Toast.makeText(this,"MODIFIQUE LOS DATOS QUE DESEA Y PULSE GUARDAR", 2500).show();
              buttonGuardar.setEnabled(true);
          } catch (JSONException e) {
              e.printStackTrace();
          }
      }else{
          Toast.makeText(this,"El id NO EXISTE!!", 5).show();
      }
  }

    @SuppressLint("WrongConstant")
    public void editUser(){
      String url = "http://"+ServicioActivity.ip+"/Sgpis2/webresources/spis2.entities.usuario/"+editEditarId.getText().toString();
      int resp;
      int estado;
    JSONObject loginParams = new JSONObject();

    try {
        if(sEstado.isChecked()){
            estado = 1;
        }else{
            estado = 0;
        }
        loginParams.put("nombre", editTextUserName.getText().toString());
        loginParams.put("apellido", editTextUserApellido.getText().toString());
        loginParams.put("email", editTextUserEmail.getText().toString());
        loginParams.put("password", editTextUserPassword.getText().toString());
        loginParams.put("idUsuario", obj.getString("idUsuario"));
        loginParams.put("fechaCreacionUsuario", obj.getString("fechaCreacionUsuario"));
        loginParams.put("status", estado);
	    loginParams.put("id_group", editTextIdGrupo.getText().toString());
        loginParams.put("id_rol", editTextIdRol.getText().toString());
    } catch (JSONException e) {
        e.printStackTrace();
    }
    resp = ServicioActivity.put(this,url,loginParams.toString());
    if (resp != 204){
        Toast.makeText(this,"No se pudo editar, ocurrio un problema", 5).show();
        return;
    }
     Toast.makeText(this,"Edicion exitosa", 5).show();
    }
}
