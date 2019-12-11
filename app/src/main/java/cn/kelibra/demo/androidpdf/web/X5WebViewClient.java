package cn.kelibra.demo.androidpdf.web;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.kelibra.demo.androidpdf.weight.KWebviewActivity;


/**
 * @author: kezy
 * @create_time 2019/10/30
 * @description:
 */
public class X5WebViewClient extends WebViewClient {


    private Context context;

    public X5WebViewClient(Context context) {
        this.context = context;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {

        if (url != null) {
            if ((!url.startsWith("file:")) && url.endsWith(".pdf")) {
                showPdfWebView(webView, url);
            } else {
//                webView.loadUrl(url);
                openNewWebview(webView, url);
                return true;
            }
        }
        return super.shouldOverrideUrlLoading(webView, url);
    }

    private void openNewWebview(WebView webView, String url) {
        Intent intent = new Intent(context, KWebviewActivity.class);
        intent.putExtra("h5_url", url);
        context.startActivity(intent);
    }

    @Override
    public void onPageStarted(WebView webView, String url, Bitmap bitmap) {

        if (url != null) {
            if ((!url.startsWith("file:")) && url.endsWith(".pdf")) {
                showPdfWebView(webView, url);
            }
        }

        super.onPageStarted(webView, url, bitmap);
    }

    @Override
    public void onPageFinished(WebView webView, String url) {
        super.onPageFinished(webView, url);
    }

    private void showPdfWebView(WebView view, String pdfUrl) {

        Log.e("====msg_web_pdf", " ------ 加载pdf文件  " + pdfUrl);


        WebSettings webSettings = view.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webSettings.setPluginState(WebSettings.PluginState.ON);
        /**
         * 简单来说，该项设置决定了JavaScript能否访问来自于任何源的文件标识的URL。
         * 比如我们把PDF.js放在本地assets里，然后通过一个URL跳转访问来自于任何URL的PDF，所以这里我们需要将其设置为true。
         * 而一般情况下，为了安全起见，是需要将其设置为false的。
         */
        webSettings.setAllowUniversalAccessFromFileURLs(true);

        //支持缩放
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setDisplayZoomControls(false);//不显示缩放按钮

        view.loadUrl("file:///android_asset/pdf.html?" + pdfUrl);
//        view.loadUrl("file:///android_asset/pdf.html?");

    }
}
