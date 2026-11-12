package ph.gov.bswm.farmpro;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebResourceRequest;
import android.content.Intent;
import android.net.Uri;
import androidx.webkit.WebViewAssetLoader;
import androidx.webkit.WebViewAssetLoader.AssetsPathHandler;
import androidx.webkit.WebViewClientCompat;
public class MainActivity extends Activity {
 private WebView web;
 private final String home="https://appassets.androidplatform.net/assets/index.html";
 @Override public void onCreate(Bundle b){super.onCreate(b);web=new WebView(this);setContentView(web);
  WebSettings s=web.getSettings();s.setJavaScriptEnabled(true);s.setDomStorageEnabled(true);s.setDatabaseEnabled(true);s.setAllowFileAccess(false);s.setAllowContentAccess(false);
  WebViewAssetLoader loader=new WebViewAssetLoader.Builder().addPathHandler("/assets/",new AssetsPathHandler(this)).build();
  web.setWebChromeClient(new WebChromeClient());
  web.setWebViewClient(new WebViewClientCompat(){
   @Override public android.webkit.WebResourceResponse shouldInterceptRequest(WebView v,WebResourceRequest req){return loader.shouldInterceptRequest(req.getUrl());}
   @Override public boolean shouldOverrideUrlLoading(WebView v,WebResourceRequest req){Uri u=req.getUrl();if("appassets.androidplatform.net".equals(u.getHost()))return false;startActivity(new Intent(Intent.ACTION_VIEW,u));return true;}
  });
  web.loadUrl(home);
 }
 @Override public void onBackPressed(){if(web.canGoBack())web.goBack();else super.onBackPressed();}
}