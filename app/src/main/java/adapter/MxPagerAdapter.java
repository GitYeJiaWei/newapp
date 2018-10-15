package adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import zhgxtc.com.zft_zhgxtc.R;

/**
 * Created by kimhillzhang on 2018-03-07.
 */

public class MxPagerAdapter extends PagerAdapter {
    private ArrayList<View> pageView = new ArrayList<View>();
    private View view1 = null;
    private View view2 = null;
    public MxPagerAdapter(Context context)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        view1 = inflater.inflate(R.layout.mx_system_activity, null);
        view2 = inflater.inflate(R.layout.mx_union_activity, null);
        pageView.add(view1);
        pageView.add(view2);
    }
    public View getView1()
    {
        return view1;
    }
    public View getView2()
    {
        return view2;
    }
    @Override    public int getCount() {
        return pageView.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        ((ViewPager)container).removeView(pageView.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager)container).addView(pageView.get(position));
        return pageView.get(position);
    }

}