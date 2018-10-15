package zhgxtc.com.zft_zhgxtc;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapter.CwAdapter;
import common.Utils;
import model.CwModel;

public class Cw_Activity extends BaseAppCompatActivity implements View.OnClickListener{
    private List<CwModel> cwModelList =new ArrayList<>();
    private ListView listView;
    private CwAdapter cwAdapter =null;
    private LinearLayout rl_parking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cw_);
        goBack(this);

        rl_parking = (LinearLayout) findViewById(R.id.rl_parking);
        rl_parking.setOnClickListener(this);

        cwAdapter =new CwAdapter(this);
        listView = (ListView) findViewById(R.id.list_parking);
        listView.setAdapter(cwAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        initCw();
    }

    private void initCw(){
            for (int i=0;i<10;i++){
                CwModel a =new CwModel();
                a.setDwdz("物博创联地下停车场车位 A00"+i);
                a.setDis(10+i);
                a.setCwprice("10");
                cwModelList.add(a);
            }
        cwAdapter.modelList = cwModelList;
        cwAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_parking:
                Utils.StartActivity(this,Parking_Activity.class, true);
                break;
                default:
        }
    }
}
