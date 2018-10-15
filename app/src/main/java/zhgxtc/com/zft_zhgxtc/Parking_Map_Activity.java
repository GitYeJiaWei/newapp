package zhgxtc.com.zft_zhgxtc;

import android.content.Intent;
import android.os.Bundle;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.MapView;
import com.uuch.adlibrary.AdConstant;
import com.uuch.adlibrary.AdManager;
import com.uuch.adlibrary.bean.AdInfo;
import com.uuch.adlibrary.transformer.DepthPageTransformer;

import java.util.ArrayList;
import java.util.List;

import map.utils.MapServices;

/**
 * Created by kimhillzhang on 2018-03-16.
 */

public class Parking_Map_Activity extends BaseAppCompatActivity {
    private MapServices mapServices = null;
    private MapView mapView;
    public static final int RESULT_CAMERA_IMAGE = 0x09;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.parking_map_activity);
        goBack(this);

        //初始化mapview
        mapView =(MapView) this.findViewById(R.id.dmapView);
        mapServices = new MapServices(Parking_Map_Activity.this, mapView);
        mapServices.SetMapView();
        mapServices.SetOnLocationCallback(new MapServices.LocationCallbackInterface() {
            @Override
            public void getLoaction(BDLocation location) {
               // Toast.makeText(Parking_Map_Activity.this, location.getLatitude() +"==="+location.getLongitude(), Toast.LENGTH_LONG).show();
            }
        });
        //底部弹出信息窗口
        startActivityForResult(new Intent(Parking_Map_Activity.this, Parking_Map_SelectWindow_Activity.class), RESULT_CAMERA_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        mapServices.Start();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapServices.Stop();
    }
}
