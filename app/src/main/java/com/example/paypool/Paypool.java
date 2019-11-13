package com.example.paypool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.integration.android.IntentIntegrator;

public class Paypool extends AppCompatActivity {
    Button btf,bth;
    TextView trey,txt11,trez;
    EditText  txt3;
    IntentIntegrator qrscan;
    SharedPreferences sharedPreferences;
    String pyrid,recid,amount,balance;

    IntentResult result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypool);
        txt3 = findViewById(R.id.editText7);//amount
        trey = findViewById(R.id.textView8);
        trez=findViewById(R.id.textView15);
        txt11 = findViewById(R.id.textView9);
        btf = findViewById(R.id.button3);
        bth = findViewById(R.id.button8);
        qrscan = new IntentIntegrator(this);
        sharedPreferences = getSharedPreferences("asd", MODE_PRIVATE);
        pyrid = sharedPreferences.getString("payeride", "****");
        balance=sharedPreferences.getString("bala","#####");
        trez.setText(balance);
        trey.setText(pyrid);
        bth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),amount,Toast.LENGTH_LONG).show();
                qrscan.initiateScan();
            }
        });}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                recid = result.getContents();
                txt11.setText(result.getContents());
                amount=txt3.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("payido", pyrid);
                editor.putString("recido",recid);
                editor.putString("ammnt",amount);
                editor.apply();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
        btf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txt3.getText().toString().isEmpty() && (Integer.parseInt(txt3.getText().toString())<Integer.parseInt(balance))) {
                    Intent irr = new Intent(getApplicationContext(), confirmpay.class);
                    startActivity(irr);
                }
                else
                    {
                        Toast.makeText(getApplicationContext(),"INSUFFICIENT BALANCE",Toast.LENGTH_LONG).show();
                    }
            }
        });
    }}