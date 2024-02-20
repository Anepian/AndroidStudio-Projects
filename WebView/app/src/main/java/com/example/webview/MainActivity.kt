package com.example.webview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val webView=findViewById<WebView>(R.id.webView1)
        val myWebSettings=webView.settings
        myWebSettings.javaScriptEnabled=true
        myWebSettings.domStorageEnabled=true
        webView.loadUrl("file:///android_asset/index.html")
    }
}