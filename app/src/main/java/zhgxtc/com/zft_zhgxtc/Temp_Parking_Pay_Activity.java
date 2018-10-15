package zhgxtc.com.zft_zhgxtc;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import common.Utils;

/**
 * Created by kimhillzhang on 2018-03-05.
 */

public class Temp_Parking_Pay_Activity extends BaseAppCompatActivity {
    private LinearLayout llAddCph = null;
    private TextView txtChangeCph = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.temp_parking_pay_activity);
        goBack(this);
        llAddCph = (LinearLayout)this.findViewById(R.id.llAddCph);
        txtChangeCph = (TextView)this.findViewById(R.id.txtChangeCph);
        llAddCph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.StartActivity(Temp_Parking_Pay_Activity.this, Temp_Parking_Pay_Add_Cph_Activity.class);
            }
        });
        txtChangeCph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("CPH","闽DC8U81");
                Utils.StartActivity(Temp_Parking_Pay_Activity.this, Temp_Parking_Pay_Add_Cph_Activity.class,bundle);
            }
        });
    }
}
