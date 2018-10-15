package zhgxtc.com.zft_zhgxtc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import common.Utils;

/**
 * Created by kimhillzhang on 2018-03-07.
 */

public class Parking_CreateOrder_Activity extends BaseAppCompatActivity {
    private RelativeLayout rl_Parking = null;
    private Button btnClear =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.parking_createorder_activity);
        goBack(this);
        rl_Parking = (RelativeLayout)this.findViewById(R.id.rl_Parking);
        rl_Parking.setOnClickListener(new rlOnClick());
        btnClear =(Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new rlOnClick());
    }
    private class rlOnClick implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.rl_Parking)
            {
                Utils.StartActivity(Parking_CreateOrder_Activity.this, Parking_Details_Activity.class);
            }
            if (v.getId() == R.id.btnClear){
                Utils.StartActivity(Parking_CreateOrder_Activity.this,Parking_PlaceOrder_Activity.class);
            }
        }
    }
}