package common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * 工具类
 */
public class Utils {
    @SuppressLint("SimpleDateFormat")
    public static Date ToDateTime(String dateString)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try
        {
            Date date = sdf.parse(dateString);
            return date;
        }
        catch(Exception ex)
        {
            return null;
        }
    }
    public static String GetDataTimeString(String dataFormat)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(dataFormat);//只需改变输出格式
        String dateStr = sdf.format(new Date());
        return dateStr;
    }
    public static String getCurrentTime(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        String currentTime = sdf.format(date);
        return currentTime;
    }
    //金额验证
    public static boolean isNumber(String str)
    {
        java.util.regex.Pattern pattern=java.util.regex.Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
        java.util.regex.Matcher match=pattern.matcher(str);
        if(match.matches()==false)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public static String getCurrentTime() {
        return getCurrentTime("yyyy-MM-dd  HH:mm:ss");
    }


    @SuppressLint("SimpleDateFormat")
    public static String GetCurrentDateString(String formatter)
    {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(formatter);
        String date = sDateFormat.format(new Date());
        return date;
    }
    public static String RemoveStringEndWidth(String str, String c)
    {
        if(str.endsWith(c))
        {
            str = str.substring(0, (str.length() - c.length()));
        }
        return str;
    }
    public static void RemoveSharePreference(Context context, String key)
    {
        SharedPreferences preferences = context.getSharedPreferences(key, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit().remove(key);
        editor.commit();
    }
    public static String ReadSharePreference(Context context, String key, String keyString)
    {
        SharedPreferences preferences = context.getSharedPreferences(key, context.MODE_PRIVATE);
        return preferences.getString(keyString, null);
    }
    @SuppressLint("CommitPrefEdits")
    public static void WriteSharePreference(Context context, String key, String keyString, String value)
    {
        SharedPreferences preferences = context.getSharedPreferences(key, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(keyString, value);
        editor.commit();
    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public static void BackgroundAlpha(Context context, float bgAlpha)
    {
        WindowManager.LayoutParams lp = ((Activity)context).getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        ((Activity)context).getWindow().setAttributes(lp);
    }
    //打开其它activity
    public static void StartActivity(Context context, Class<?> newClass, Boolean coloseSelf)
    {
        Intent intent = new Intent();
        intent.setClass(context, newClass);
        context.startActivity(intent);
        if(coloseSelf)
        {
            //从列表中移除
            CloseActivity.RemoveActivity(context);
            ((Activity)context).finish();
        }
    }
    public static void StartActivity(final Context context, final Class<?> newClass, final Bundle bundle, final Boolean coloseSelf)
    {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(context, newClass);
        context.startActivity(intent);
        if(coloseSelf)
        {
            //从列表中移除
            CloseActivity.RemoveActivity(context);
            ((Activity)context).finish();
        }
    }
    public static Bitmap comp(Bitmap image, float imgWidth, float imgHeight) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if( baos.toByteArray().length / 1024 > 1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = imgHeight;//这里设置高度为800f
        float ww = imgWidth;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    }
    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中

        while ( baos.toByteArray().length / 1024 > 1000) {	//循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩options%，把压缩后的数据存放到baos中

        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }
    public static byte[] BitmapToByteArray(Bitmap image)
    {
        if(image == null)
        {
            return new byte[]{};
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }
    public static byte[] ImageViewToByteArray(ImageView imageView)
    {
        Bitmap image = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        return BitmapToByteArray(image);
    }
    public static void StartActivity(Context context, Class<?> newClass, Bundle bundle)
    {
        StartActivity(context, newClass,bundle,false);
    }
    public static void StartActivity(Context context, Class<?> newClass)
    {
        StartActivity(context,newClass,false);
    }


}
