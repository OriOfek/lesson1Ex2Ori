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
    boolean isValid;


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
        isValid = true;
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

    private void checkvalid(EditText et, String a) {
        float value = 1;
        String str = et.getText().toString();
        if (str.equals("")||str.equals(".")||str.equals("-")) {
            isValid = false;
            Toast.makeText(this, "please put a number in "+a, Toast.LENGTH_SHORT).show();
        }
        else
        {
            value = Float.parseFloat(et.getText().toString());
        }

        if (a.equals("a") && value == 0)
        {
            isValid = false;
            Toast.makeText(this, "a need to be defrent from 0 (it won't be a parabole)", Toast.LENGTH_SHORT).show();

        }
    }

    public void solve(View view) {
        isValid = true;
        checkvalid(eta,"a");
        checkvalid(etb,"b");
        checkvalid(etc,"c");
        if (isValid)
        {
            a = Float.valueOf(eta.getText().toString());
            b = Float.valueOf(etb.getText().toString());
            c = Float.valueOf(etc.getText().toString());

            si.putExtra("a",a);
            si.putExtra("b",b);
            si.putExtra("c",c);

            startActivityForResult(si, ANSWERS_NUMBER);
        }
    }
    private  String editNumers(float number)
    {
        if((((float)((int)number)) == (float)number))
        {
            return String.valueOf((int)number);
        }
        return String.valueOf(number);
    }

    @Override
    protected void onActivityResult(int source, int good, @Nullable Intent data_back) {
        super.onActivityResult(source, good, data_back);
        float r1;
        float r2;
        if (data_back != null) {
            // check that it is the SecondActivity with an OK result
            if (source == ANSWERS_NUMBER) {
                if (good == RESULT_OK) {

                    r1 = data_back.getFloatExtra("result1", 1);
                    r2 = data_back.getFloatExtra("result2", 1);

                    // set text view with string
                    result1.setText(editNumers(Float.valueOf(String.valueOf(r1))));
                    result2.setText(editNumers(Float.valueOf(String.valueOf(r2))));

                }
            }
        }

    }
}