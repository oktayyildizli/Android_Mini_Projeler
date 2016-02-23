package com.ozcaan11.android_mini_projeler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HesapMakinesiActivity extends AppCompatActivity {

    float result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hesap_makinesi);
        final EditText n1 = (EditText) findViewById(R.id.txtNum1);
        final EditText n2 = (EditText) findViewById(R.id.txtNum2);

        Button btnArti = (Button) findViewById(R.id.btnArti);
        Button btnEksi = (Button) findViewById(R.id.btnEksi);
        Button btnCarp = (Button) findViewById(R.id.btnCarp);
        Button btnBol = (Button) findViewById(R.id.btnBol);


        n1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (! hasFocus){
                    if (n1.getText().toString().equals("")){
                        Toast.makeText(HesapMakinesiActivity.this, "boş bırakılamaz", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        n2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (! hasFocus){
                    if (n2.getText().toString().equals("")){
                        Toast.makeText(HesapMakinesiActivity.this, "boş bırakılamaz", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnArti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = Float.parseFloat(n1.getText().toString()) + Float.parseFloat(n2.getText().toString());
                Toast.makeText(HesapMakinesiActivity.this, "Sonuç: " + result, Toast.LENGTH_SHORT).show();

            }

        });
        btnEksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = Float.parseFloat(n1.getText().toString()) - Float.parseFloat(n2.getText().toString());
                Toast.makeText(HesapMakinesiActivity.this, "Sonuç: " + result, Toast.LENGTH_SHORT).show();
            }
        });
        btnCarp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = Float.parseFloat(n1.getText().toString()) * Float.parseFloat(n2.getText().toString());
                Toast.makeText(HesapMakinesiActivity.this, "Sonuç: " + result, Toast.LENGTH_SHORT).show();
            }
        });
        btnBol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = Float.parseFloat(n1.getText().toString()) / Float.parseFloat(n2.getText().toString());
                Toast.makeText(HesapMakinesiActivity.this, "Sonuç: " + result, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
