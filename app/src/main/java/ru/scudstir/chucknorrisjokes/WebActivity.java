package ru.scudstir.chucknorrisjokes;
import android.content.Intent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebActivity extends AppCompatActivity {
    private WebView browser;
    private Button jokeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        jokeButton = findViewById(R.id.b_jokes_for_web);
        jokeButton.setOnClickListener(jokeListener);

        browser = findViewById(R.id.wv_browser);
        browser.setWebViewClient(new MyWebViewClient());
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setLoadWithOverviewMode(true);
        browser.getSettings().setUseWideViewPort(true);
        browser.loadUrl("http://www.icndb.com/api/");
    }
    View.OnClickListener jokeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(WebActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };

    @Override
    public void onBackPressed() {
        if(browser.canGoBack()) {
            browser.goBack();
        } else {
            super.onBackPressed();
        }
    }
    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request){
            view.loadUrl(request.getUrl().toString());
            return true;
        }

    }
}
