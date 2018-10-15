package zhgxtc.com.zft_zhgxtc;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import PhotoView.PhotoView;
import adapter.QueryImage_Adapter;

/**
 * Created by kimhillzhang on 2017-11-22.
 */

public class QueryImage_View_Activity extends AppCompatActivity {
    private ViewPager mPager;
    public List<PhotoView> photoViewList = new ArrayList<>();
    private QueryImage_Adapter queryImage_adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.queryimages_view_activity);
        String[] images = this.getIntent().getStringArrayExtra("images");

        PhotoView view = new PhotoView(QueryImage_View_Activity.this);
        view.enable();
        view.setScaleType(ImageView.ScaleType.FIT_CENTER);
        view.setImageBitmap(((BitmapDrawable)QueryImage_View_Activity.this.getResources().getDrawable(R.mipmap.cw1)).getBitmap());
        photoViewList.add(view);

        view = new PhotoView(QueryImage_View_Activity.this);
        view.enable();
        view.setScaleType(ImageView.ScaleType.FIT_CENTER);
        view.setImageBitmap(((BitmapDrawable)QueryImage_View_Activity.this.getResources().getDrawable(R.mipmap.cw2)).getBitmap());
        photoViewList.add(view);

        view = new PhotoView(QueryImage_View_Activity.this);
        view.enable();
        view.setScaleType(ImageView.ScaleType.FIT_CENTER);
        view.setImageBitmap(((BitmapDrawable)QueryImage_View_Activity.this.getResources().getDrawable(R.mipmap.cw3)).getBitmap());
        photoViewList.add(view);

        view = new PhotoView(QueryImage_View_Activity.this);
        view.enable();
        view.setScaleType(ImageView.ScaleType.FIT_CENTER);
        view.setImageBitmap(((BitmapDrawable)QueryImage_View_Activity.this.getResources().getDrawable(R.mipmap.cw4)).getBitmap());
        photoViewList.add(view);




        queryImage_adapter = new QueryImage_Adapter();
        queryImage_adapter.photoViewList = photoViewList;
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
        mPager.setAdapter(queryImage_adapter);

        mPager.setCurrentItem(0);
    }
}
