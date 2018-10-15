package zhgxtc.com.zft_zhgxtc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import LicenseKeyboard.LicenseKeyboardUtil;

/**
 * Created by kimhillzhang on 2018-03-07.
 */

public class Temp_Parking_Pay_Add_Cph_Activity  extends BaseAppCompatActivity {
    private EditText inputbox1, inputbox2,
            inputbox3, inputbox4,
            inputbox5, inputbox6, inputbox7;
    private LicenseKeyboardUtil keyboardUtil;
    private TextView tv_CurrentName = null;
    private String licenseNumber = "";
    private ImageView btnScan = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.temp_parking_pay_add_cph_activity);
        goBack(this);
        tv_CurrentName = (TextView) this.findViewById(R.id.tv_CurrentName);

        btnScan = (ImageView) this.findViewById(R.id.btnScan);
        inputbox1 = (EditText) this.findViewById(R.id.et_car_license_inputbox1);
        inputbox2 = (EditText) this.findViewById(R.id.et_car_license_inputbox2);
        inputbox3 = (EditText) this.findViewById(R.id.et_car_license_inputbox3);
        inputbox4 = (EditText) this.findViewById(R.id.et_car_license_inputbox4);
        inputbox5 = (EditText) this.findViewById(R.id.et_car_license_inputbox5);
        inputbox6 = (EditText) this.findViewById(R.id.et_car_license_inputbox6);
        inputbox7 = (EditText) this.findViewById(R.id.et_car_license_inputbox7);


        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null && bundle.containsKey("CPH")) {
            tv_CurrentName.setText("变更车牌");
            licenseNumber = bundle.getString("CPH");
            setCph();
        }
        keyboardUtil = new LicenseKeyboardUtil(this, new EditText[]{inputbox1, inputbox2, inputbox3,
                inputbox4, inputbox5, inputbox6, inputbox7}, R.id.keyboard_view);
        keyboardUtil.showKeyboard();


        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent();
                mIntent.setClass(Temp_Parking_Pay_Add_Cph_Activity.this, Scan_Cph_Activity.class);
                startActivityForResult(mIntent, 0);
            }
        });
    }
    private void setCph()
    {
        for (int i = 0; i < licenseNumber.length(); i++) {
            String Value = String.valueOf(licenseNumber.charAt(i));
            if (i == 0) {
                inputbox1.setText(Value);
            } else if (i == 1) {
                inputbox2.setText(Value);
            } else if (i == 2) {
                inputbox3.setText(Value);
            } else if (i == 3) {
                inputbox4.setText(Value);
            } else if (i == 4) {
                inputbox5.setText(Value);
            } else if (i == 5) {
                inputbox6.setText(Value);
            } else if (i == 6) {
                inputbox7.setText(Value);
            }
        }
    }
    // 回调方法，从第二个页面回来的时候会执行这个方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 0 && data != null) {
            licenseNumber = data.getStringExtra("CPH");
            if(licenseNumber == null && licenseNumber.equals(""))
                return;
            setCph();
        }
    }
}