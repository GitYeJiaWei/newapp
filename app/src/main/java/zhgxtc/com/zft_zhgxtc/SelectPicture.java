package zhgxtc.com.zft_zhgxtc;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fragment.MyFragment;

import java.io.File;
import java.io.IOException;

public class SelectPicture extends BaseAppCompatActivity implements View.OnClickListener {

    private Button btn_take_photo, btn_pick_photo, btn_cancel;
    private LinearLayout layout;
    private Intent intent;
    private String fileName = "";
    private File outputImage;
    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_picture);
        intent = getIntent();
        btn_take_photo = (Button) this.findViewById(R.id.btn_take_photo);
        btn_pick_photo = (Button) this.findViewById(R.id.btn_pick_photo);
        btn_cancel = (Button) this.findViewById(R.id.btn_cancel);

        layout = (LinearLayout) findViewById(R.id.pop_layout);

        layout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！",
                        Toast.LENGTH_SHORT).show();
            }
        });
        btn_cancel.setOnClickListener(this);
        btn_pick_photo.setOnClickListener(this);
        btn_take_photo.setOnClickListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if(data != null)
        {
            if (data.getExtras() != null)
            {
                intent.putExtras(data.getExtras());
            }
            if (data.getData()!= null)
                intent.setData(data.getData());
        }
        else
        {
            Bundle bun = new Bundle();
            bun.putString("fileName", imageUri+"");
            intent.putExtras(bun);
        }
        setResult(1, intent);
        finish();

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_take_photo:
               /* try {
                    File cacheDir = null;
                    String name = System.currentTimeMillis()+".jpg";
                    if (Environment.getExternalStorageState().equals(
                            Environment.MEDIA_MOUNTED))
                        cacheDir = new File(
                                Environment.getExternalStorageDirectory(),
                                "/QLWEIGHT");
                    else
                        cacheDir = SelectPicture.this.getCacheDir();
                    if (!cacheDir.exists())
                        cacheDir.mkdirs();
                    fileName = cacheDir.getPath()+"/"+name;
                    File file = new File(cacheDir,fileName);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    startActivityForResult(intent, MyFragment.RESULT_CAMERA_IMAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
                //创建File对象，用于存储拍照后的图片
                outputImage =new File(getExternalCacheDir(),"output_image.jpg");
                try{
                    if (outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT>=24){
                    imageUri = FileProvider.getUriForFile(SelectPicture.this,
                            "zhgxtc.com.zft_zhgxtc.SelectPicture",outputImage);
                }else{
                    imageUri =Uri.fromFile(outputImage);
                }
                //启动相机程序
                Intent intent1 =new Intent("android.media.action.IMAGE_CAPTURE");
                intent1.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent1,MyFragment.RESULT_CAMERA_IMAGE);
                break;
            case R.id.btn_pick_photo:
                try {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, 2);
                } catch (ActivityNotFoundException e) {

                }
                break;
            case R.id.btn_cancel:
                finish();
                break;
            default:
                break;
        }

    }

}

