package com.example.lesson1ex2ori;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class Answers extends AppCompatActivity {
    Intent gi;
    String url;
    float a, b, c, determinant;
    WebView wv;
    String signa;
    String signb;
    String signc;
    String stra;
    String strb;
    String strc;
    float result1, result2;
    TextView abc, results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);

        wv = (WebView) findViewById(R.id.wv);
        abc = (TextView) findViewById(R.id.abc);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebViewClient(new MyBrowser());

        results = (TextView) findViewById(R.id.results);

        gi = getIntent();
        a = gi.getFloatExtra("a", 1);
        b = gi.getFloatExtra("b", 1);
        c = gi.getFloatExtra("c", 1);

        abc.setText("a=" + editNumbers(a) + " b=" + editNumbers(b) + " c=" + editNumbers(c));
        determinant = (float) ((b * b) - (4.0 * a * c));

        result1 = (float) ((-b + Math.sqrt(determinant)) / (2.0 * a));
        result2 = (float) ((-b - Math.sqrt(determinant)) / (2.0 * a));

        signa = getSign(String.valueOf(a));
        signb = getSign(String.valueOf(b));
        signc = getSign(String.valueOf(c));

        stra = editNumbers(Float.valueOf(getStr(String.valueOf(a))));
        strb = getStr(editNumbers(b));
        strc = getStr(editNumbers(c));

        if (strc.contains("*"))
        {
            strc = strc.replace("*","");
        }

        if(signa !="-")
        {
            url = "https://www.google.com/search?q="+ stra + "x%5E2" +signb+ strb+ "x" +signc+ strc + "&rlz=1C1CHBD_enIL872IL872&oq=x%5E2%2B5x&aqs=chrome.1.69i57j0l7.15066j0j7&sourceid=chrome&ie=UTF-8";
        }
        else {
            url = "https://www.google.com/search?q=" +signa +stra+ "*" + "x%5E2" + signb+strb + "x"+signc + strc + "&rlz=1C1CHBD_enIL872IL872&oq=x%5E2%2B5x&aqs=chrome.1.69i57j0l7.15066j0j7&sourceid=chrome&ie=UTF-8";
        }
        wv.loadUrl(url);

        results.setText("result1=" + editNumbers(result1) + " result2=" + editNumbers(result2));
    }

    public void back(View view) {
        gi.putExtra("result1", result1);
        gi.putExtra("result2", result2);
        setResult(RESULT_OK, gi);
        finish();
    }

    private  String editNumbers(float number)
    {
        if(Float.isNaN(number)) {
            return ("no solution");
        }
        if((((float)((int)number)) == (float)number))
        {
            return String.valueOf((int)number);
        }
        return String.valueOf(number);
    }
    /*
    Input: edit text
    Output: The sign
    read the edit text and decide what is the sign
     */
    private String getSign(String str) {
        String sign = "";

        if (str.contains("-"))
        {
            sign ="-";
        }
        else
        {
            sign ="%2B";
        }
        return sign;
    }

    private String getStr(String number)
    {
        float value = 0;
        String answer ="";

        value = Float.parseFloat(number);
        answer = number;
        if (answer.contains("-"))
        {
            answer= answer.replace("-","");
        }
        if (value == 0)
        {
            answer = answer + "*";
        }
        return answer;
    }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}