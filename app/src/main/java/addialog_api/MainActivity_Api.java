package addialog_api;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.uuch.adlibrary.AdConstant;
import com.uuch.adlibrary.AdManager;
import com.uuch.adlibrary.bean.AdInfo;
import com.uuch.adlibrary.transformer.DepthPageTransformer;
import com.uuch.adlibrary.transformer.RotateDownPageTransformer;

import java.util.ArrayList;
import java.util.List;

import zhgxtc.com.zft_zhgxtc.R;

/**
 * ���Թ������demo
 */
public class MainActivity_Api extends AppCompatActivity {

    private List<AdInfo> advList = null;
    private Spinner spinner = null;
    private Button button1 = null;
    private EditText editText = null;
    private Button button2 = null;
    private Button button3 = null;
    private Button button4 = null;
    private Button button5 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        initView();
        initListener();
    }

    /**
     * ��ʼ������
     */
    private void initData() {
        advList = new ArrayList<>();
        AdInfo adInfo = new AdInfo();
        adInfo.setActivityImg("https://raw.githubusercontent.com/yipianfengye/android-adDialog/master/images/testImage1.png");
        advList.add(adInfo);

        adInfo = new AdInfo();
        adInfo.setActivityImg("https://raw.githubusercontent.com/yipianfengye/android-adDialog/master/images/testImage2.png");
        advList.add(adInfo);
    }

    /**
     * ��ʼ�����
     */
    private void initView() {
        List<DataBean> mList = new ArrayList<>();
        mList.add(new DataBean(-1, "��ѡ���浯����������"));
        mList.add(new DataBean(AdConstant.ANIM_DOWN_TO_UP, "�������ϵ�����浯��"));
        mList.add(new DataBean(AdConstant.ANIM_UP_TO_DOWN, "�������µ�����浯��"));
        mList.add(new DataBean(AdConstant.ANIM_LEFT_TO_RIGHT, "�������ҵ�����浯��"));
        mList.add(new DataBean(AdConstant.ANIM_RIGHT_TO_LEFT, "�������󵯳���浯��"));
        mList.add(new DataBean(AdConstant.ANIM_UPLEFT_TO_CENTER, "�����ϵ�����浯��"));
        mList.add(new DataBean(AdConstant.ANIM_UPRIGHT_TO_CENTER, "�����ϵ�����浯��"));
        mList.add(new DataBean(AdConstant.ANIM_DOWNLEFT_TO_CENTER, "�����µ�����浯��"));
        mList.add(new DataBean(AdConstant.ANIM_DOWNRIGHT_TO_CENTER, "�����µ�����浯��"));

    }

    /**
     * ��ʼ���¼�����
     */
    private void initListener() {

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AdManager adManager = new AdManager(MainActivity_Api.this, advList);
                adManager.setOverScreen(true)
                        .setPageTransformer(new DepthPageTransformer());
                switch (position) {
                    /**
                     * �������ϵ�����浯��
                     */
                    case 1:
                        adManager.showAdDialog(AdConstant.ANIM_DOWN_TO_UP);
                        break;
                    /**
                     * �������µ�����浯��
                     */
                    case 2:
                        adManager.showAdDialog(AdConstant.ANIM_UP_TO_DOWN);
                        break;
                    /**
                     * �������ҵ�����浯��
                     */
                    case 3:
                        adManager.showAdDialog(AdConstant.ANIM_LEFT_TO_RIGHT);
                        break;
                    /**
                     * �������󵯳���浯��
                     */
                    case 4:
                        adManager.showAdDialog(AdConstant.ANIM_RIGHT_TO_LEFT);
                        break;
                    /**
                     * �����ϵ�����浯��
                     */
                    case 5:
                        adManager.showAdDialog(AdConstant.ANIM_UPLEFT_TO_CENTER);
                        break;
                    /**
                     * �����ϵ�����浯��
                     */
                    case 6:
                        adManager.showAdDialog(AdConstant.ANIM_UPRIGHT_TO_CENTER);
                        break;
                    /**
                     * �����µ�����浯��
                     */
                    case 7:
                        adManager.showAdDialog(AdConstant.ANIM_DOWNLEFT_TO_CENTER);
                        break;
                    /**
                     * �����µ�����浯��
                     */
                    case 8:
                        adManager.showAdDialog(AdConstant.ANIM_DOWNRIGHT_TO_CENTER);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdManager adManager = new AdManager(MainActivity_Api.this, advList);
                String result = editText.getText().toString();
                if (TextUtils.isEmpty(result)) {
                    Toast.makeText(MainActivity_Api.this, "�����뵯�������ĽǶ�!", Toast.LENGTH_SHORT).show();
                    return;
                }

                adManager.setOnImageClickListener(new AdManager.OnImageClickListener() {
                    @Override
                    public void onImageClick(View view, AdInfo advInfo) {
                        Toast.makeText(MainActivity_Api.this, "�������ViewPagerItem...", Toast.LENGTH_SHORT).show();
                    }
                });
                adManager.showAdDialog(Integer.parseInt(result));
            }
        });

        /**
         * �Զ������ù������������Ļ��������Լ���߱�
         */
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdManager adManager = new AdManager(MainActivity_Api.this, advList);

                adManager.setOnImageClickListener(new AdManager.OnImageClickListener() {
                    @Override
                    public void onImageClick(View view, AdInfo advInfo) {
                        Toast.makeText(MainActivity_Api.this, "�������ViewPagerItem...", Toast.LENGTH_SHORT).show();
                    }
                })
                        .setPadding(100)
                        .setWidthPerHeight(0.5f)
                        .showAdDialog(AdConstant.ANIM_UP_TO_DOWN);
            }
        });

        /**
         * �Զ��嵯��������ɫ,�����Ƿ񸲸�ȫ��,�رհ�ť�Ƿ���ʾ��
         */
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdManager adManager = new AdManager(MainActivity_Api.this, advList);

                adManager.setOnImageClickListener(new AdManager.OnImageClickListener() {
                    @Override
                    public void onImageClick(View view, AdInfo advInfo) {
                        Toast.makeText(MainActivity_Api.this, "�������ViewPagerItem...", Toast.LENGTH_SHORT).show();
                    }
                })
                        .setBackViewColor(Color.parseColor("#AA333333"))
                        .setDialogCloseable(false)
                        .showAdDialog(AdConstant.ANIM_UP_TO_DOWN);
            }
        });

        /**
         * �Զ����趨�������Բ������ٶȲ���
         */
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdManager adManager = new AdManager(MainActivity_Api.this, advList);

                adManager.setOnImageClickListener(new AdManager.OnImageClickListener() {
                    @Override
                    public void onImageClick(View view, AdInfo advInfo) {
                        Toast.makeText(MainActivity_Api.this, "�������ViewPagerItem...", Toast.LENGTH_SHORT).show();
                    }
                })
                        .setBounciness(20)
                        .setSpeed(4)
                        .showAdDialog(AdConstant.ANIM_UP_TO_DOWN);
            }
        });

        /**
         * �Զ������õ���ViewPager��������
         */
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdManager adManager = new AdManager(MainActivity_Api.this, advList);

                adManager.setOnImageClickListener(new AdManager.OnImageClickListener() {
                    @Override
                    public void onImageClick(View view, AdInfo advInfo) {
                        Toast.makeText(MainActivity_Api.this, "�������ViewPagerItem...", Toast.LENGTH_SHORT).show();
                    }
                })
                        .setPageTransformer(new RotateDownPageTransformer())
                        .showAdDialog(AdConstant.ANIM_UP_TO_DOWN);
            }
        });
    }

}
