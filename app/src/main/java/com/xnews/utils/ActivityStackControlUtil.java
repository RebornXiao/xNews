package com.xnews.utils;

import android.app.Activity;
import android.content.Intent;

import com.xnews.R;
import com.xnews.base.BaseActivity;

import java.util.Stack;


public class ActivityStackControlUtil {

    private static Stack<String> sActivityStack = new Stack<String>();

    private static Stack<Activity> activityStack = new Stack<Activity>();

    public static BaseActivity sCurrentActivity = null;

    // private Context context;

    private static final String TAG = "ActivityStackControlUtil";

    private static ActivityStackControlUtil instance;

    public static ActivityStackControlUtil getInstance() {
        if (instance == null) {
            instance = new ActivityStackControlUtil();
        }
        return instance;
    }


    public void jumpTo(Class<?> cl) {
        jumpTo(new Intent(sCurrentActivity, cl));
    }

    public void jumpTo(Intent intent) {
        jump(intent, R.anim.push_left_in, R.anim.push_left_out);
    }

    public void jumpBackTo(Class<?> cl) {
        jump(new Intent(sCurrentActivity, cl), android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
    }

    private void jump(Intent intent, int enterAnim, int exitAnim) {
        MLog.d("{" + intent.getComponent().getClassName() + "} <- { "
                + sCurrentActivity.getClass().getName() + "}");
        String target = intent.getComponent().getClassName();
        if (sActivityStack.contains(target)) {
            popAllActivityExceptMain(target);
        } else if (sCurrentActivity != null) {
            sCurrentActivity.startActivity(intent);
            sCurrentActivity.overridePendingTransition(enterAnim, exitAnim);
        }
    }

    public static boolean isCurrActivityTop(Class<?> cls) {
        if (sCurrentActivity != null && cls != null) {
            return sCurrentActivity.getClass().getName().equals(cls.getClass().getName());
        }
        return false;
    }

    public void onCreate(BaseActivity activity) {
        sCurrentActivity = activity;
        pushActivity(activity);
    }

    public void onResume(BaseActivity activity) {
        sCurrentActivity = activity;
    }

    public void onDestroy(BaseActivity activity) {
        popActivity(activity);
    }

    /**
     * <获取当前栈顶Activity> <功能详细描述>
     *
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Activity currentActivity() {
        if (activityStack == null || activityStack.size() == 0) {
            return null;
        }
        Activity activity = activityStack.lastElement();

        MLog.e(TAG, "get current activity:"
                + activity.getClass().getSimpleName());
        return activity;
    }

    /**
     * <将Activity入栈> <功能详细描述>
     *
     * @param activity
     * @see [类、类#方法、类#成员]
     */
    public void pushActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        MLog.e(TAG, "push stack activity:"
                + activity.getClass().getSimpleName());
        activityStack.add(activity);
    }

    /**
     * <退出栈顶Activity> <功能详细描述>
     *
     * @param activity
     * @see [类、类#方法、类#成员]
     */
    public void popActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            MLog.e(TAG, "remove current activity:"
                    + activity.getClass().getSimpleName());
            activityStack.remove(activity);
            activity = null;
        }
    }

    /**
     * <退出栈中所有Activity,当前的activity除外> <功能详细描述>
     *
     * @param clsName
     * @see [类、类#方法、类#成员]
     */
    public void popAllActivityExceptMain(String clsName) {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            if (activity.getClass().getName().equals(clsName)) {
                break;
            }

            popActivity(activity);
        }
    }

}
