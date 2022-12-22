package com.rchuck.Calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txt_result;
        EditText edtHeightFt, edtHeightInch, edtWeight;
        Button btn_continue;

        edtHeightFt = findViewById(R.id.edtHeightFt);
        edtHeightInch = findViewById(R.id.edtHeightInch);
        txt_result = findViewById(R.id.txt_result);
        btn_continue = findViewById(R.id.btn_continue);
        edtWeight = findViewById(R.id.edtWeight);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                int wt = Integer.parseInt(edtWeight.getText().toString());
                int ft = Integer.parseInt(edtHeightFt.getText().toString());
                int inch = Integer.parseInt(edtHeightInch.getText().toString());

                int totalInch = ft*12 + inch;
                double totalCm = totalInch * 2.53;
                double totalM = totalCm/100;
                double bmi = wt/(totalM*totalM);

                if(bmi > 25){
                    txt_result.setText("You are overweight");
                } else if(bmi<18){
                    txt_result.setText("You are underweight");
                } else{
                    txt_result.setText("You are healthy");
                }
            }
        });
    }
}