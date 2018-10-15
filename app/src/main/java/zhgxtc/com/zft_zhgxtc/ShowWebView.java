package zhgxtc.com.zft_zhgxtc;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class ShowWebView extends BaseAppCompatActivity{

    private TextView tv_CurrentName;
    private WebView serview_webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_web_view);
        goBack(this);

        tv_CurrentName =findViewById(R.id.tv_CurrentName);
        serview_webview =findViewById(R.id.serview_webview);
        initData();
    }

    private void initData(){
        Bundle bundle =getIntent().getExtras();
        if(bundle!=null){
            tv_CurrentName.setText(bundle.getString("title"));
            serview_webview.getSettings().setJavaScriptEnabled(true);
            serview_webview.setWebViewClient(new WebViewClient());
            serview_webview.loadUrl(bundle.getString("url"));
        }
    }
}
