package common;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import zhgxtc.com.zft_zhgxtc.R;


public class ProgressDialogUtils {
	private static Dialog loadingDialog = null;
	private static TextView tipTextView = null;
	public static void close()
	{
		if(loadingDialog != null)
		{
			loadingDialog.dismiss();
			loadingDialog = null;
		}
	}
	public static boolean isNull()
	{
		if(loadingDialog == null)
			return true;
		return false;
	}
	public static void setReText(String text)
	{
		if(tipTextView != null)
			tipTextView.setText(text);
	}
	/**
	 * �õ��Զ����progressDialog
	 * @param context
	 * @param msg
	 * @return
	 */
	public static void createLoadingDialog(Context context, String msg) {

		if(loadingDialog != null)
		{
			if(tipTextView != null)
				tipTextView.setText(msg);
		}
		else
		{
			LayoutInflater inflater = LayoutInflater.from(context);
			View v = inflater.inflate(R.layout.loading_dialog, null);
			tipTextView = (TextView) v.findViewById(R.id.tipTextView);
			if(msg !=null && msg.equals("") == false)
			{
				tipTextView.setText(msg);
			}
	
			loadingDialog = new Dialog(context, R.style.loading_dialog);
	
			loadingDialog.setCancelable(true);
			loadingDialog.setCanceledOnTouchOutside(false);
			loadingDialog.setContentView(v, new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT));
			loadingDialog.setOnCancelListener(new OnCancelListener(){

				@Override
				public void onCancel(DialogInterface arg0) {
					close();
				}
				
			});
			loadingDialog.show();
		}  
	}
}
