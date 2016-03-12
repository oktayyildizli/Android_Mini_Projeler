package com.ozcaan11.android_mini_projeler;

import android.content.Intent;
import android.media.MediaActionSound;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnHesapMakinesi = (Button) findViewById(R.id.btnHesapMakinesi);
        Button btnHesapMakinesiPro = (Button) findViewById(R.id.btnHesapMakinesiPro);
        Button btnFaktoriyel = (Button) findViewById(R.id.btnFaktoriyel);
        btnHesapMakinesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HesapMakinesiActivity.class));
            }
        });
        btnHesapMakinesiPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HesapMakinesiProActivity.class));
            }
        });
        btnFaktoriyel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FaktoriyelActivity.class));
            }
        });
    }
}
