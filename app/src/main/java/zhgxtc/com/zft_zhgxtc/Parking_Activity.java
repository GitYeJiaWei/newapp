package zhgxtc.com.zft_zhgxtc;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import CityList.CityList.DbHelper.CityUtils;
import adapter.City_District_Adapter;
import adapter.ParkingAdapter;
import common.Utils;
import model.ParkingModel;

/**
 * Created by kimhillzhang on 2018-03-07.
 */

public class Parking_Activity extends BaseAppCompatActivity implements View.OnClickListener{

    private List<ParkingModel> parkingList =new ArrayList<>();
    private ListView listView;
    private ParkingAdapter parkingAdapter = null;
    private LinearLayout rl_seat;
    private TextView tv_CurrentName = null;
    private PopupWindow popOtherUser = null;
    private City_District_Adapter city_district_adapter = null;
    private CityUtils cityUtils = new CityUtils();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.parking_activity);
        goBack(this);

        rl_seat =(LinearLayout)findViewById(R.id.rl_seat);
        rl_seat.setOnClickListener(this);

        tv_CurrentName = (TextView)this.findViewById(R.id.tv_CurrentName);
        parkingAdapter =new ParkingAdapter(Parking_Activity.this);
        listView =(ListView) findViewById(R.id.list_parking);
        listView.setAdapter(parkingAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ParkingModel parkingModel =parkingList.get(position);
                Utils.StartActivity(Parking_Activity.this, Parking_CreateOrder_Activity.class);
            }
        });
        tv_CurrentName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.BackgroundAlpha(Parking_Activity.this, 0.5f);
                popOtherUser.showAsDropDown(Parking_Activity.this.findViewById(R.id.rlTop));
            }
        });
        initParking();
        showCityDistrict();
    }

    private void showCityDistrict()
    {
        LayoutInflater l=LayoutInflater.from(Parking_Activity.this);
        View v = l.inflate(R.layout.show_city_district_activity, null);
        popOtherUser = new PopupWindow(v, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置弹出窗体显示时的动画，从底部向上弹出
        popOtherUser.setAnimationStyle(R.style.take_photo_anim);
        popOtherUser.setOutsideTouchable(true);
        popOtherUser.setBackgroundDrawable(new ColorDrawable(Color.argb(255, 255, 0, 0)));
        popOtherUser.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Utils.BackgroundAlpha(Parking_Activity.this, 1f);
            }
        });
        ListView listView_OtherUser = (ListView)v.findViewById(R.id.listview_dist);
        city_district_adapter = new City_District_Adapter(Parking_Activity.this);

        city_district_adapter.modelList1 = cityUtils.getDistList(Parking_Activity.this, "福州市");

        listView_OtherUser.setAdapter(city_district_adapter);
        listView_OtherUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                popOtherUser.dismiss();
                Utils.BackgroundAlpha(Parking_Activity.this, 1f);
            }
        });
    }

    private void initParking(){
        for (int i=0;i<10;i++){
            ParkingModel a =new ParkingModel();
            a.setDwmc("物博创联地下停车场"+i);
            a.setDis(10+i);
            a.setSpdj("10");
            a.setCwOver(100+i);
            a.setDwdz("厦门市同安区北区大道5-"+i+"号");
            parkingList.add(a);
        }
        parkingAdapter.modelList = parkingList;
        parkingAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_seat:
                Utils.StartActivity(this,Cw_Activity.class, true);
                break;
                default:
                    break;
        }
    }
}