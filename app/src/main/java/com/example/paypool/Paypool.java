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
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();

        /**
         * Set your logo here
         */
        // checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            /**
             * Merchant Name
             * eg: ACME Corp || HasGeek etc.
             */
            options.put("name", "Merchant Name");

            /**
             * Description can be anything
             * eg: Reference No. #123123 - This order number is passed by you for your internal reference. This is not the `razorpay_order_id`.
             *     Invoice Payment
             *     etc.
             */
            options.put("description", "Reference No. #123456");
            // options.put("order_id", "order_9A33XWu170gUtm");
            options.put("currency", "INR");

            /**
             * Amount is always passed in currency subunits
             * Eg: "500" = INR 5.00
             */
            options.put("amount", Integer.parseInt(txt1.getText().toString())*100);

            checkout.open(activity, options);
        } catch(Exception e) {
            // Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }
    public void onPaymentSuccess(String s) {
        Toast.makeText(Paypool.this,"PAYMENT SUCCESFULL",Toast.LENGTH_LONG).show();
    }


    public void onPaymentError(int i, String s) {
        Toast.makeText(Paypool.this, "PAYMENT UNSUCCESFULL", Toast.LENGTH_LONG).show();
    }

        @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                recid =result.getContents();
                txt11.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}
