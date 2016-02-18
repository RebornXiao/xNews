package com.xnews.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.xnews.R;
import com.xnews.app.MyApp;
import com.xnews.utils.StringUtils;

/**
 * fragment享元类
 * Created by xiao on 2016/2/16.
 */
public class BaseFragment extends Fragment {
    public Context mContext;
    public MyApp app;
    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mContext.setTheme(R.style.MyAppTheme);
        app = (MyApp) mContext.getApplicationContext();
    }

    protected void gotoNextActivity(Bundle mBundle, Class<?> cls) {
        if (getActivity() != null
                && getActivity() instanceof FragmentContorlListener) {
            FragmentContorlListener mListener = (FragmentContorlListener) getActivity();
            mListener.gotoNextActivity(mBundle, cls);
        }
    }

    protected void gotoNextFragment(int id, Bundle mBundle, Class<?> cls) {
        if (getActivity() != null
                && getActivity() instanceof FragmentContorlListener) {
            FragmentContorlListener mListener = (FragmentContorlListener) getActivity();
            mListener.gotoNextFragment(id, mBundle, cls);
        }
    }

    public interface FragmentContorlListener {
        void gotoNextActivity(Bundle mBundle, Class<?> cls);

        void gotoNextFragment(int id, Bundle mBundle, Class<?> cls);

    }

    public void dismissTipsDialogs() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
//        mDialogFactory.dissmissProgressDialog();
    }

    public void showTipsDialogs(final String title, final String msg) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mContext);
        }
//                Drawable drawable = getResources().getDrawable(
//                        R.drawable.loading_animation);
//                mProgressDialog.setIndeterminateDrawable(drawable);
        mProgressDialog.setTitle(title);
        mProgressDialog.setMessage(msg);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
//        mDialogFactory.showProgressDialog(this, title, msg);
    }

    public boolean isNullString(String imgUrl) {
        if (StringUtils.isEmpty(imgUrl)) {
            return true;
        }
        return false;
    }

}
