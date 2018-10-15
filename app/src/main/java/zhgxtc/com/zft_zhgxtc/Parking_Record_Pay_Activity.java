package zhgxtc.com.zft_zhgxtc;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import common.Utils;

public class Parking_Record_Pay_Activity extends BaseAppCompatActivity {
    private RelativeLayout rl_Parking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking__record__pay_);
        goBack(this);

        rl_Parking = (RelativeLayout)this.findViewById(R.id.rl_Parking);
        rl_Parking.setOnClickListener(new Parking_Record_Pay_Activity.rlOnClick());

    }

    private class rlOnClick implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.rl_Parking)
            {
                Utils.StartActivity(Parking_Record_Pay_Activity.this, Parking_Details_Activity.class);
            }

        }
    }
}
