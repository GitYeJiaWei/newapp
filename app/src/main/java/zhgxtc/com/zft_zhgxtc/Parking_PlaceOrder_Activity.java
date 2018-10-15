package zhgxtc.com.zft_zhgxtc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import common.Utils;

public class Parking_PlaceOrder_Activity extends BaseAppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking__place_order_);
        goBack(this);
        Button get_order= (Button) findViewById(R.id.get_order);
        get_order.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.get_order:
                Utils.StartActivity(Parking_PlaceOrder_Activity.this,Parking_PlaceOrder_Details_Activity.class);
                break;
        }
    }
}
