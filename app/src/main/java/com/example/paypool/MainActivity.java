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

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {
    Button btn,btg;
    EditText a1,a2,a3,a4;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a1=findViewById(R.id.editText);//name
        a2=findViewById(R.id.editText4);//phone
        a3=findViewById(R.id.editText2);//password
        a4=findViewById(R.id.editText3);//confirmpassword
        btg=findViewById(R.id.button10);
        btg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ids=new Intent(getApplicationContext(),Login.class);
                startActivity(ids);
            }
        });
        btn=findViewById(R.id.button);//registerbutton
        sharedPreferences = getSharedPreferences("asd", MODE_PRIVATE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //checking wether edittext is empty and passwords are entered same
                if(!(a1.getText().toString().isEmpty()||a2.getText().toString().isEmpty()||a3.getText().toString().isEmpty()||a4.getText().toString().isEmpty())&&(a3.getText().toString().equals(a4.getText().toString())))

                {
                        //storing values to database
                    StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://intown-film.000webhostapp.com/Paypool/paypoolreg.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
//If we are getting success from server

                                    Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("name", a1.getText().toString());
                                    editor.apply();
                                   Intent ias=new Intent(getApplicationContext(),QRGEN.class);
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

                            params.put("name",a1.getText().toString());
                            params.put("phone",a2.getText().toString());
                            params.put("password",a3.getText().toString());

                         //returning parameter
                            return params;
                        }
                    };


                        //Adding the string request to the queue
                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    requestQueue.add(stringRequest);

                }
                else
                {
                    //if values are incorrect
                    new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("INCORRECT VALUES")
                            .show();

                }
            }
        });
    }
}

