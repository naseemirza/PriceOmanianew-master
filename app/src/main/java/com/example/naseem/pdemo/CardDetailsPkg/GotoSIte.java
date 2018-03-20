package com.example.naseem.pdemo.CardDetailsPkg;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.naseem.pdemo.R;

public class GotoSIte extends AppCompatActivity {

    WebView mywebview;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goto_site);

        mywebview = (WebView) findViewById(R.id.webview);
        mywebview.setWebViewClient(new MyWebViewClient());
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String url="https://uae.souq.com/ae-en/apple-iphone-7-with-facetime-32gb-4g-lte-rose-gold-certified-pre-owned-23364686/i/?phgid=1101l3dT8&pubref=priceomania||||&utm_source=affiliate_hub&utm_medium=cpt&utm_content=affiliate&utm_campaign=100l2&u_type=10&u_title=&u_c=&u_fmt=links&u_a=1100l20137&u_as=priceomania";
        mywebview.getSettings().setJavaScriptEnabled(true);
        mywebview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mywebview.loadUrl(url);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressDialog = new ProgressDialog(GotoSIte.this);
            progressDialog.setMessage("Please wait ...");
            progressDialog.setProgressStyle(90);
            progressDialog.show();
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        return true;
    }
}

