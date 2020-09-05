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
    String result1Str, result2Str;
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


        abc.setText("a=" + editNumers(a) + " b=" + editNumers(b) + " c=" + editNumers(c));
        determinant = (float) ((b * b) - (4.0 * a * c));

        result1 = (float) ((-b + Math.pow(determinant, 0.5)) / (2.0 * a));
        result2 = (float) ((-b - Math.pow(determinant, 0.5)) / (2.0 * a));

        signa = getsign(String.valueOf(a));
        signb = getsign(String.valueOf(b));
        signc = getsign(String.valueOf(c));

        stra = editNumers(Float.valueOf(getstr(String.valueOf(a))));
        strb = editNumers(Float.valueOf(getstr(String.valueOf(b))));
        strc = editNumers(Float.valueOf(getstr(String.valueOf(c))));

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

        results.setText("result1=" + editNumers(result1) + " result2=" + editNumers(result2));
    }

    public void back(View view) {
        gi.putExtra("result1", result1);
        gi.putExtra("result2", result2);
        setResult(RESULT_OK, gi);
        finish();
    }

    private  String editNumers(float number)
    {
        if((((float)((int)number)) == (float)number))
        {
            return String.valueOf((int)number);
        }
        return String.valueOf(number);
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
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}