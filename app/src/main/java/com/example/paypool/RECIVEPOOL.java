package com.example.paypool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RECIVEPOOL extends AppCompatActivity {
    Button bte;
    String recrid;
    TextView trexe;
    String balance;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recivepool);
        bte = findViewById(R.id.button9);
        trexe=findViewById(R.id.textView19);
        sharedPreferences = getSharedPreferences("asd", MODE_PRIVATE);
        recrid = sharedPreferences.getString("payeride", "****");
        bte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    //storing values to database
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://intown-film.000webhostapp.com/Paypool/balretrival.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    //If we are getting success from server
                                    Toast.makeText(RECIVEPOOL.this, response, Toast.LENGTH_LONG).show();
                                    try {
                                        JSONArray jsonArray = new JSONArray(response);
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject json_obj = jsonArray.getJSONObject(i);
                                            balance = json_obj.getString("wallbal");
                                            trexe.setText(balance);

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    //error handling
                                }

                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            //Adding parameters to request

                            params.put("id", recrid);

                            //returning parameter
                            return params;
                        }
                    };

                    //Adding the string request to the queue
                    RequestQueue requestQueue = Volley.newRequestQueue(RECIVEPOOL.this);
                    requestQueue.add(stringRequest);

                }

            }


        });
    }}
