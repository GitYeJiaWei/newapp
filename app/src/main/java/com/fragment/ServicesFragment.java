package com.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import common.Utils;
import zhgxtc.com.zft_zhgxtc.R;
import zhgxtc.com.zft_zhgxtc.ShowWebView;

/**
 * Created by kimhillzhang on 2018-03-05.
 */

public class ServicesFragment extends Fragment implements View.OnClickListener{
    private View inflate = null;
    private LinearLayout btn_shop,btn_car;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (inflate == null) {
            inflate = inflater.inflate(R.layout.services_fragment, null);
        }
        ViewGroup parent = (ViewGroup) inflate.getParent();
        if (parent != null) {
            parent.removeView(inflate);
        }

        btn_car =inflate.findViewById(R.id.btn_car);
        btn_shop = inflate.findViewById(R.id.btn_shop);
        btn_car.setOnClickListener(this);
        btn_shop.setOnClickListener(this);
        return  inflate;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_car:
                Bundle bundle =new Bundle();
                bundle.putString("title","共享汽车");
                bundle.putString("url","http://www.utripcar.com/download/down.html");
                Utils.StartActivity(getActivity(),ShowWebView.class,bundle);
                break;
            case R.id.btn_shop:
                //url="http://www.sxx001.com/Wap/Home/Index";
                break;

                default:
        }
    }
}
