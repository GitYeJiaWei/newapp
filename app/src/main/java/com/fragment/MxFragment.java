package com.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapter.MxPagerAdapter;
import adapter.Mx_systemAdapter;
import adapter.Mx_unionAdapter;
import common.Utils;
import model.MxModel;
import zhgxtc.com.zft_zhgxtc.Mx_Statics_Activity;
import zhgxtc.com.zft_zhgxtc.R;

/**
 * Created by kimhillzhang on 2018-03-05.
 */

public class MxFragment extends Fragment {
    private View inflate = null;
    private LinearLayout ll001 =null;
    private LinearLayout ll002 =null;
    private TextView txt001 = null;
    private TextView txt002 = null;
    private TextView tv_CurrentName = null;
    private ViewPager viewPage = null;
    private List<MxModel> mxsystemModelList =new ArrayList<>();
    private List<MxModel> mxunionModelList =new ArrayList<>();
    private ListView mx_system_listview,mx_union_listview;
    private Mx_systemAdapter mx_systemAdapter =null;
    private Mx_unionAdapter mx_unionAdapter =null;
    private ImageView btnMx = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (inflate == null) {
            inflate = inflater.inflate(R.layout.mx_fragment, null);
        }
        ViewGroup parent = (ViewGroup) inflate.getParent();
        if (parent != null) {
            parent.removeView(inflate);
        }
        ll001 = (LinearLayout)inflate.findViewById(R.id.ll001);
        ll002 = (LinearLayout)inflate.findViewById(R.id.ll002);
        txt001 = (TextView) inflate.findViewById(R.id.txt001);
        txt002 = (TextView) inflate.findViewById(R.id.txt002);
        btnMx = (ImageView)inflate.findViewById(R.id.btnMx);
        tv_CurrentName = (TextView) inflate.findViewById(R.id.tv_CurrentName);
        btnMx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.StartActivity(getActivity(), Mx_Statics_Activity.class);
            }
        });

        ll001.setOnClickListener(new OnLLClick());
        ll002.setOnClickListener(new OnLLClick());

        initTab();
        init_mx_system();
        return  inflate;
    }
    void setStatus(int i)
    {
        if(i == 0)
        {
            ll001.setBackground(getActivity().getResources().getDrawable(R.drawable.left_conner));
            txt001.setTextColor(getActivity().getResources().getColor(R.color.title_bar_color));
            ll002.setBackground(null);
            txt002.setTextColor(getActivity().getResources().getColor(R.color.white));
            tv_CurrentName.setText("余额：0.00");
            init_mx_system();
        }
        else if(i == 1)
        {
            ll002.setBackground(getActivity().getResources().getDrawable(R.drawable.right_conner));
            txt002.setTextColor(getActivity().getResources().getColor(R.color.title_bar_color));
            ll001.setBackground(null);
            txt001.setTextColor(getActivity().getResources().getColor(R.color.white));
            tv_CurrentName.setText("总金额：0.00");
            init_mx_union();
        }
    }
    void initTab()
    {
        MxPagerAdapter mPagerAdapter = new MxPagerAdapter(getActivity());
        mx_systemAdapter =new Mx_systemAdapter(getActivity());
        mx_unionAdapter =new Mx_unionAdapter(getActivity());

        mx_system_listview = (ListView) mPagerAdapter.getView1().findViewById(R.id.mx_system_listview);
        mx_union_listview = (ListView) mPagerAdapter.getView2().findViewById(R.id.mx_union_listview);

        mx_system_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               /* ParkingModel parkingModel =parkingList.get(position);
                Utils.StartActivity(Parking_Activity.this, Parking_CreateOrder_Activity.class);*/
            }
        });
        mx_union_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               /* ParkingModel parkingModel =parkingList.get(position);
                Utils.StartActivity(Parking_Activity.this, Parking_CreateOrder_Activity.class);*/
            }
        });

        mx_system_listview.setAdapter(mx_systemAdapter);
        mx_union_listview.setAdapter(mx_unionAdapter);
        viewPage = (ViewPager) inflate.findViewById(R.id.viewPage);
        viewPage.setAdapter(mPagerAdapter);
        viewPage.setCurrentItem(0, true);
        viewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setStatus(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                /*此方法是在状态改变的时候调用，其中arg0这个参数有三种状态（0，1，2）。
                arg0 ==1的时辰默示正在滑动，
                arg0==2的时辰默示滑动完毕了，
                arg0==0的时辰默示什么都没做。*/

            }
        });


    }

    private void init_mx_system(){
        mxsystemModelList.clear();
        for (int i=0;i<10;i++){
            MxModel a =new MxModel();
            a.setSjh("18859865658"+i);
            a.setJe(10+i);
            a.setTime("20180307 234223"+i);
            mxsystemModelList.add(a);
        }
        mx_systemAdapter.modelList = mxsystemModelList;
        mx_systemAdapter.notifyDataSetChanged();
    }

    private void init_mx_union(){
        mxunionModelList.clear();
        for (int i=0;i<10;i++){
            MxModel b =new MxModel();
            b.setSjh("闽A12345"+i);
            b.setJe(10+i);
            b.setTime("20180307 234223"+i);
            mxunionModelList.add(b);
        }
        mx_unionAdapter.modelList1 = mxunionModelList;
        mx_unionAdapter.notifyDataSetChanged();

    }

    private class OnLLClick implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.ll001){
                viewPage.setCurrentItem(0, true);
            }
            else if(v.getId() == R.id.ll002)
            {
                viewPage.setCurrentItem(1, true);
            }
        }
    };
}
