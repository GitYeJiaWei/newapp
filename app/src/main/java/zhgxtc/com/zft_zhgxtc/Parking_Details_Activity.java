package zhgxtc.com.zft_zhgxtc;

import android.os.Bundle;

/**
 * Created by kimhillzhang on 2018-03-07.
 */

public class Parking_Details_Activity extends BaseAppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.parking_details_activity);
        goBack(this);
    }
}