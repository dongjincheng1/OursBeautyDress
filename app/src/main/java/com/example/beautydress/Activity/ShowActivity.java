package com.example.beautydress.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.beautydress.R;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_layout);

        WebView webView = (WebView) findViewById(R.id.webview_id);
        WebSettings settings = webView.getSettings();
        //settings.setJavaScriptEnabled(true);

        WebViewClient client = new WebViewClient();
        webView.setWebViewClient(client);
        webView.loadUrl(getIntent().getStringExtra("detailUrl"));
    }
}
