package zhgxtc.com.zft_zhgxtc;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import common.AdDialog;
import common.Utils;

public class Cw_Deatils_Activity extends BaseAppCompatActivity {
    private String[] tareImages = new String[4];
    ImageView tareImage1 = null;
    ImageView tareImage2 = null;
    ImageView tareImage3 = null;
    ImageView tareImage4 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cw__deatils_);
        goBack(this);

        AdDialog dialog=new AdDialog(this);
        dialog.show();

        tareImage1 = (ImageView)this.findViewById(R.id.tareImage1);
        tareImage2 = (ImageView)this.findViewById(R.id.tareImage2);
        tareImage3 = (ImageView)this.findViewById(R.id.tareImage3);
        tareImage4 = (ImageView)this.findViewById(R.id.tareImage4);

        tareImage1.setOnClickListener(new imgOnClick());
        tareImage2.setOnClickListener(new imgOnClick());
        tareImage3.setOnClickListener(new imgOnClick());
        tareImage4.setOnClickListener(new imgOnClick());
        tareImage1.setImageBitmap(((BitmapDrawable)this.getResources().getDrawable(R.mipmap.cw1)).getBitmap());
        tareImage2.setImageBitmap(((BitmapDrawable)this.getResources().getDrawable(R.mipmap.cw2)).getBitmap());
        tareImage3.setImageBitmap(((BitmapDrawable)this.getResources().getDrawable(R.mipmap.cw3)).getBitmap());
        tareImage4.setImageBitmap(((BitmapDrawable)this.getResources().getDrawable(R.mipmap.cw4)).getBitmap());
    }

    class imgOnClick implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {

            if(v.getId() == R.id.tareImage1 ||
                    v.getId() == R.id.tareImage2 ||
                    v.getId() == R.id.tareImage3 ||
                    v.getId() == R.id.tareImage4) {
                Bundle bundle = new Bundle();
                bundle.putStringArray("images", tareImages);
                bundle.putInt("imageid", v.getId());
                Utils.StartActivity(Cw_Deatils_Activity.this, QueryImage_View_Activity.class, bundle);
            }
        }
    };
}
