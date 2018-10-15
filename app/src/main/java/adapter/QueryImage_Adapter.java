package adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.ArrayList;
import java.util.List;

import PhotoView.PhotoView;

/**
 * Created by kimhillzhang on 2017-12-26.
 */

public class QueryImage_Adapter extends PagerAdapter {
    public List<PhotoView> photoViewList = new ArrayList<>();
    @Override
    public int getCount() {
        return photoViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position %= photoViewList.size();
        if(position < 0)
        {
            position = photoViewList.size() + position;
        }
        PhotoView view = photoViewList.get(position);
        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent vp =view.getParent();
        if (vp!=null){
            ViewGroup parent = (ViewGroup)vp;
            parent.removeView(view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if(position>photoViewList.size()-1)
        {
            return;
        }
        container.removeView((View) object);
    }
}
