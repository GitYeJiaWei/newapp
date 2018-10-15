package CityList.CityList.DbHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import CityList.CityList.Model.CitySortModel;
import CityList.CityList.Model.DistrictModel;
import CityList.CityList.Model.ProvinceModel;

/**
 * Created by kimhillzhang on 2018-03-10.
 */

public class CityUtils {
    public CityUtils()
    {
        AllCitySqliteOpenHelper.ASSETS_NAME = "mzk_db.db";
        AllCitySqliteOpenHelper.DB_NAME = "mzk_db.db";
    }
    /*
   * 获取所有省
   * */
    public List<ProvinceModel> getProvinceList(Context context) {
        SQLiteDatabase db;
        Cursor cursor = null;
        AllCitySqliteOpenHelper op=new AllCitySqliteOpenHelper(context);
        ArrayList<ProvinceModel> proList=new ArrayList<ProvinceModel>();
        try {
            op.createDataBase();
            db = op.getWritableDatabase();
            cursor = db.rawQuery("select * from fs_province", null); //fs_province省表   fs_district地区表
            while (cursor.moveToNext()) {
                String ProvinceName=cursor.getString(cursor.getColumnIndex("ProvinceName"));
                int id=cursor.getInt(cursor.getColumnIndex("ProvinceID"));
                ProvinceModel sortModel = new ProvinceModel();
                sortModel.ProvinceName = ProvinceName;
                sortModel.ProvinceID = id;
                proList.add(sortModel);
            }
        }
        catch (Exception ex)
        {

        }
        return proList;
    }
    /*
    * 获取某市下的区
    * */
    public List<DistrictModel> getDistList(Context context, int cityid) {
        SQLiteDatabase db;
        Cursor cursor = null;
        AllCitySqliteOpenHelper op=new AllCitySqliteOpenHelper(context);
        ArrayList<DistrictModel> disList=new ArrayList<DistrictModel>();
        try {
            op.createDataBase();
            db = op.getWritableDatabase();
            cursor = db.rawQuery("select * from fs_district where CityID="+cityid, null); //fs_province省表   fs_district地区表
            while (cursor.moveToNext()) {
                String disName=cursor.getString(cursor.getColumnIndex("DistrictName"));
                int id=cursor.getInt(cursor.getColumnIndex("DistrictID"));
                DistrictModel sortModel = new DistrictModel();
                sortModel.DistrictName = disName;
                sortModel.DistrictID  = id;
                disList.add(sortModel);
            }
        }
        catch (Exception ex)
        {

        }
        return disList;
    }
    /*
    * 获取某市下的区
    * */
    public List<DistrictModel> getDistList(Context context, String cityname) {
        SQLiteDatabase db;
        Cursor cursor = null;
        AllCitySqliteOpenHelper op=new AllCitySqliteOpenHelper(context);
        ArrayList<DistrictModel> disList=new ArrayList<DistrictModel>();
        try {
            op.createDataBase();
            db = op.getWritableDatabase();
            cursor = db.rawQuery("select * from fs_district t1 inner join fs_city t2 on t1.CityID=t2.CityID where t2.CityName='"+cityname+"'", null); //fs_province省表   fs_district地区表
            while (cursor.moveToNext()) {
                String disName=cursor.getString(cursor.getColumnIndex("DistrictName"));
                int id=cursor.getInt(cursor.getColumnIndex("DistrictID"));
                DistrictModel sortModel = new DistrictModel();
                sortModel.DistrictName = disName;
                sortModel.DistrictID  = id;
                disList.add(sortModel);
            }
        }
        catch (Exception ex)
        {

        }
        return disList;
    }
    /*
    * 获取所有市区
    * */
    public List<CitySortModel> getCityList(Context context) {
        SQLiteDatabase db;
        Cursor cursor = null;
        AllCitySqliteOpenHelper op=new AllCitySqliteOpenHelper(context);
        ArrayList<CitySortModel> cityList=new ArrayList<CitySortModel>();
        try {
            op.createDataBase();
            db = op.getWritableDatabase();
            cursor = db.rawQuery("select * from fs_city", null); //fs_province省表   fs_district地区表
            while (cursor.moveToNext()) {
                String cityName=cursor.getString(cursor.getColumnIndex("CityName"));
                int id=cursor.getInt(cursor.getColumnIndex("CityID"));
                CitySortModel sortModel = new CitySortModel();
                sortModel.setName(cityName);
                sortModel.setId(id);
                cityList.add(sortModel);
            }
        }
        catch (Exception ex)
        {

        }
        return cityList;
    }
}
