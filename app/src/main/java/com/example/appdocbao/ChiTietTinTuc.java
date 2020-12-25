package com.example.appdocbao;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ChiTietTinTuc extends AppCompatActivity {
    WebView wvData;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_tin_tuc);
        wvData = findViewById(R.id.wvData);

        String link = getIntent().getStringExtra("link");

        if(link != null){

            dialog = new ProgressDialog(this);
            dialog.setMessage("Loading....");
            dialog.setCancelable(false);
            dialog.show();
            wvData.setWebViewClient(new WebViewClient(){
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    dialog.dismiss();
                }


                @Override
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                    super.onReceivedError(view, request, error);
                    dialog.dismiss();
                }
            });


            wvData.loadUrl(link);
        }
    }
}
