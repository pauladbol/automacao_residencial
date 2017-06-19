package com.example.pauladbol.houseautomation;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.OutputStream;
import java.net.HttpURLConnection;

/**
 * Created by pauladbol on 2017-06-12.
 */
public class WebManager {
    private String urlBase;

    public WebManager() {
        this.urlBase = "http://";
    }

    public void alterarLampada (Lampada lampada, String acao)
    {

        String url = "https://...../acao=" + acao + "&lampada=" + lampada;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                       // Toast.makeText("Lampada: " + acao, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(DetalheActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                       // Toast.makeText(getApplicationContext(),"Ocorreu um erro! Tente novamente mais tarde.",Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



//        String json = GsonFactory.createGson().toJson(lampada);
//
//        String path = this.getPath("paymentMethod", "saveCatalog");
//        HttpURLConnection connection = this.openConnection(path, "POST");
//
//        try
//        {
//            try (OutputStream output = connection.getOutputStream())
//            {
//                output.write(json.getBytes());
//                output.flush();
//            }
//
//            int code = connection.getResponseCode();
//            if (code != 200)
//            {
//                throw this.instantiateException(connection, code);
//            }
//        } catch (CommunicationException e)
//        {
//            throw e;
//        } catch (Exception e)
//        {
//            throw new CommunicationException("Falha na comunicação com o servidor da 4all", e);
//        } finally
//        {
//            connection.disconnect();
//        }
    }
}
