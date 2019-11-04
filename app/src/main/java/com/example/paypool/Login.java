package com.example.paypool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Login extends AppCompatActivity {
    EditText a5,a6;
    Button btm,bto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        a5=findViewById(R.id.editText5);//username
        a6=findViewById(R.id.editText6);//password
        btm=findViewById(R.id.button2);
        bto=findViewById(R.id.button4);
        btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(a5.getText().toString().isEmpty() || a6.getText().toString().isEmpty()))
                {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://intown-film.000webhostapp.com/Paypool/paypoollog.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(Login.this, response, Toast.LENGTH_LONG).show();
                                    if(response.equals("success")) {
                                        //If we are getting success from server

                                        Intent ibe= new Intent(getApplicationContext(),Selector.class);
                                        startActivity(ibe);

                                    }
                                    else
                                    {
                                        Toast.makeText(Login.this,"invalid",Toast.LENGTH_LONG).show();
                                    }
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

                            params.put("nameo",a5.getText().toString());
                            params.put("passwordo",a6.getText().toString());

                          //returning parameter
                            return params;
                        }
                    };
                         //Adding the string request to the queue
                    RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                    requestQueue.add(stringRequest);
                }
                else
                {
                    //if values are incorrect
                    new SweetAlertDialog(Login.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("INVALID CREDENTIALS")
                            .show();
                }
            }
        });

    }
}


