package com.example.lesson1ex2ori;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    float a,b,c;
    Intent si;
    TextView result1,result2;
    final int ANSWERS_NUMBER = 345; ;
    EditText eta,etb,etc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = 0;
        b = 0;
        c = 0;

        eta = (EditText)findViewById(R.id.a);
        etb = (EditText)findViewById(R.id.b);
        etc = (EditText)findViewById(R.id.c);
        result1 = (TextView)findViewById(R.id.result1);
        result2 = (TextView)findViewById(R.id.result2);
        si = new Intent(this,Answers.class);
    }

    public void generate(View view) {
        Random rnd = new Random();
        DecimalFormat df = new DecimalFormat();
        a = (Float.MIN_VALUE) + rnd.nextFloat() * (Float.MAX_VALUE - (Float.MIN_VALUE));
        b = (Float.MIN_VALUE) + rnd.nextFloat() * (Float.MAX_VALUE - (Float.MIN_VALUE));
        c = (Float.MIN_VALUE) + rnd.nextFloat() * (Float.MAX_VALUE - (Float.MIN_VALUE));

        eta.setText(String.valueOf(a));
        etb.setText(String.valueOf(b));
        etc.setText(String.valueOf(c));
    }

    public void solve(View view) {
        if(!(eta.getText().toString().equals("") && etb.getText().toString().equals("") && etc.getText().toString().equals("")))
        {
            a = Float.valueOf(eta.getText().toString());
            b = Float.valueOf(etb.getText().toString());
            c = Float.valueOf(etc.getText().toString());
        }
        else
        {
            Toast.makeText(MainActivity.this, "please enter a number!", Toast.LENGTH_LONG).show();
        }

        si.putExtra("a",a);
        si.putExtra("b",b);
        si.putExtra("c",c);

        startActivityForResult(si, ANSWERS_NUMBER);
    }

    @Override
    protected void onActivityResult(int source, int good, @Nullable Intent data_back) {
        super.onActivityResult(source, good, data_back);
        double r1;
        double r2;
        if (data_back != null) {
            // check that it is the SecondActivity with an OK result
            if (source == ANSWERS_NUMBER) {
                if (good == RESULT_OK) {

                    r1 = data_back.getDoubleExtra("result1", 1);
                    r2 = data_back.getDoubleExtra("result2", 1);

                    // set text view with string
                    result1.setText(String.valueOf(r1));
                    result2.setText(String.valueOf(r2));

                }
            }
        }

    }
}