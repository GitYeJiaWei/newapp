package adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.ArrayList;

public class MPagerAdapter extends  PagerAdapter {
	private ArrayList<View> pageView = null;
	public MPagerAdapter(ArrayList<View> _pageView)
	{
		pageView = _pageView;
	}

	@Override
	public int getCount() { 
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
		position %= pageView.size();
		if(position < 0)
		{
			position = pageView.size() + position;
		}
		View view = pageView.get(position);
		//如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
		ViewParent vp =view.getParent();
		if (vp!=null){
			ViewGroup parent = (ViewGroup)vp;
			parent.removeView(view);
		}
		container.addView(view);
		return view;
	}

}
