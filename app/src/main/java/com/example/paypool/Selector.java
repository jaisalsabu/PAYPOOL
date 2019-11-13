package com.example.paypool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import net.glxn.qrgen.android.QRCode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Selector extends AppCompatActivity {
    Button bta,btb;//pay and recieve buttons
    SharedPreferences sharedPreferences;
    TextView tx6;
    String shp,balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
        bta=findViewById(R.id.button5);
        btb=findViewById(R.id.button6);
        tx6=findViewById(R.id.textView6);
        //pay section starts here .....
        sharedPreferences = getSharedPreferences("asd", MODE_PRIVATE);
        shp = sharedPreferences.getString("payid", "****");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("payeride",shp);
        editor.apply();
        tx6.setText(shp);
        bta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://intown-film.000webhostapp.com/Paypool/balretrival.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //If we are getting success from server
                                Toast.makeText(Selector.this, response, Toast.LENGTH_LONG).show();
                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject json_obj = jsonArray.getJSONObject(i);
                                        balance = json_obj.getString("wallbal");
                                        Intent izs=new Intent(getApplicationContext(),mywallet.class);
                                        izs.putExtra("bal",balance);
                                        startActivity(izs);
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

                        params.put("id",shp);

                        //returning parameter
                        return params;
                    }
                };

                //Adding the string request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(Selector.this);
                requestQueue.add(stringRequest);

            }
        });
        btb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ics=new Intent(getApplicationContext(),RECIVEPOOL.class);
                startActivity(ics);
            }
        });
    }
}
