package common;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import zhgxtc.com.zft_zhgxtc.R;


/**
 * Created by kimhillzhang on 2017-11-16.
 */

public class DialogUtils {
    public static void ShowToast(final Context c, String msg)
    {
        Toast toast = Toast.makeText(c, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    public static AlertDialog showCustomerConfirmDialog(Context c, String msg, Button.OnClickListener okListener, Button.OnClickListener cancelListener)
    {
        final AlertDialog dlg = new AlertDialog.Builder(c).create();
        dlg.show();
        Window window = dlg.getWindow();
        window.setContentView(R.layout.confirmdialog_activity);
        TextView txtMsg = (TextView) window.findViewById(R.id.txtMsg);
        txtMsg.setText(msg);
        Button btnOk = (Button) window.findViewById(R.id.btnOk);
        Button btnCancel = (Button) window.findViewById(R.id.btnCancel);
        btnOk.setOnClickListener(okListener);
        if(cancelListener == null)
        {
            btnCancel.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View arg0) {
                    dlg.cancel();
                }
            });
        }
        else
        {
            btnCancel.setOnClickListener(cancelListener);
        }
        return dlg;
    }

}
