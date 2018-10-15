package zhgxtc.com.zft_zhgxtc;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by kimhillzhang on 2018-03-07.
 */

public class BaseAppCompatActivity extends AppCompatActivity {
    public void goBack(final AppCompatActivity appCompatActivity)
    {
        ImageView btnBack = (ImageView)this.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new ImageView.OnClickListener(){
            @Override
            public void onClick(View v) {
                appCompatActivity.finish();
            }
        });
    }
}
