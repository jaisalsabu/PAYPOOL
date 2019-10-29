package com.example.paypool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRSCAN extends AppCompatActivity {
    Button btd;
    TextView txt11;
    String payid;
    IntentIntegrator qrscan;
    SharedPreferences sharedPreferenc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);
        btd = findViewById(R.id.button8);
        txt11 = findViewById(R.id.textView);
        qrscan = new IntentIntegrator(this);
        sharedPreferenc = getSharedPreferences("asd", MODE_PRIVATE);
        btd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferenc.edit();
                editor.putString("Payerid", txt11.getText().toString());
                editor.apply();
                qrscan.initiateScan();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                txt11.setText(result.getContents());
                payid=txt11.getText().toString();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
