package com.example.paypool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Selector extends AppCompatActivity {
    Button bta,btb;//pay and recieve buttons
    SharedPreferences sharedPreferences;
    TextView tx6;
    String shp;

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
        tx6.setText(shp);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("payerid",shp);
        editor.apply();
        bta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent izs=new Intent(getApplicationContext(),Paypool.class);
                startActivity(izs);

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
