package zhgxtc.com.zft_zhgxtc;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.util.DebugUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.fragment.HomeFragment;
import com.fragment.MxFragment;
import com.fragment.MyFragment;
import com.fragment.ServicesFragment;
import common.DialogUtils;

public class MainActivity extends  AppCompatActivity implements View.OnClickListener {
    // 三个tab布局
    private RelativeLayout rl_home, rl_query, rl_clienter, rl_my;

    // 底部标签切换的Fragment,获取增删改的集合transaction
    private Fragment homeFragment, queryFragment, myFragment, clienterFragment, currentFragment;
    private FragmentTransaction transaction;
    private FragmentManager fragmentManger;
    // 底部标签图片
    private ImageView iv_query, iv_home, iv_my, iv_clienter;
    // 底部标签的文本
    private TextView tv_home, tv_query, tv_my, tv_clienter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());

        this.setContentView(R.layout.activity_main);
        fragmentManger = getFragmentManager();
        transaction = fragmentManger.beginTransaction();

        initUI();
        initTab(0);

    }
    /**
     * 初始化UI
     */
    private void initUI() {
        rl_home = (RelativeLayout) findViewById(R.id.rl_home);
        rl_query = (RelativeLayout) findViewById(R.id.rl_query);
        rl_clienter = (RelativeLayout) findViewById(R.id.rl_client);
        rl_my = (RelativeLayout) findViewById(R.id.rl_me);

        rl_home.setOnClickListener(this);
        rl_query.setOnClickListener(this);
        rl_clienter.setOnClickListener(this);
        rl_my.setOnClickListener(this);

        iv_home = (ImageView) findViewById(R.id.iv_home);
        iv_query = (ImageView) findViewById(R.id.iv_query);
        iv_my = (ImageView) findViewById(R.id.iv_me);
        iv_clienter = (ImageView) findViewById(R.id.iv_client);

        tv_home = (TextView) findViewById(R.id.tv_home);
        tv_query = (TextView) findViewById(R.id.tv_query);
        tv_my = (TextView) findViewById(R.id.tv_me);
        tv_clienter = (TextView) findViewById(R.id.tv_client);
    }
    /**
     * 初始化底部标签
     */
    public void initTab(int i) {
        iv_home.setImageResource(R.mipmap.myparking0000);
        tv_home.setTextColor(getResources().getColor(R.color.txt_color_gray));

        iv_query.setImageResource(R.mipmap.service00);
        tv_query.setTextColor(getResources().getColor(R.color.txt_color_gray));

        iv_clienter.setImageResource(R.mipmap.mx00_);
        tv_clienter.setTextColor(getResources().getColor(R.color.txt_color_gray));

        iv_my.setImageResource(R.mipmap.my00_);
        tv_my.setTextColor(getResources().getColor(R.color.txt_color_gray));

        if(i == 0)
        {
            if (homeFragment == null) {
                homeFragment = new HomeFragment();
            }
            addOrShowFragment(transaction, homeFragment);
            iv_home.setImageResource(R.mipmap.mypark00001);
            tv_home.setTextColor(getResources().getColor(R.color.title_bar_color));
        }
        else if(i == 1)
        {
            if (queryFragment == null) {
                queryFragment = new ServicesFragment();
            }
            addOrShowFragment(transaction, queryFragment);
            iv_query.setImageResource(R.mipmap.service01);
            tv_query.setTextColor(getResources().getColor(R.color.title_bar_color));
        }
        else if(i == 2)
        {
            if (clienterFragment == null) {
                clienterFragment = new MxFragment();
            }
            addOrShowFragment(transaction, clienterFragment);
            iv_clienter.setImageResource(R.mipmap.mx01_);
            tv_clienter.setTextColor(getResources().getColor(R.color.title_bar_color));
        }
        else if(i == 3)
        {
            if (myFragment == null) {
                myFragment = new MyFragment();
            }
            addOrShowFragment(transaction, myFragment);
            iv_my.setImageResource(R.mipmap.my01_);
            tv_my.setTextColor(getResources().getColor(R.color.title_bar_color));
        }
    }

    /**
     * 添加或者显示碎片
     *
     * @param transaction
     * @param fragment
     */
    private void addOrShowFragment(FragmentTransaction transaction, Fragment fragment) {
        if (currentFragment == fragment)
            return;
        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            if(currentFragment == null)
            {
                transaction.add(R.id.content, fragment).commitAllowingStateLoss();
            }
            else
            {
                //transaction.hide(currentFragment).add(R.id.content_layout, fragment).commit();//不重新加载
                transaction.remove(currentFragment).add(R.id.content, fragment).commitAllowingStateLoss();
            }
        } else {
            //transaction.hide(currentFragment).show(fragment).commit();//不重新加载
            transaction.remove(currentFragment).show(fragment).commitAllowingStateLoss();
        }
        currentFragment = fragment;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_home: // 停车
                initTab(0);
                break;
            case R.id.rl_query: // 服务
                initTab(1);
                break;
            case R.id.rl_client: // 对账
                initTab(2);
                break;
            case R.id.rl_me: // 我的
                initTab(3);
                break;
            default:
                break;
        }
    }
    // 返回键
    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){

                DialogUtils.ShowToast(MainActivity.this, "再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                MainActivity.this.finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
