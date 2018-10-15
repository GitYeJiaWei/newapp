package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import model.ParkingRecordModel;
import zhgxtc.com.zft_zhgxtc.R;

/**
 * Created by YJW on 2018/3/9.
 */

public class ParkingRecordAdapter extends BaseAdapter {

        //定义需要包装的JSONArray对象
        public List<ParkingRecordModel> modelList = new ArrayList<ParkingRecordModel>();
        private Context context = null;
        //视图容器
        private LayoutInflater listContainer= null;
        public ParkingRecordAdapter(Context ctx)
        {
            context = ctx;
            ////创建视图容器并设置上下文
            listContainer = LayoutInflater.from(ctx);
        }

        //自定义控件集合
        public final class ListItemView
        {
            TextView sjh,state,money,starttime,stoptime;
        }

        @Override
        public int getCount() {
        return this.modelList.size();
    }

        @Override
        public Object getItem(int arg0) {
        if(getCount() > 0)
        {
            return this.modelList.get(arg0);
        }
        return null;
    }

        @Override
        public long getItemId(int arg0) {

        return 0;
    }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {
            ParkingRecordAdapter.ListItemView listItemView = null;
        if(arg1 == null)
        {
            arg1 = listContainer.inflate(R.layout.parking_record_activity_listviewitem, null);
            listItemView = new ParkingRecordAdapter.ListItemView();
            listItemView.sjh = (TextView)arg1.findViewById(R.id.sjh);
            listItemView.state = (TextView)arg1.findViewById(R.id.state);
            listItemView.money = (TextView)arg1.findViewById(R.id.money);
            listItemView.starttime = (TextView)arg1.findViewById(R.id.starttime);
            listItemView.stoptime = (TextView)arg1.findViewById(R.id.stoptime);
            //获取listview布局文件的视图
            arg1.setTag(listItemView);
        }
        else
        {
            listItemView = (ParkingRecordAdapter.ListItemView)arg1.getTag();
        }

        final ParkingRecordModel tbl = (ParkingRecordModel)this.getItem(arg0);

        listItemView.sjh.setText(tbl.getSjh());
        if (tbl.getState().equals("已支付")){
            listItemView.state.setTextColor(arg1.getResources().getColor(R.color.txt_color_subgray));
        }else if (tbl.getState().equals("未停车")){
            listItemView.state.setTextColor(arg1.getResources().getColor(R.color.green));
        }else{
            listItemView.state.setTextColor(arg1.getResources().getColor(R.color.red));
        }
        listItemView.state.setText(tbl.getState());
        listItemView.money.setText(tbl.getMoney()+"元");
        listItemView.starttime.setText("进场时间："+tbl.getStrattime());
        listItemView.stoptime.setText("出场时间："+tbl.getStoptime());
        return arg1;
    }
}
