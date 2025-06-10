package com.example.webviewapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private SharedPreferences preferences;
    private static final String LAST_URL_KEY = "last_url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        preferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowFileAccess(true);

        CookieManager.getInstance().setAcceptCookie(true);

        String lastUrl = preferences.getString(LAST_URL_KEY, "https://wdsdesigngroup.com/fcards/");
        webView.loadUrl(lastUrl);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                preferences.edit().putString(LAST_URL_KEY, url).apply();
            }
        });
    }
}
