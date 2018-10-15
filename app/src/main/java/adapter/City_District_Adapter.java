package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import CityList.CityList.Model.DistrictModel;
import zhgxtc.com.zft_zhgxtc.R;

/**
 * Created by kimhillzhang on 2018-03-10.
 */

public class City_District_Adapter extends BaseAdapter {
    //定义需要包装的JSONArray对象
    public List<DistrictModel> modelList1 = new ArrayList<DistrictModel>();
    private Context context = null;
    //视图容器
    private LayoutInflater listContainer= null;
    public City_District_Adapter(Context ctx)
    {
        context = ctx;
        ////创建视图容器并设置上下文
        listContainer = LayoutInflater.from(ctx);
    }

    //自定义控件集合
    public final class ListItemView
    {
       private TextView txtName;
    }

    @Override
    public int getCount() {
        return this.modelList1.size();
    }

    @Override
    public Object getItem(int arg0) {
        if(getCount() > 0)
        {
            return this.modelList1.get(arg0);
        }
        return null;
    }

    @Override
    public long getItemId(int arg0) {

        return 0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        City_District_Adapter.ListItemView listItemView = null;
        if(arg1 == null)
        {
            arg1 = listContainer.inflate(R.layout.show_city_district_listviewitem, null);
            listItemView = new City_District_Adapter.ListItemView();
            listItemView.txtName = (TextView)arg1.findViewById(R.id.txtName);
            //获取listview布局文件的视图
            arg1.setTag(listItemView);
        }
        else
        {
            listItemView = (City_District_Adapter.ListItemView)arg1.getTag();
        }

        final DistrictModel tb2 = (DistrictModel)this.getItem(arg0);

        listItemView.txtName.setText(tb2.DistrictName);
        return arg1;
    }
}
