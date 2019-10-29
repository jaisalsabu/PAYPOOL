package com.example.paypool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Paypool extends AppCompatActivity {
    Button btf;
    EditText txt1,txt2,txt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypool);
        txt1=findViewById(R.id.editText7);//phone number
        txt2=findViewById(R.id.editText8);//details
        txt3=findViewById(R.id.editText9);//amount
        btf=findViewById(R.id.button3);
        btf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    //storing values to database
                    StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://intown-film.000webhostapp.com/Paypool/paypoolreg.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
//If we are getting success from server

                                    Toast.makeText(Paypool.this,response,Toast.LENGTH_LONG).show();
                                    Intent ias=new Intent(getApplicationContext(),QRSCAN.class);
                                    startActivity(ias);

                                    try {
                                        JSONArray jsonArray=new JSONArray(response);
                                        for(int i=0;i<jsonArray.length();i++){
                                            JSONObject json_obj = jsonArray.getJSONObject(i);

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

                            }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<>();
                            //Adding parameters to request

                            params.put("amount",txt3.getText().toString());
                            params.put("details",txt2.getText().toString());

                            //returning parameter
                            return params;
                        }
                    };


                    //Adding the string request to the queue
                    RequestQueue requestQueue = Volley.newRequestQueue(Paypool.this);
                    requestQueue.add(stringRequest);

                }

            }
        });
    }
}
