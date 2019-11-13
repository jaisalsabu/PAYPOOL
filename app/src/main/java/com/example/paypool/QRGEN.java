package com.example.paypool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class QRGEN extends AppCompatActivity {
    Button btc,bth;
    ImageView img;
    Bitmap myBitmap;
    TextView trex;
    String name,Pide;
    SharedPreferences sharedPreference;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgen);
        sharedPreference = getSharedPreferences("asd", MODE_PRIVATE);
        btc = findViewById(R.id.button7);
        trex = findViewById(R.id.textView7);
        bth = findViewById(R.id.button12);
        img = findViewById(R.id.imageView);
        name = sharedPreference.getString("name", "*****");
        btc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://intown-film.000webhostapp.com/Paypool/paypoolpidretrival.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //If we are getting success from server
                                Toast.makeText(QRGEN.this, response, Toast.LENGTH_LONG).show();
                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject json_obj = jsonArray.getJSONObject(i);
                                        Pide = json_obj.getString("Pid");
                                        trex.setText(Pide);
                                        SharedPreferences.Editor editor = sharedPreference.edit();
                                        editor.putString("payid", Pide);
                                        editor.apply();
                                        myBitmap = QRCode.from(Pide).bitmap();
                                        img.setImageBitmap(myBitmap);
                                        Toast.makeText(getApplicationContext(), Pide, Toast.LENGTH_LONG).show();
                                        saveImageToExternalStorage(myBitmap);

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

                        params.put("name", name);

                        //returning parameter
                        return params;
                    }
                };

                //Adding the string request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(QRGEN.this);
                requestQueue.add(stringRequest);
            }


        });
        bth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iso = new Intent(getApplicationContext(), Selector.class);
                startActivity(iso);

            }
        });
    }
       private void saveImageToExternalStorage(Bitmap finalBitmap) {
            String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString();
            File myDir = new File(root + "/saved_images_1");
            myDir.mkdirs();
            //Random generator = new Random();
            //int n = 10000;
            //n = generator.nextInt(n);
            String fname = "Imageqr.jpg";
            File file = new File(myDir, fname);
            if (file.exists())
                file.delete();
            try {
                FileOutputStream out = new FileOutputStream(file);
                finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }


// Tell the media scanner about the new file so that it is
// immediately available to the user.
            MediaScannerConnection.scanFile(this, new String[]{file.toString()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("ExternalStorage", "Scanned " + path + ":");
                            Log.i("ExternalStorage", "-> uri=" + uri);
                        }
                    });
                    }
    }

