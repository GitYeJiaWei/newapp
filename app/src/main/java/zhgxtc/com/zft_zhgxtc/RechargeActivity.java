package zhgxtc.com.zft_zhgxtc;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import common.CashierInputFilter;
import common.DialogUtils;
import common.Utils;
import common.UtilsHelper;

/**
 * 充值
 */
public class RechargeActivity extends BaseAppCompatActivity implements View.OnClickListener{
    private RadioGroup rl_pay;
    private RadioButton radio_10,radio_20,radio_30,radio_50,radio_100;
    private EditText radio_0;
    private String meony="微信支付";
    private String pay;
    private Button rl_recharge;
    private InputMethodManager imm;
    private LinearLayout listen_layout;
    private TextView pay_money;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        goBack(this);

        rl_pay =findViewById(R.id.rl_pay);
        rl_pay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton1=group.findViewById(checkedId);
                meony =radioButton1.getText().toString();

            }
        });

        imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        pay_money =findViewById(R.id.pay_money);
        radio_0 =findViewById(R.id.radio_0);
        radio_0.setOnClickListener(this);
        radio_10 =findViewById(R.id.radio_10);
        radio_10.setOnClickListener(this);
        radio_20 =findViewById(R.id.radio_20);
        radio_20.setOnClickListener(this);
        radio_30 =findViewById(R.id.radio_30);
        radio_30.setOnClickListener(this);
        radio_50 =findViewById(R.id.radio_50);
        radio_50.setOnClickListener(this);
        radio_100 =findViewById(R.id.radio_100);
        radio_100.setOnClickListener(this);
        rl_recharge =findViewById(R.id.rl_recharge);
        rl_recharge.setOnClickListener(this);
        listen_layout =findViewById(R.id.listen_layout);
        listen_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                listen_layout.setFocusable(true);
                listen_layout.setFocusableInTouchMode(true);
                listen_layout.requestFocus();
                return false;
            }
        });

        initview();
        radio_0.addTextChangedListener(new TextWatcher() {
            /**
             * 编辑内容未改变之前回调
             */
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().isEmpty()==false){
                    radio_0.setSelection(s.toString().trim().length()-1);
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()==false){
                    radio_0.setSelection(s.toString().trim().length()-1);
                    pay_money.setText("¥ "+s.toString().replace("元",""));
                }
            }
        });


    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                View view = getCurrentFocus();
                UtilsHelper.hideKeyboard(ev, view, RechargeActivity.this);//调用方法判断是否需要隐藏键盘
                break;

            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private void initview(){
        pay="10";
        radio_10.setChecked(true);
        radio_0.setFocusable(false);
        radio_0.setFocusableInTouchMode(false);
        radio_20.setChecked(false);
        radio_30.setChecked(false);
        radio_50.setChecked(false);
        radio_100.setChecked(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_recharge:
                if (pay.equals("0")){
                    pay=radio_0.getText().toString();
                }
                Toast.makeText(RechargeActivity.this,meony+":"+pay,Toast.LENGTH_LONG).show();
                break;
            case R.id.radio_0:
                pay="0";
                radio_0.setFocusable(true);
                radio_0.setFocusableInTouchMode(true);
                radio_0.requestFocus();
                imm.showSoftInput(radio_0,0);
                radio_10.setChecked(false);
                radio_20.setChecked(false);
                radio_30.setChecked(false);
                radio_50.setChecked(false);
                radio_100.setChecked(false);

                //edittext 数值改变改变修改
                InputFilter[] is = {new CashierInputFilter()};
                radio_0.setFilters(is);
                pay_money.setText("¥ "+radio_0.getText().toString().replace("元",""));
                break;
            case R.id.radio_10:
                pay="10";
                radio_10.setChecked(true);
                radio_0.setFocusable(false);
                radio_0.setFocusableInTouchMode(false);
                radio_20.setChecked(false);
                radio_30.setChecked(false);
                radio_50.setChecked(false);
                radio_100.setChecked(false);
                pay_money.setText("¥ 10");
                break;
            case R.id.radio_20:
                radio_20.setChecked(true);
                radio_0.setFocusable(false);
                radio_0.setFocusableInTouchMode(false);
                pay="20";
                radio_10.setChecked(false);
                radio_30.setChecked(false);
                radio_50.setChecked(false);
                radio_100.setChecked(false);
                pay_money.setText("¥ 20");
                break;
            case R.id.radio_30:
                radio_30.setChecked(true);
                radio_0.setFocusable(false);
                radio_0.setFocusableInTouchMode(false);
                pay="30";
                radio_20.setChecked(false);
                radio_10.setChecked(false);
                radio_50.setChecked(false);
                radio_100.setChecked(false);
                pay_money.setText("¥ 30");
                break;
            case R.id.radio_50:
                radio_50.setChecked(true);
                radio_0.setFocusable(false);
                radio_0.setFocusableInTouchMode(false);
                pay="50";
                radio_20.setChecked(false);
                radio_30.setChecked(false);
                radio_10.setChecked(false);
                radio_100.setChecked(false);
                pay_money.setText("¥ 50");
                break;
            case R.id.radio_100:
                radio_100.setChecked(true);
                radio_0.setFocusable(false);
                radio_0.setFocusableInTouchMode(false);
                pay="100";
                radio_20.setChecked(false);
                radio_30.setChecked(false);
                radio_10.setChecked(false);
                radio_50.setChecked(false);
                pay_money.setText("¥ 100");
                break;

        }
    }
}
