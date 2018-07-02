package com.example.carlos.appsgp;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 *Clase que servira para proveer servicio de conexion
 * actua como controlador.
**/
public class ServicioActivity extends AppCompatActivity {

    //variables a ser utilizadas
    final static private int duracionToast = 10;
    final static private int timeout = 5000;
<<<<<<< HEAD
    final static public String ip = "192.168.43.26:8080";
=======
    final static public String ip = "192.168.43.236:8080";
>>>>>>> 31113d261b184838df61fce3d9b4071b171ef901

    /*Metodo que realiza la conexion (POST) con el servidor y envia la respuesta del
    servidor*/
    @SuppressLint("WrongConstant")
    public static String postRespuesta(Context contexto,String targetURL, String urlParameters) {

        URL url;
        HttpURLConnection connection = null;
        try {
            //se establece una conexion con el servidor
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);

            //se envia una peticion al servidor con los parametros
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            //se obtiene la respuesta del servidor
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                //response.append('\n');
            }
            rd.close();
            return response.toString();

        } catch (Exception e) {
            Toast.makeText(contexto,"Error de conexión: "+e.getMessage(), duracionToast).show();
            e.printStackTrace();
        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    /*Metodo que realiza la conexion (POST) con el servidor y el servidor no responde, en este caso se
    retorna el codigo para saber si salio OK */
    @SuppressLint("WrongConstant")
    public static int postSinRespuesta(Context context,String targetURL,String urlParameters) {

        URL url;
        HttpURLConnection connection = null;
        try {

            //se establece una conexion con el servidor
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);

            //envia la peticion al servidor
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            //se retorna el codigo de la conexion, ej. 204 si salio ok
            return connection.getResponseCode();

        } catch (Exception e) {
            Toast.makeText(context,"Error de conexión",duracionToast).show();
            e.printStackTrace();
        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
        return 0;
    }
    /*Metodo que realiza la conexion (GET) con el servidor y el servidor responde con todos los
    datos */
    @SuppressLint("WrongConstant")
    public static JSONArray get(Context context, String targetURL) {
        URL url;
        HttpURLConnection connection = null;
        try {
            //establece la conexion con el servidor
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // obtiene la respuesta del servidor
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\n');
            }
            rd.close();
            JSONArray respJSON = new JSONArray(response.toString());
            //se retorna el array
            return respJSON;

        } catch (Exception e) {
            Toast.makeText(context,"Error de conexión "+e.getMessage(),duracionToast).show();
            e.printStackTrace();
        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    /*Metodo que realiza la conexion (delete) con el servidor y el servidor no responde,se retorna
    el codigo de la conexion */
    @SuppressLint("WrongConstant")
    public static int delete(Context contexto,String targetURL) {
        URL url;
        HttpURLConnection connection = null;
        try {

            //establece la conexion con el servidor
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            //se retorna el codigo
            return connection.getResponseCode();

        } catch (Exception e) {
            Toast.makeText(contexto,"Ocurrio un problema de conexion", duracionToast).show();
            e.printStackTrace();
        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
        return 0;
    }

    /*Metodo que realiza la conexion (put) con el servidor y el servidor no responde,se retorna
    el codigo de la conexion */
    @SuppressLint("WrongConstant")
    public static int put(Context contexto,String targetURL, String urlParameters) {
        URL url;
        HttpURLConnection connection = null;
        try {
            //establece la conexion con el servidor
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);

            //envia la peticion al servidor
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            return connection.getResponseCode();
        } catch (Exception e) {
            Toast.makeText(contexto,"Error de conexión.", duracionToast).show();
            e.printStackTrace();
        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
        return 0;
    }
    /*Metodo que realiza la conexion (Get) con el servidor en este caso se consulta por id
     por ende solo va a retornar un resultado*/
    public static String getId(String targetURL) {

        URL url;
        HttpURLConnection connection = null;
        try {

            //establece la conexion con el servidor
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // obtiene la respuesta del servidor
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
            rd.close();
            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }
}

