package zhgxtc.com.zft_zhgxtc;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import CityList.CityAdapter;
import CityList.CityList.DbHelper.AllCitySqliteOpenHelper;
import CityList.CityList.DbHelper.CityUtils;
import CityList.CityList.Model.CitySortModel;
import CityList.PinyinComparator;
import CityList.PinyinUtils;
import CityList.SideBar;
import CityList.SortAdapter;

/**
 * Created by kimhillzhang on 2018-03-09.
 * 获取城市列表本文件实现了两个数据库
 * 一个数据库是：meituan_cities.db，该数据库只有一个城市列表，并且有一个拼音的字段。getCityList()方法
 * 一个数据为虽：mzk_db.db，该数据库包含省市区，可通过sqlite工具打开查看，该数据库没有拼音字段，所以在弄出首字
 * 母时如果有多音字将会错误，如：厦门变成了shamen，跑到s去了；重庆变成了zhong qing，跑到z去了,现在将他们进行单独处理。filledData()方法
 */

public class Select_City_Activity extends BaseAppCompatActivity {
    private ListView sortListView = null;
    private SideBar sideBar = null;
    private TextView dialogTxt = null;
    private SortAdapter adapter = null;
    private List<CitySortModel> SourceDateList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.select_city_activity);
        goBack(this);
        initViews();
    }
    private void initViews() {
        sideBar = (SideBar) findViewById(R.id.sidrbar);
        dialogTxt = (TextView) findViewById(R.id.dialogTxt);
        sortListView = (ListView) findViewById(R.id.country_lvcountry);
        sideBar.setTextView(dialogTxt);//设置点击侧边放大字母
        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if(position != -1) {
                    sortListView.setSelection(position+1);
                }
            }
        });
        //ListView的点击事件
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplication(), ((CitySortModel) adapter.getItem(position - 1)).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        setAdapter();
    }
    private void setAdapter() {
        SourceDateList = filledData();//getCityList();
        Collections.sort(SourceDateList, new PinyinComparator());
        adapter = new SortAdapter(this, SourceDateList);
        sortListView.addHeaderView(initHeadView());
        sortListView.setAdapter(adapter);
    }

    /**
     * 列表头部，显示热门城市，从arrays文件取出
     * */
    private View initHeadView() {
        View headView = getLayoutInflater().inflate(R.layout.headview, null);
        GridView mGvCity = (GridView) headView.findViewById(R.id.gv_hot_city);
        String[] datas = getResources().getStringArray(R.array.city);
        ArrayList<String> cityList = new ArrayList<>();
        for (int i = 0; i < datas.length; i++) {
            cityList.add(datas[i]);
        }
        CityAdapter adapter = new CityAdapter(getApplicationContext(), R.layout.gridview_item, cityList);
        mGvCity.setAdapter(adapter);
        return headView;
    }


    /***
     * 获取本地区数据库 -- 只市-市带拼音
     * */
    private ArrayList<CitySortModel> getCityList() {
        SQLiteDatabase db;
        Cursor cursor = null;
        AllCitySqliteOpenHelper.ASSETS_NAME = "meituan_cities.db";
        AllCitySqliteOpenHelper.DB_NAME = "meituan_cities.db";
        AllCitySqliteOpenHelper op = new AllCitySqliteOpenHelper(Select_City_Activity.this);
        ArrayList<CitySortModel> cityList=new ArrayList<CitySortModel>();
        try {
            op.createDataBase();
            db = op.getWritableDatabase();
            cursor= db.rawQuery("select * from city",null);

            while (cursor.moveToNext()) {
                String cityName=cursor.getString(cursor.getColumnIndex("name"));
                String cityPinyin=cursor.getString(cursor.getColumnIndex("pinyin"));
                CitySortModel sortModel = new CitySortModel();
                sortModel.setName(cityName);
                sortModel.setSortLetters(cityPinyin.substring(0, 1).toUpperCase());
                sortModel.setPinyin(cityPinyin);
                cityList.add(sortModel);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            cursor.close();
        }
        return cityList;
    }
    /***
     * 获取本地区数据库 -- 带省市区 db
     * */
    private List<CitySortModel> filledData() {
        CityUtils cityUtils = new CityUtils();
        List<CitySortModel> cityList = cityUtils.getCityList(Select_City_Activity.this);
        List<CitySortModel> mSortList = new ArrayList<>();
        for (int i = 0; i < cityList.size(); i++) {
            CitySortModel sortModel = cityList.get(i);
            String Name = sortModel.getName();
            sortModel.setName(Name);
            String pinyin = PinyinUtils.getPingYin(sortModel.getName());
            if(Name.contains("厦门")) {
                pinyin = "xiamen";
            }
            else if(Name.contains("重庆")) {
                pinyin = "chongqing";
            }
            String sortString = pinyin.substring(0, 1).toUpperCase();
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            }
            mSortList.add(sortModel);
        }
        return mSortList;
    }
}
