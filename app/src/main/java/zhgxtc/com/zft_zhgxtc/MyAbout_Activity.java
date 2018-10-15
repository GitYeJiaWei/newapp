package zhgxtc.com.zft_zhgxtc;

import android.os.Bundle;

public class MyAbout_Activity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_about_);
        goBack(this);
    }
}
