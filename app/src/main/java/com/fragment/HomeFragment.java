package com.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.MapView;
import com.uuch.adlibrary.AdConstant;
import com.uuch.adlibrary.AdManager;
import com.uuch.adlibrary.bean.AdInfo;
import com.uuch.adlibrary.transformer.DepthPageTransformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import zhgxtc.com.zft_zhgxtc.CaptureActivity;
import adapter.AdViewPagerAdapter;
import common.DialogUtils;
import common.Utils;
import map.utils.MapServices;
import model.adModel;
import zhgxtc.com.zft_zhgxtc.Parking_Activity;
import zhgxtc.com.zft_zhgxtc.Parking_Map_Activity;
import zhgxtc.com.zft_zhgxtc.Parking_Record_Activity;
import zhgxtc.com.zft_zhgxtc.R;
import zhgxtc.com.zft_zhgxtc.Select_City_Activity;
import zhgxtc.com.zft_zhgxtc.Temp_Parking_Pay_Activity;

import static android.app.Activity.RESULT_OK;

/**
 * Created by kimhillzhang on 2018-03-05.
 */

public class HomeFragment extends Fragment {
    private View inflate = null;
    private ViewPager mViewPaper;
    private int oldPosition = 0;
    private LinearLayout llDotTitle = null;//广告图标题点
    private AdViewPagerAdapter adViewPagerAdapter = null;
    private LinearLayout llTempPay = null;
    private LinearLayout llParking = null;
    private LinearLayout llRecord = null;
    private RelativeLayout rlMap = null;
    private TextView tv_CurrentName,tv_ChooseDate =null;

    private MapServices mapServices = null;
    private MapView mapView;
    private int mYear,mMonth,mDay;
    private Dialog dataPickerDialog;
    private ImageView btnDevice;
    private static final int REQUEST_CODE_SCAN = 0x0000;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private int ad=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (inflate == null) {
            inflate = inflater.inflate(R.layout.home_fragment, null);
        }
        ViewGroup parent = (ViewGroup) inflate.getParent();
        if (parent != null) {
            parent.removeView(inflate);
        }
        btnDevice =inflate.findViewById(R.id.btnDevice);
        mViewPaper = inflate.findViewById(R.id.vp);
        tv_ChooseDate = inflate.findViewById(R.id.tv_ChooseDate);
        tv_CurrentName = inflate.findViewById(R.id.tv_CurrentName);
        llDotTitle =inflate.findViewById(R.id.llDotTitle);
        llTempPay = inflate.findViewById(R.id.llTempPay);
        llParking = inflate.findViewById(R.id.llParking);
        llRecord = inflate.findViewById(R.id.llRecord);
        rlMap =  inflate.findViewById(R.id.rlMap);
        btnDevice.setOnClickListener(new llOnClick());
        tv_ChooseDate.setOnClickListener(new llOnClick());
        llTempPay.setOnClickListener(new llOnClick());
        llParking.setOnClickListener(new llOnClick());
        llRecord.setOnClickListener(new llOnClick());
        rlMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.StartActivity(getActivity(), Parking_Map_Activity.class);
            }
        });
        tv_CurrentName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.StartActivity(getActivity(), Select_City_Activity.class);
            }
        });

        List<adModel> list = new ArrayList<>();
        adModel M1 = new adModel();
        //M1.Title="广告图片a";
        M1.TempImageId = R.mipmap.a1;
        list.add(M1);
        adModel M2 = new adModel();
        //M2.Title="广告图片b";
        M2.TempImageId = R.mipmap.a2;
        list.add(M2);
        adModel M3 = new adModel();
        //M3.Title="广告图片c";
        M3.TempImageId = R.mipmap.a3;
        list.add(M3);
        adModel M4 = new adModel();
       // M4.Title="广告图片d";
        M4.TempImageId = R.mipmap.a4;
        list.add(M4);
        loadAd(list);

        //初始化日期
        final Calendar calendar=Calendar.getInstance();
        mYear =calendar.get(Calendar.YEAR);
        mMonth =calendar.get(Calendar.MONTH);
        mDay =calendar.get(Calendar.DAY_OF_MONTH);
        SetDateTimeToTextView(tv_ChooseDate);

        //初始化mapview
        mapView =(MapView) inflate.findViewById(R.id.dmapView);
        mapServices = new MapServices(getActivity(), mapView);
        mapServices.SetMapView();
        mapServices.SetOnLocationCallback(new MapServices.LocationCallbackInterface() {
            @Override
            public void getLoaction(BDLocation location) {
                //Toast.makeText(getActivity(), location.getLatitude() +"==="+location.getLongitude(), Toast.LENGTH_LONG).show();
            }
        });
        return  inflate;
    }


    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        mapServices.Start();

        if (ad==0){
            List<AdInfo> advList = new ArrayList<>();
            AdInfo adInfo = new AdInfo();
            adInfo.setActivityImg("https://raw.githubusercontent.com/yipianfengye/android-adDialog/master/images/testImage1.png");
            advList.add(adInfo);
            AdManager adManager = new AdManager(getActivity(), advList);
            adManager.setOverScreen(true)
                    .setPageTransformer(new DepthPageTransformer());
            adManager.showAdDialog(AdConstant.ANIM_DOWN_TO_UP);
            ad=1;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapServices.Stop();
    }

    private class llOnClick implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.llTempPay)
            {
                Utils.StartActivity(getActivity(), Temp_Parking_Pay_Activity.class);
            }
            if(v.getId() == R.id.llParking)
            {
                Utils.StartActivity(getActivity(), Parking_Activity.class);
            }
            if(v.getId() == R.id.llRecord)
            {
                Utils.StartActivity(getActivity(), Parking_Record_Activity.class);
            }
            if (v.getId() == R.id.tv_ChooseDate){
                dataPickerDialog = new DatePickerDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, mdateListener, mYear, mMonth, mDay);
                dataPickerDialog.show();
            }
            if (v.getId() == R.id.btnDevice){
                Intent Zxing =new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(Zxing,REQUEST_CODE_SCAN);
            }
        }
    };

    private DatePickerDialog.OnDateSetListener mdateListener =new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            SetDateTimeToTextView(tv_ChooseDate);
        }
    };

    void SetDateTimeToTextView(TextView txtView)
    {
        String newDateStr = mYear+"-" + (mMonth + 1)+"-"+mDay;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
            java.util.Date date = sdf.parse(newDateStr);
            txtView.setText(new SimpleDateFormat("yyyy-MM-dd").format(date));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                if(content.contains("SITE:"));
                {
                    String string1=content.replace("SITE:","");
                    DialogUtils.ShowToast(getActivity(),"扫描结果："+string1);
                }
            }
        }

    }

    /**
     * 广告图
     * */
    void loadAd(List<adModel> list)
    {
        oldPosition = 0;
        final String[]  titles = new String[5];
        final List<View> dots = new ArrayList<View>();
        llDotTitle.removeAllViews();
        final List<ImageView> images = new ArrayList<ImageView>();
        try {
            for (int h = 0; h < list.size(); h++) {
                adModel model = list.get(h);
                //创建点
                View view = new View(getActivity());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10,10);
                params.setMargins(4, 0, 4, 0);//左，上，右，下
                view.setLayoutParams(params);
                view.setBackground(getActivity().getResources().getDrawable(R.drawable.dot_normal));
                dots.add(view);
                llDotTitle.addView(view);
                //创建标题
                titles[h] = model.Title;
                ImageView imageView = new ImageView(getActivity());//动态创建图
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                imageView.setImageDrawable(getActivity().getResources().getDrawable(model.TempImageId));
                images.add(imageView);
            }
        }
        catch (Exception ex)
        {}


        final TextView title = (TextView)inflate.findViewById(R.id.title);
        title.setText(titles[0]);
        dots.get(oldPosition).setBackgroundResource(R.drawable.dot_focused);
        if(adViewPagerAdapter != null) {
            adViewPagerAdapter.images.clear();
            adViewPagerAdapter = null;
        }
        adViewPagerAdapter = new AdViewPagerAdapter();
        adViewPagerAdapter.images = images;
        mViewPaper.setAdapter(adViewPagerAdapter);
        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageSelected(int position) {
                title.setText(titles[position]);
                dots.get(position).setBackgroundResource(R.drawable.dot_focused);
                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
                oldPosition = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        if(images.size()>0)
        {
            mViewPaper.setCurrentItem(0);
        }
    }
}
