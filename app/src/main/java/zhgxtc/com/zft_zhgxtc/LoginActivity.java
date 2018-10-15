package zhgxtc.com.zft_zhgxtc;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import OkHttp3Utils.CallbackInterface;
import OkHttp3Utils.HttpService;
import OkHttp3Utils.PostParameter;
import common.DialogUtils;
import common.LoginUserInfo;
import common.Utils;
import model.sys_users;

public class LoginActivity extends BaseAppCompatActivity implements View.OnClickListener{
    private TextView tv_login,tv_regist;
    private EditText et_password,et_account;
    private final String TAG="Login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_login = findViewById(R.id.tv_login);
        tv_login.setOnClickListener(this);
        tv_regist = findViewById(R.id.tv_regist);
        tv_regist.setOnClickListener(this);
        et_account = findViewById(R.id.et_account);
        et_password =findViewById(R.id.et_password);
        et_account.setText(Utils.ReadSharePreference(LoginActivity.this,"sjh","Sjh"));
        et_password.setText(Utils.ReadSharePreference(LoginActivity.this,"pwd","Pwd"));
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_login:
                if (TextUtils.isEmpty(et_account.getText().toString().trim()) ||
                        TextUtils.isEmpty(et_password.getText().toString().trim()))
                {
                    DialogUtils.ShowToast(LoginActivity.this,"请输入用户名或密码");
                    return ;
                }else{
                    String account =et_account.getText().toString().trim();
                    if (account.length()<3){
                        DialogUtils.ShowToast(LoginActivity.this,"用户名不能小于三位");
                    }
                    String password =et_password.getText().toString().trim();
                    PostParameter parameter =new PostParameter();
                    parameter.Add("sjh",account);
                    parameter.Add("pwd", password);
                   // UtilsHelper.InsertSjhToSm(password, account);  对密码用md5加密
                    parameter.Add("committype", "login");

                    HttpService httpService = new HttpService(LoginActivity.this, "正在登录，请稍后...", 0, callbackInterface);
                    httpService.postAsyncHttp("tools/android.ashx",parameter);
                }
                break;
            case R.id.tv_regist:
                Utils.StartActivity(this,RegisterActivity.class);
                break;
                default:
                    break;
        }
    }


    /**
     * 回调
     */
    CallbackInterface callbackInterface=new CallbackInterface() {
        @Override
        public void onMySuccess(String result, int flag) {
            if (flag==0){
                try {
                    JSONObject jsonObject =new JSONObject(result);
                    LoginUserInfo.UserModel =new sys_users();
                    LoginUserInfo.UserModel.error =jsonObject.getString("error");
                    if (!LoginUserInfo.UserModel.error.equals("")){
                        DialogUtils.ShowToast(LoginActivity.this,"账号或密码不正确");
                        return;
                    }
                    LoginUserInfo.UserModel.grxz=jsonObject.getString("grxz");
                    LoginUserInfo.UserModel.cph=jsonObject.getString("cph");
                    LoginUserInfo.UserModel.sjh=jsonObject.getString("sjh");
                    LoginUserInfo.UserModel.sjh2=jsonObject.getString("sjh2");
                    LoginUserInfo.UserModel.dwdz=jsonObject.getString("dwdz");
                    LoginUserInfo.UserModel.grdz=jsonObject.getString("grdz");
                    LoginUserInfo.UserModel.xm=jsonObject.getString("xm");
                    Utils.WriteSharePreference(LoginActivity.this,"sjh","Sjh",LoginUserInfo.UserModel.sjh);
                    Utils.WriteSharePreference(LoginActivity.this,"pwd","Pwd",et_password.getText().toString().trim());
                    Utils.WriteSharePreference(LoginActivity.this,"grxz","Grxz",LoginUserInfo.UserModel.grxz);
                    Utils.WriteSharePreference(LoginActivity.this,"xm","Xm",LoginUserInfo.UserModel.xm);
                    Utils.StartActivity(LoginActivity.this,MainActivity.class,true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

               /* *//*映射 泛型RequestResultModel<T>*//*
                java.lang.reflect.Type type =new TypeToken<RequestResultModel<sys_users>>(){}.getType();
                RequestResultModel<sys_users> Model = JsonUtil.fromJson(result,type);
                if (!Model.ResultCode.equals("0")){
                    DialogUtils.ShowToast(LoginActivity.this,Model.Message);
                }else{5
                    LoginUserInfo.UserModel = Model.DataModel;
                    Utils.WriteSharePreference(LoginActivity.this,"name","Name",et_account.getText().toString().trim());
                    Utils.WriteSharePreference(LoginActivity.this,"pwd","Pwd",et_password.getText().toString().trim());
                    Utils.StartActivity(LoginActivity.this,MainActivity.class,true);
                }*/
            }

        }

        @Override
        public void onMyError(String result) {
            DialogUtils.ShowToast(LoginActivity.this,result);
        }
    };
}
