package zhgxtc.com.zft_zhgxtc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fosung.libeasypr.view.EasyPRPreSurfaceView;
import com.fosung.libeasypr.view.EasyPRPreView;

/**
 * Created by kimhillzhang on 2018-03-28.
 */

public class Scan_Cph_Activity extends BaseAppCompatActivity {
    private EasyPRPreView easyPRPreView;
    private Button btnShutter;
    private TextView text;
    private ImageView bgnOk = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.scan_cph_activity);
        this.goBack(this);
        easyPRPreView = (EasyPRPreView) findViewById(R.id.preSurfaceView);
        btnShutter = (Button) findViewById(R.id.btnShutter);
        bgnOk = (ImageView)this.findViewById(R.id.bgnOk);
        text = (TextView) findViewById(R.id.text);
        initListener();
        bgnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( text.getText().toString().equals("")){
                    Toast.makeText(Scan_Cph_Activity.this, "请先识别车牌号！", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent mIntent = new Intent();
                mIntent.putExtra("CPH", text.getText().toString());
                // 设置结果，并进行传送
                Scan_Cph_Activity.this.setResult(0, mIntent);
                Scan_Cph_Activity.this.finish();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (easyPRPreView != null) {
            easyPRPreView.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (easyPRPreView != null) {
            easyPRPreView.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (easyPRPreView != null) {
            easyPRPreView.onDestroy();
        }
    }

    private void initListener() {
        easyPRPreView.setRecognizedListener(new EasyPRPreSurfaceView.OnRecognizedListener() {
            @Override
            public void onRecognized(String result) {
                if (result == null || result.equals("0")) {
                    Toast.makeText(Scan_Cph_Activity.this, "换个姿势试试!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Scan_Cph_Activity.this, "识别完成", Toast.LENGTH_SHORT).show();
                    text.setText(result);
                }
            }
        });
        btnShutter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                easyPRPreView.recognize();//开始识别
            }
        });
    }
}
