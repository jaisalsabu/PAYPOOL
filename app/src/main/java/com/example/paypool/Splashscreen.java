package com.example.paypool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class Splashscreen extends AppCompatActivity {
    ProgressBar rto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        rto=findViewById(R.id.progressBar);
        Thread spalsher = new Thread() {
            public void run() {
                try {
                    sleep(5*1000);
                    rto.setVisibility(View.INVISIBLE);
                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i);

                    finish();
                }
                catch(Exception e)
                {

                }

            }

        };
        spalsher.start();
    }
}
