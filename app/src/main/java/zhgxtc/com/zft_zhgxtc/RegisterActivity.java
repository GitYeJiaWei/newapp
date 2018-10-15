package zhgxtc.com.zft_zhgxtc;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import adapter.MPagerAdapter;

public class RegisterActivity extends AppCompatActivity {
    private View view1 = null;
    private View view2 = null;
    private View view3 = null;
    private ImageView btnBack = null;
    private TextView txtOne = null;
    private TextView txtTwo = null;
    private TextView txtThree = null;
    private Button btnSelected = null;
    private Button btnValide = null;
    private Button btnRegister = null;
    private EditText editTell = null;
    private ViewPager viewPage = null;
    private ArrayList<View> vList1 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnBack = (ImageView)this.findViewById(R.id.btnBack);
        txtOne = (TextView)this.findViewById(R.id.txtOne);
        txtTwo = (TextView)this.findViewById(R.id.txtTwo);
        txtThree = (TextView)this.findViewById(R.id.txtThree);
        InitViewPage();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPage.getCurrentItem() == 0)
                {
                    RegisterActivity.this.finish();
                    return;
                }
                viewPage.setCurrentItem(viewPage.getCurrentItem() - 1, true);
            }
        });

    }
    /**
     * 初始化横向滑动界面
     * */
    void InitViewPage() {
        viewPage = (ViewPager) this.findViewById(R.id.viewPage);
        viewPage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        LayoutInflater inflater = LayoutInflater.from(RegisterActivity.this);
        view1 = inflater.inflate(R.layout.activity_register_01, null);
        view2 = inflater.inflate(R.layout.activity_register_02, null);
        view3 = inflater.inflate(R.layout.activity_register_03, null);
        vList1.add(view1);
        vList1.add(view2);
        vList1.add(view3);
        MPagerAdapter mPagerAdapter = new MPagerAdapter(vList1);


        btnSelected = (Button)view1.findViewById(R.id.btnSelected);
        btnValide = (Button)view2.findViewById(R.id.btnValide);
        btnRegister = (Button)view3.findViewById(R.id.btnRegister);

        btnSelected.setOnClickListener(new ButtonOnClick());
        btnValide.setOnClickListener(new ButtonOnClick());
        btnRegister.setOnClickListener(new ButtonOnClick());

        viewPage.setAdapter(mPagerAdapter);
        viewPage.setCurrentItem(0, true);
        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setPageStatus(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    void setPageStatus(int position)
    {
        if(position == 0)
        {
            txtOne.setTextColor(RegisterActivity.this.getResources().getColor(R.color.title_bar_color));
            txtTwo.setTextColor(RegisterActivity.this.getResources().getColor(R.color.txt_color_gray));
            txtThree.setTextColor(RegisterActivity.this.getResources().getColor(R.color.txt_color_gray));
        }
        if(position == 1)
        {
            txtOne.setTextColor(RegisterActivity.this.getResources().getColor(R.color.txt_color_gray));
            txtTwo.setTextColor(RegisterActivity.this.getResources().getColor(R.color.title_bar_color));
            txtThree.setTextColor(RegisterActivity.this.getResources().getColor(R.color.txt_color_gray));
        }
        if(position == 2)
        {
            txtOne.setTextColor(RegisterActivity.this.getResources().getColor(R.color.txt_color_gray));
            txtTwo.setTextColor(RegisterActivity.this.getResources().getColor(R.color.txt_color_gray));
            txtThree.setTextColor(RegisterActivity.this.getResources().getColor(R.color.title_bar_color));
        }
    }
    class ButtonOnClick implements  View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btnSelected)
            {
                viewPage.setCurrentItem(1, true);
            }
            if(v.getId() == R.id.btnValide)
            {
                viewPage.setCurrentItem(2, true);
            }
            if(v.getId() == R.id.btnRegister)
            {

            }
        }
    }
}
