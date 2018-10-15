package OkHttp3Utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;

import common.ProgressDialogUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by kimhillzhang on 2017-12-06.
 */

public class HttpService {
    private CallbackInterface callbackInterface  = null;
    private int flag = 0;
    public HttpService(Context mContext, String loadingTxt, int _flag, CallbackInterface _CallbackInterface) {
        if (loadingTxt == null || loadingTxt.equals(""))
        {
            loadingTxt = "正在加载，请稍后...";
        }
        ProgressDialogUtils.createLoadingDialog(mContext, loadingTxt);
        this.flag = _flag;
        this.callbackInterface = _CallbackInterface;
    }
    private Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0)
            {
                callbackInterface.onMyError(msg.obj.toString());
            }
            if(msg.what == 1)
            {
                callbackInterface.onMySuccess(msg.obj.toString(), flag);
            }
            ProgressDialogUtils.close();
        }
    };
    /***
     * GET请求
     *
     */
    public void getAsyncHttp(String url)
    {
        if(url.startsWith("/"))
        {
            url = url.substring(1);
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url(HttpConfig.HttpHeader1.concat(url));
        Request request = requestBuilder.build();
        Call mcall= okHttpClient.newCall(request);
        final Message message = mHandler.obtainMessage();
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                message.what = 0;
                message.obj = "请求数据失败！";
                mHandler.sendMessage(message);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                message.what = 1;
                message.obj = response.body().string();
                mHandler.sendMessage(message);
            }
        });
    }
    /**
     * POST
     * */
    public void postAsyncHttp(String url, PostParameter parameter) {
        if (url.startsWith("/")) {
            url = url.substring(1);
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        for (PostParameter p : parameter.postParameters)
        {
            builder = builder.add(p.Key, p.Value);
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .url(HttpConfig.HttpHeader1.concat(url))
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        final Message message = mHandler.obtainMessage();
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                message.what = 0;
                message.obj = "请求数据失败！";
                mHandler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                message.what = 1;
                String responseTxt = response.body().string();
                message.obj = responseTxt;
                mHandler.sendMessage(message);
            }
        });
    }
}
