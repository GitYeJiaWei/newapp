package common;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kimhillzhang on 2017-11-14.
 */

public class CloseActivity {
    public static List<Activity> activityList = new ArrayList<Activity>();
    public static void exitClient()
    {
        // 关闭所有Activity
        for (int i = 0; i < activityList.size(); i++)
        {
            if (null != activityList.get(i))
            {
                Activity activity = activityList.get(i);
                activity.finish();
            }
        }
    }
    public static void AddActivity(Activity activity)
    {
        if(!CloseActivity.activityList.contains(activity))
        {
            CloseActivity.activityList.add(activity);
        }
    }
    public static void RemoveActivity(Context ctx)
    {
        if(CloseActivity.activityList.size()>0)
        {
            CloseActivity.activityList.remove((Activity)ctx);
        }
    }
}
