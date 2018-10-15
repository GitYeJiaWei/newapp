package zhgxtc.com.zft_zhgxtc;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import adapter.ParkingRecordAdapter;
import common.Utils;
import custom.view.AutoListView;
import model.ParkingRecordModel;

/**
 * Created by kimhillzhang on 2018-03-08.
 */

public class Parking_Record_Activity extends BaseAppCompatActivity {
    private custom.view.AutoListView parking_recordlist;
    private ParkingRecordAdapter parkingRecordAdapter=null;
    private List<ParkingRecordModel> parkingRecordModelList =new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageView top;
    int page = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.parking_record_activity);
        goBack(this);

        top =(ImageView)this.findViewById(R.id.top);

        parking_recordlist =(custom.view.AutoListView) findViewById(R.id.paiking_record);
        parkingRecordAdapter =new ParkingRecordAdapter(this);
        parking_recordlist.setAdapter(parkingRecordAdapter);

        parking_recordlist.setOnLoadListener(new AutoListView.OnLoadListener() {
            @Override
            public void onLoad() {
                onRefreshfruit();
                page++;
            }

            @Override
            public void onAfterScroll(int firstVisibleItem) {
                if (firstVisibleItem>=2){
                    top.setVisibility(View.VISIBLE);
                }else {
                    top.setVisibility(View.GONE);
                }
            }
        });
        parking_recordlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == parkingRecordAdapter.getCount())
                    return;
                ParkingRecordModel parkingRecordModel=parkingRecordModelList.get(position);
                String state=parkingRecordModel.getState();
                if (state.equals("已支付")){
                    Utils.StartActivity(Parking_Record_Activity.this,Parking_Record_Details_Activity.class);
                }else if (state.equals("未停车")){
                    Utils.StartActivity(Parking_Record_Activity.this,Parking_PlaceOrder_Details_Activity.class);
                }else{
                    Utils.StartActivity(Parking_Record_Activity.this,Parking_Record_Pay_Activity.class);

                }
            }
        });

        swipeRefreshLayout =(SwipeRefreshLayout)this.findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                onRefreshfruit();
            }
        });

        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT>=6){
                    parking_recordlist.smoothScrollToPosition(0);//返回顶部由滑动效果
                }else {
                    parking_recordlist.setSelection(0);//直接返回顶部
                }
            }
        });
        initParkingRecord();
    }


    /**
     * SwipeRefreshLayout 上拉刷新
     */
    private void onRefreshfruit(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initParkingRecord();//初始化数据
                        parkingRecordAdapter.notifyDataSetChanged();//更新adapter
                        swipeRefreshLayout.setRefreshing(false);//刷新结束，隐藏刷新进度条

                        parking_recordlist.onLoadComplete();
                        parking_recordlist.setResultSize(9);
                    }
                });
            }
        }).start();
    }

    private void initParkingRecord(){
        for (int i=0;i<10;i++){
            ParkingRecordModel a =new ParkingRecordModel();
            a.setSjh("闽DC8U81"+i);
            if (i==2 || i==4){
                a.setState("未停车");
            }else if (i==1 || i==6 || i==7){
                a.setState("已停车");
            }else{
                a.setState("已支付");
            }
            a.setMoney(10+i);
            a.setStrattime("2018-12-12 22:22"+i);
            a.setStoptime("2018-12-12 22:22"+i);
            parkingRecordModelList.add(a);
        }
        parkingRecordAdapter.modelList = parkingRecordModelList;
        parkingRecordAdapter.notifyDataSetChanged();
    }
}