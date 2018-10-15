package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import model.ParkingModel;
import zhgxtc.com.zft_zhgxtc.R;

/**
 * Created by YJW on 2018/1/12.
 */

public class ParkingAdapter extends BaseAdapter {
    //定义需要包装的JSONArray对象
    public List<ParkingModel> modelList = new ArrayList<ParkingModel>();
    private Context context = null;
    //视图容器
    private LayoutInflater listContainer= null;
    public ParkingAdapter(Context ctx)
    {
        context = ctx;
        ////创建视图容器并设置上下文
        listContainer = LayoutInflater.from(ctx);
    }

    //自定义控件集合
    public final class ListItemView
    {
        TextView dwmc,dwdz,dis,cwCount,cwOver;
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
        ListItemView listItemView = null;
        if(arg1 == null)
        {
            arg1 = listContainer.inflate(R.layout.parking_activity_listviewitem, null);
            listItemView = new ListItemView();
            listItemView.dwmc = (TextView)arg1.findViewById(R.id.dwmc);
            listItemView.dwdz = (TextView)arg1.findViewById(R.id.dwdz);
            listItemView.dis = (TextView)arg1.findViewById(R.id.dis);
            listItemView.cwCount = (TextView)arg1.findViewById(R.id.cwCount);
            listItemView.cwOver = (TextView)arg1.findViewById(R.id.cwOver);
            //获取listview布局文件的视图
            arg1.setTag(listItemView);
        }
        else
        {
            listItemView = (ListItemView)arg1.getTag();
        }

        final ParkingModel tbl = (ParkingModel)this.getItem(arg0);

        listItemView.dwmc.setText(tbl.getDwmc());
        listItemView.dwdz.setText(tbl.getDwdz());
        listItemView.dis.setText(tbl.getDis()+"km");
        listItemView.cwCount.setText("￥"+tbl.getSpdj());
        listItemView.cwOver.setText(tbl.getCwOver()+"个");
        return arg1;
    }
}
