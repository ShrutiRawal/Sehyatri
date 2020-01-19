package com.example.codeutsava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class GraphActivity extends AppCompatActivity {
    private WebView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        graph = (WebView)findViewById(R.id.graph);
        WebSettings webSettings = graph.getSettings();
        webSettings.setJavaScriptEnabled(true);
        graph.loadUrl("https://console.firebase.google.com/u/0/project/codeutsava-15484/overview");
    }
}
