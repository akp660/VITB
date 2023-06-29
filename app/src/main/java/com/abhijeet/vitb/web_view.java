package com.abhijeet.vitb;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class web_view extends Activity {

    private WebView mywebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        ImageView imageView = findViewById(R.id.home);
        imageView.setOnClickListener(view -> home());

        mywebView=(WebView) findViewById(R.id.webview);
        mywebView.setWebViewClient(new WebViewClient());
        mywebView.loadUrl("https://vtop.vitbhopal.ac.in/vtop/initialProcess");
        WebSettings webSettings=mywebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }

    public static class mywebClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){
            super.onPageStarted(view,url,favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,String url){
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed(){
        if(mywebView.canGoBack()) {
            mywebView.goBack();
        }
        else{
            super.onBackPressed();
        }
    }

    public void home(){
        Intent intent = new Intent(web_view.this,MainActivity.class);
        startActivity(intent);
    }

}
