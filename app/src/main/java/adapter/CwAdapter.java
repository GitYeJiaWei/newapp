package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import common.CwDialog;
import common.Utils;
import model.CwModel;
import zhgxtc.com.zft_zhgxtc.Cw_Deatils_Activity;
import zhgxtc.com.zft_zhgxtc.R;

/**
 * Created by YJW on 2018/3/23.
 */

public class CwAdapter extends BaseAdapter{
    //定义需要包装的JSONArray对象
    public List<CwModel> modelList = new ArrayList<CwModel>();
    private Context context = null;
    //视图容器
    private LayoutInflater listContainer= null;
    public CwAdapter(Context ctx)
    {
        context = ctx;
        ////创建视图容器并设置上下文
        listContainer = LayoutInflater.from(ctx);
    }

    //自定义控件集合
    public final class ListItemView
    {
        TextView dwdz,cwprice,dis,cw_details,dw;
        Button order,parking,navi;
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
        CwAdapter.ListItemView listItemView = null;
        if(arg1 == null)
        {
            arg1 = listContainer.inflate(R.layout.cw_listview_item, null);
            listItemView = new CwAdapter.ListItemView();
            listItemView.dwdz = arg1.findViewById(R.id.dwdz);
            listItemView.dis = arg1.findViewById(R.id.dis);
            listItemView.cwprice = arg1.findViewById(R.id.cwprice);
            listItemView.cw_details =arg1.findViewById(R.id.cw_details);
            listItemView.dw = arg1.findViewById(R.id.dw);
            listItemView.order =arg1.findViewById(R.id.order);
            listItemView.parking =arg1.findViewById(R.id.parking);
            listItemView.navi =arg1.findViewById(R.id.navi);
            //获取listview布局文件的视图
            arg1.setTag(listItemView);
        }
        else
        {
            listItemView = (CwAdapter.ListItemView)arg1.getTag();
        }

        final CwModel tbl = (CwModel)this.getItem(arg0);

        listItemView.dwdz.setText(tbl.getDwdz());
        listItemView.dis.setText(tbl.getDis()+"km");
        listItemView.cwprice.setText(tbl.getCwprice()+"元");

        /*详情*/
        listItemView.cw_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.StartActivity(context, Cw_Deatils_Activity.class);
            }
        });

        /*费用详情*/
        listItemView.dw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CwDialog cwDialog =new CwDialog(context);
                cwDialog.show();
            }
        });

        /*预约*/
        listItemView.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /*停车*/
        listItemView.parking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /*导航*/
        listItemView.navi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return arg1;
    }
}
