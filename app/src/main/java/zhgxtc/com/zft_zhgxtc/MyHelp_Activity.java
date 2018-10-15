package zhgxtc.com.zft_zhgxtc;

import android.os.Bundle;
import android.webkit.WebView;

public class MyHelp_Activity extends BaseAppCompatActivity {
    private WebView myhelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_help);
        goBack(this);

       /* myhelp =findViewById(R.id.myhelp);
        myhelp.loadUrl("file:///android_asset/video.html");*/
    }
}
