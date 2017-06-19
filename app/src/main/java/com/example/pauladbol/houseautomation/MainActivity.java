package com.example.pauladbol.houseautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button luzCozinha = (Button) findViewById(R.id.luzCozinha);

        Button luzDorm1 = (Button) findViewById(R.id.luzDorm1);

        Button luzSala = (Button) findViewById(R.id.luzSala);

        Button luzDorm2 = (Button) findViewById(R.id.luzDorm2);

        luzCozinha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterarLampada("cozinha");
            }
        });

        luzDorm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterarLampada("dorm1");
            }
        });

        luzSala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterarLampada("sala");
            }
        });

        luzDorm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterarLampada("dorm2");
            }
        });

    }

    public void alterarLampada (String comodo)
    {

        String url = "https://...../&comodo=" + comodo;

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

    }
