package com.example.paypool;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Paypool extends AppCompatActivity {
    Button btf,bth;
    TextView trey,txt11;
    EditText txt1, txt2, txt3;
    IntentIntegrator qrscan;
    SharedPreferences sharedPreferences;
    String pyrid,recid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypool);
        txt1 = findViewById(R.id.editText7);//phone number
        txt2 = findViewById(R.id.editText8);//details
        txt3 = findViewById(R.id.editText9);//amount
        trey = findViewById(R.id.textView8);
        txt11=findViewById(R.id.textView9);
        btf = findViewById(R.id.button3);
        bth = findViewById(R.id.button8);
        qrscan = new IntentIntegrator(this);
        sharedPreferences = getSharedPreferences("asd", MODE_PRIVATE);
        pyrid = sharedPreferences.getString("payerid", "****");
        trey.setText(pyrid);
        bth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrscan.initiateScan();
            }
        });
        btf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    //storing values to database
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "//url to be added",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
//If we are getting success from server
                                    startPayment();
                                    try {
                                        JSONArray jsonArray = new JSONArray(response);
                                        for (int i = 0; i < jsonArray.length(); i++) {
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

                            }) {

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            //Adding parameters to request

                            params.put("amount", txt3.getText().toString());
                            params.put("details", txt2.getText().toString());
                            params.put("payerid", pyrid);
                            params.put("reciverid", recid);
                           // params.put("payerid",payerid);
                            //params.put("recid",recid);

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

    public void startPayment() {
        /**
         * You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Razorpay Corp");
            options.put("description", "Demoing Charges");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://rzp-mobile.s3.amazonaws.com/images/rzp.png");
            options.put("currency", "INR");

            String payment = editTextPayment.getText().toString();

            double total = Double.parseDouble(payment);
            total = total * 100;
            options.put("amount", total);

            JSONObject preFill = new JSONObject();
            preFill.put("email", "sikander@gkmit.co");
            preFill.put("contact", "9680224241");

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        Toast.makeText(this, "Payment successfully done! " + razorpayPaymentID, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPaymentError(int code, String response) {
        try {
            Toast.makeText(this, "Payment error please try again", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("OnPaymentError", "Exception in onPaymentError", e);
        }
    }
}
