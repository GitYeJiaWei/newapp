package zhgxtc.com.zft_zhgxtc;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import common.Utils;

/**
 * 余额
 */
public class Rl_personActivity extends BaseAppCompatActivity implements View.OnClickListener{
    private RelativeLayout rl_person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rl_person);
        goBack(this);

        rl_person =findViewById(R.id.rl_person);
        rl_person.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_person:
                Utils.StartActivity(Rl_personActivity.this,RechargeActivity.class);
                break;
                default:
        }
    }
}
