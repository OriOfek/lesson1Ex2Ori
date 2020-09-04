package com.example.lesson1ex2ori;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
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
    double result1, result2;
    TextView abc, results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);

        wv = (WebView) findViewById(R.id.wv);
        abc = (TextView) findViewById(R.id.abc);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebViewClient(new MyWebViewClient());
        results = (TextView) findViewById(R.id.results);

        gi = getIntent();
        a = gi.getFloatExtra("a", 1);
        b = gi.getFloatExtra("b", 1);
        c = gi.getFloatExtra("c", 1);

        abc.setText("a=" + a + " b=" + b + " c=" + c);
        determinant = (float) ((b * b) - (4.0 * a * c));

        result1 = (-b + Math.pow(determinant, 0.5)) / (2.0 * a);
        result2 = (-b - Math.pow(determinant, 0.5)) / (2.0 * a);

        signa = getsign(String.valueOf(a));
        signb = getsign(String.valueOf(b));
        signc = getsign(String.valueOf(c));

        stra = getstr(String.valueOf(a));
        strb = getstr(String.valueOf(b));
        strc = getstr(String.valueOf(c));

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

        results.setText("result1=" + result1 + " result2=" + result2);
    }

    public void back(View view) {
        gi.putExtra("result1", result1);
        gi.putExtra("result2", result2);
        setResult(RESULT_OK, gi);
        finish();
    }
    /*
    Input: edit text
    Output: The sign
    read the edit text and dexide what is the sign
     */
    private String getsign(String str) {
        String sign ="";

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

    private String getstr(String number)
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


    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}