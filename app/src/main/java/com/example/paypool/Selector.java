package com.example.paypool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Selector extends AppCompatActivity {
    Button bta,btb;//pay and recieve buttons

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
        bta=findViewById(R.id.button5);
        btb=findViewById(R.id.button6);
        //pay section starts here .....
        bta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ibs=new Intent(getApplicationContext(),Paypool.class);
                startActivity(ibs);

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
