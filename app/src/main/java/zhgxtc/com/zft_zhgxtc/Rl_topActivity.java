package zhgxtc.com.zft_zhgxtc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import OkHttp3Utils.CallbackInterface;
import OkHttp3Utils.HttpService;
import OkHttp3Utils.PostParameter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import common.DialogUtils;
import common.LoginUserInfo;

public class Rl_topActivity extends BaseAppCompatActivity {

    @BindView(R.id.tv_CurrentName)
    TextView tvCurrentName;
    @BindView(R.id.rlTop)
    RelativeLayout rlTop;
    @BindView(R.id.rl_user)
    TextView rlUser;
    @BindView(R.id.rl_myPark)
    RelativeLayout rlMyPark;
    @BindView(R.id.rl_xing)
    TextView rlXing;
    @BindView(R.id.rl_ming)
    TextView rlMing;
    @BindView(R.id.rl_phone)
    TextView rlPhone;
    @BindView(R.id.rl_chepaihao)
    TextView rlChepaihao;
    @BindView(R.id.rl_email)
    TextView rlEmail;
    @BindView(R.id.rl_danwei)
    TextView rlDanwei;
    @BindView(R.id.rl_address)
    TextView rlAddress;
    @BindView(R.id.rl_peraddress)
    TextView rlPeraddress;
    @BindView(R.id.llsecond)
    LinearLayout llsecond;
    @BindView(R.id.btnClear)
    Button btnClear;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.rl_user1)
    TextView rlUser1;
    @BindView(R.id.rl_xing1)
    TextView rlXing1;
    @BindView(R.id.rl_ming1)
    TextView rlMing1;
    @BindView(R.id.rl_phone1)
    TextView rlPhone1;
    @BindView(R.id.rl_chepaihao1)
    TextView rlChepaihao1;
    @BindView(R.id.rl_email1)
    TextView rlEmail1;
    @BindView(R.id.rl_danwei1)
    TextView rlDanwei1;
    @BindView(R.id.rl_address1)
    TextView rlAddress1;
    @BindView(R.id.rl_peraddress1)
    TextView rlPeraddress1;

    private double mapx,mapy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rl_top);
        ButterKnife.bind(this);
        goBack(this);

        initData();
    }


    private void initData() {
        HttpService httpService = new HttpService(this, "加载数据中...", 1, callbackInterface);
        httpService.getAsyncHttp("tools/Register.ashx?action=get&sjh=" + LoginUserInfo.UserModel.sjh);
    }

    CallbackInterface callbackInterface = new CallbackInterface() {
        @Override
        public void onMySuccess(String result, int flag) {
            if (flag == 1) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    rlUser.setText(jsonObject.getString("sjh"));
                    rlXing.setText(jsonObject.getString("x"));
                    rlMing.setText(jsonObject.getString("m"));
                    rlPhone.setText(jsonObject.getString("tell"));
                    rlChepaihao.setText(jsonObject.getString("carno"));
                    rlEmail.setText(jsonObject.getString("email"));
                    rlDanwei.setText(jsonObject.getString("dwmc"));
                    rlPeraddress.setText(jsonObject.getString("dwdz"));
                    LoginUserInfo.UserModel.grdz =jsonObject.getString("grxz");
                    mapx=jsonObject.getDouble("mapx");
                    mapy=jsonObject.getDouble("mapy");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (flag ==2){
                try {
                    JSONObject jsonObject =new JSONObject(result);
                    if (jsonObject.getBoolean("status")) {
                        DialogUtils.ShowToast(Rl_topActivity.this, jsonObject.getString("msg"));
                    } else {
                        DialogUtils.ShowToast(Rl_topActivity.this, jsonObject.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onMyError(String result) {
            DialogUtils.ShowToast(Rl_topActivity.this, result);
        }
    };

    @OnClick(R.id.btnClear)
    public void onViewClicked() {
        PostParameter postParameter =new PostParameter();
        postParameter.Add("sjh",rlUser.getText().toString());
        postParameter.Add("x",rlXing.getText().toString());
        postParameter.Add("m",rlMing.getText().toString());
        postParameter.Add("carno",rlChepaihao.getText().toString());
        postParameter.Add("tell",rlPhone.getText().toString());
        postParameter.Add("dwmc",rlDanwei.getText().toString());
        postParameter.Add("dwdz",rlPeraddress.getText().toString());
        postParameter.Add("grdz",LoginUserInfo.UserModel.grdz);
        postParameter.Add("email",rlEmail.getText().toString());

        postParameter.Add("lat",mapx+"");
        postParameter.Add("lng",mapy+"");
        postParameter.Add("action","save");

        HttpService httpService =new HttpService(Rl_topActivity.this,"正在保存数据...",2,callbackInterface);
        httpService.postAsyncHttp("tools/Register.ashx",postParameter);

    }
}
