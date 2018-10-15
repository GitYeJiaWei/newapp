package OkHttp3Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by kimhillzhang on 2017-12-08.
 */

public class ImageLoaderUtils {
    private ImageView imageView = null;
    public static final String CACHE_PATH = Environment.getExternalStorageDirectory() + "/QLMWEIGHT/";
    public ImageLoaderUtils(ImageView _ImageView)
    {
        this.imageView = _ImageView;
    }
    private Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            //把byte字节组装成图片
            Bitmap bmp = BitmapFactory.decodeByteArray(bundle.getByteArray("bmp"), 0, bundle.getByteArray("bmp").length);
            imageView.setImageBitmap(bmp);
            setBitmapToLocal(bundle.getString("imgurl"), bmp);//保存到本地
        }
    };
    /**
     * 把图片保存到本地
     *
     */
    public void setBitmapToLocal(String imageUrl, Bitmap bitmap) {
        File file;
        String fileName = md5(imageUrl);
        // 当sdcard卡已经挂载的时候
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))  {
            file = new File(CACHE_PATH, fileName);
        } else {
            return;
        }
        try {
            File dirFile = new File(CACHE_PATH);  //目录转化成文件夹
            if (!dirFile.exists()) {                //如果不存在，那就建立这个文件夹
                dirFile.mkdirs();
            }                            //文件夹有啦，就可以保存图片啦
            file = new File(CACHE_PATH, fileName + ".jpg");// 在SDcard的目录下创建图片文,以当前时间为其命名

            // 把Bitmap对象以jpg格式保存
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                    new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 从本地读取数据
     */
    public Bitmap getBitmapFromLocal(String imageUrl) {
        File file;
        String fileName = md5(imageUrl);
        // 当sdcard卡已经挂载的时候
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            file = new File(CACHE_PATH, fileName+".jpg");
        } else {
           return  null;
        }
        if (file.exists()) {
            try {
                return BitmapFactory.decodeStream(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * 将文件名用md5 加密
     * @param str
     * @return
     */
    public String md5(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            byte[] digest = instance.digest(str.getBytes());
            StringBuffer sb = new StringBuffer();

            for (byte b : digest) {
                int i = b & 0xFF;
                String hex = Integer.toHexString(i);
                if (hex.length() < 2) {
                    hex = "0" + hex;
                }
                sb.append(hex);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void LoadImage(final String _ImageUrl)
    {
        if(_ImageUrl == null || _ImageUrl.equals(""))
        {
            return;
        }
        Bitmap bitmap = getBitmapFromLocal(_ImageUrl);
        if(bitmap != null){
            imageView.setImageBitmap(bitmap);
            return;
        }
        final OkHttpClient client = new OkHttpClient();
        //创建OkHttpClient针对某个url的数据请求
        Request request = new Request.Builder().url(HttpConfig.HttpHeader1 + _ImageUrl).build();
        Call call = client.newCall(request);
        //请求加入队列
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //此处处理请求失败的业务逻辑
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //我写的这个例子是请求一个图片
                //response的body是图片的byte字节
                byte[] bytes = response.body().bytes();
                Message message = mHandler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putByteArray("bmp",bytes);
                bundle.putString("imgurl", _ImageUrl);
                message.setData(bundle);
                mHandler.sendMessage(message);
            }
        });
    }
}
