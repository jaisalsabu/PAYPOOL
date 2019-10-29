package com.example.paypool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class QRGEN extends AppCompatActivity {
    Button btc;
    ImageView img;
    Bitmap myBitmap;
    Intent Intent;
    String name,Pid;
    SharedPreferences sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgen);
        sharedPreference = getSharedPreferences("asd", MODE_PRIVATE);
        btc=findViewById(R.id.button7);
        img=findViewById(R.id.imageView);
        name=sharedPreference.getString("name","*****");
        btc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://intown-film.000webhostapp.com/Paypool/paypoolpidretrival.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //If we are getting success from server
                                Toast.makeText(QRGEN.this, response, Toast.LENGTH_LONG).show();
                                try {
                                    JSONArray jsonArray=new JSONArray(response);
                                    for(int i=0;i<jsonArray.length();i++){
                                        JSONObject json_obj = jsonArray.getJSONObject(i);
                                        Pid=json_obj.getString("Pid");
                                        myBitmap= QRCode.from(Pid).bitmap();
                                        img.setImageBitmap(myBitmap);
                                        Toast.makeText(getApplicationContext(),Pid,Toast.LENGTH_LONG).show();

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

                        params.put("name",name);

                        //returning parameter
                        return params;
                    }
                };
                Intent iso=new Intent(getApplicationContext(),Selector.class);
                startActivity(iso);
                //Adding the string request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(QRGEN.this);
                requestQueue.add(stringRequest);
            }


        });

    }
}