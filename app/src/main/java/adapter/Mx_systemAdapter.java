package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.MxModel;
import zhgxtc.com.zft_zhgxtc.R;

/**
 * Created by YJW on 2018/3/8.
 */

public class Mx_systemAdapter extends BaseAdapter {
    //定义需要包装的JSONArray对象
    public List<MxModel> modelList = new ArrayList<MxModel>();
    private Context context = null;
    //视图容器
    private LayoutInflater listContainer= null;
    public Mx_systemAdapter(Context ctx)
    {
        context = ctx;
        ////创建视图容器并设置上下文
        listContainer = LayoutInflater.from(ctx);
    }

    //自定义控件集合
    public final class ListItemView
    {
        TextView mx_time,mx_je,mx_sjh;
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
        Mx_systemAdapter.ListItemView listItemView = null;
        if(arg1 == null)
        {
            arg1 = listContainer.inflate(R.layout.mx_system_activity_listviewitem, null);
            listItemView = new Mx_systemAdapter.ListItemView();
            listItemView.mx_time = (TextView)arg1.findViewById(R.id.mx_time);
            listItemView.mx_je = (TextView)arg1.findViewById(R.id.mx_je);
            listItemView.mx_sjh = (TextView)arg1.findViewById(R.id.mx_sjh);
            //获取listview布局文件的视图
            arg1.setTag(listItemView);
        }
        else
        {
            listItemView = (Mx_systemAdapter.ListItemView)arg1.getTag();
        }

        final MxModel tbl = (MxModel)this.getItem(arg0);

        listItemView.mx_time.setText(tbl.getTime());
        listItemView.mx_je.setText(tbl.getJe()+"");
        listItemView.mx_sjh.setText(tbl.getSjh());
        return arg1;
    }
}
