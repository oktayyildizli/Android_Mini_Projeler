package com.ozcaan11.android_mini_projeler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FaktoriyelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faktoriyel);
        final EditText num = (EditText) findViewById(R.id.txtFakt);
        Button btnFakt = (Button) findViewById(R.id.btnFakt);
        btnFakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float number = Float.parseFloat(num.getText().toString());
                int sonuc = (int) facktoriyel(number);
                Toast.makeText(FaktoriyelActivity.this, "Sonuç: " + sonuc, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private float facktoriyel(float number) {
        if (number == 0 || number == 1) {
            return 1;
        } else {
            return number * facktoriyel(number - 1);
        }
    }
}
