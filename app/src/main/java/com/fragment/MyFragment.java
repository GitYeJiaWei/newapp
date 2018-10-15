package com.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.FileNotFoundException;

import common.LoginUserInfo;
import common.Utils;
import zhgxtc.com.zft_zhgxtc.LoginActivity;
import zhgxtc.com.zft_zhgxtc.MainActivity;
import zhgxtc.com.zft_zhgxtc.MyAbout_Activity;
import zhgxtc.com.zft_zhgxtc.MyHelp_Activity;
import zhgxtc.com.zft_zhgxtc.MyParking_Record_Activity;
import zhgxtc.com.zft_zhgxtc.Parking_Record_Activity;
import zhgxtc.com.zft_zhgxtc.R;
import zhgxtc.com.zft_zhgxtc.Rl_personActivity;
import zhgxtc.com.zft_zhgxtc.Rl_topActivity;
import zhgxtc.com.zft_zhgxtc.SelectPicture;


/**
 * Created by kimhillzhang on 2018-03-05.
 */

public class MyFragment extends Fragment implements View.OnClickListener{
    private View inflate = null;
    private RelativeLayout rl_person,rl_mx,rl_myParkRecord,rl_myPark,rl_help,rl_about,rl_quit;
    private custom.view.CircleImageView civ_head =null;
    public static final int RESULT_CAMERA_IMAGE = 0x09;
    private Bitmap newHeaderImage = null;
    private ImageView rlTop;
    private TextView txtName,txtTell;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (inflate == null) {
            inflate = inflater.inflate(R.layout.my_fragment, null);
        }
        ViewGroup parent = (ViewGroup) inflate.getParent();
        if (parent != null) {
            parent.removeView(inflate);
        }
        civ_head = inflate.findViewById(R.id.civ_head);
        civ_head.setOnClickListener(this);
        rl_person = inflate.findViewById(R.id.rl_person);
        rl_person.setOnClickListener(this);
        rlTop =  inflate.findViewById(R.id.rlTop);
        rlTop.setOnClickListener(this);
        rl_mx = inflate.findViewById(R.id.rl_mx);
        rl_mx.setOnClickListener(this);
        rl_myParkRecord =  inflate.findViewById(R.id.rl_myParkRecord);
        rl_myParkRecord.setOnClickListener(this);
        rl_myPark = inflate.findViewById(R.id.rl_myPark);
        rl_myPark.setOnClickListener(this);
        rl_help =inflate.findViewById(R.id.rl_help);
        rl_help.setOnClickListener(this);
        rl_about = inflate.findViewById(R.id.rl_about);
        rl_about.setOnClickListener(this);
        rl_quit =inflate.findViewById(R.id.rl_quit);
        rl_quit.setOnClickListener(this);
        txtName = inflate.findViewById(R.id.txtName);
        txtTell = inflate.findViewById(R.id.txtTell);
        txtName.setText(LoginUserInfo.UserModel.xm);
        txtTell.setText( LoginUserInfo.UserModel.sjh);
        return  inflate;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_CAMERA_IMAGE){
            if (data != null) {
                //取得返回的Uri,基本上选择照片的时候返回的是以Uri形式，但是在拍照中有得机子呢Uri是空的，所以要特别注意
                Uri mImageCaptureUri = data.getData();//选择相册
                //返回的Uri不为空时，那么图片信息数据都会在Uri中获得。如果为空，那么我们就进行下面的方式获取
                if (mImageCaptureUri != null)
                {
                    try {
                        //这个方法是根据Uri获取Bitmap图片的静态方法
                        newHeaderImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), mImageCaptureUri);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        //这里是有些拍照后的图片是直接存放到Bundle中的所以我们可以从这里面获取Bitmap图片
                        String fileName = extras.getString("fileName");
                        Uri image =Uri.parse(fileName);
                        try {
                            newHeaderImage = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(image));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    }
                }
                if (newHeaderImage != null) {
                    newHeaderImage = Utils.comp(newHeaderImage, 500, 500);
                    civ_head.setImageBitmap(newHeaderImage);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_person:
                Utils.StartActivity(getActivity(), Rl_personActivity.class);
                break;
            case R.id.rlTop:
                Utils.StartActivity(getActivity(), Rl_topActivity.class);
                break;
            case R.id.rl_mx:
                MainActivity mainActivity = (MainActivity) getActivity();
                if(mainActivity != null)
                {
                    mainActivity.initTab(2);
                }
                break;
            case R.id.rl_myParkRecord:
                Utils.StartActivity(getActivity(), Parking_Record_Activity.class);
                break;
            case R.id.rl_myPark:
                Utils.StartActivity(getActivity(),MyParking_Record_Activity.class);
                break;
            case R.id.rl_help:
                Utils.StartActivity(getActivity(),MyHelp_Activity.class);
                break;
            case R.id.rl_about:
                Utils.StartActivity(getActivity(), MyAbout_Activity.class);
                break;
            case R.id.rl_quit:
                Utils.StartActivity(getActivity(), LoginActivity.class,true);
                break;
            case R.id.civ_head:
                startActivityForResult(new Intent(getActivity(), SelectPicture.class), RESULT_CAMERA_IMAGE);
                break;
                default:
                    break;
        }
    }
}
