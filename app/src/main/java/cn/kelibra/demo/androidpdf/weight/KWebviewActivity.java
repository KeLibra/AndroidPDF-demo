package cn.kelibra.demo.androidpdf.weight;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.kelibra.demo.androidpdf.CommonBaseActivity;
import cn.kelibra.demo.androidpdf.R;
import cn.kelibra.demo.androidpdf.web.X5WebChromeClient;
import cn.kelibra.demo.androidpdf.web.X5WebViewClient;


public class KWebviewActivity extends CommonBaseActivity {

    String h5_url;

    private WebView x5WebView;

    private boolean isOtherLink; // 是否是外部链接， 是的话走特殊处理

    @Override
    protected int getContentLayout() {
        return R.layout.activity_x5_webview;
    }


    @Override
    protected void initContentView() {
        super.initContentView();
        x5WebView = findViewById(R.id.layout_web);

        initWebView();

        if (isOtherLink) {
            try {
                findViewById(R.id.rl_web_layout).setFitsSystemWindows(true);
            } catch (Exception e) {
            }
        }


    }


    @Override
    protected void initData() {
        super.initData();

        if (h5_url != null) {
            x5WebView.loadUrl(h5_url);
        }
    }

    private void initWebView() {

        initWebSetting();


        x5WebView.setWebChromeClient(getWebChromeClient());

        if (isOtherLink) {
        } else {
            x5WebView.setWebViewClient(getWebViewCllient());
        }

//            x5WebView.setWebViewClient(getWebViewCllient());
    }

    private WebViewClient getWebViewCllient() {
        return new X5WebViewClient(this);
    }


    private WebChromeClient getWebChromeClient() {
        return new X5WebChromeClient();
    }

    private void initWebSetting() {

        WebSettings webSetting = x5WebView.getSettings();
        webSetting.setUseWideViewPort(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
    }

    protected void getIntentData() {

        h5_url = getIntent().getStringExtra("h5_url");

        Bundle bundle = this.getIntent().getExtras();
        isOtherLink = bundle.getBoolean("isOtherLink", false);
    }


    public void onBackPressed() {
        String lastUrl = "";

        if (x5WebView != null && x5WebView.getUrl() != null) {
            lastUrl = this.x5WebView.getUrl();
        }

        if (x5WebView != null && x5WebView.canGoBack()) {
            x5WebView.goBack();
            if (this.x5WebView.getUrl().equals(lastUrl)) {
                if (x5WebView.canGoBack()) {
                    x5WebView.goBack();
                } else {
                    finish();
                }
            } else {
                finish();
            }
        } else {
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (x5WebView != null) {
            x5WebView.removeAllViews();
            x5WebView.destroy();
            x5WebView = null;
        }
    }

    public void loadJsCallback(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (x5WebView != null) {
                    x5WebView.loadUrl(s);
                }
            }
        });
    }
}
