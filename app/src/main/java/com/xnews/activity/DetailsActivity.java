package com.xnews.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xnews.R;
import com.xnews.base.BaseActivity;
import com.xnews.bean.NewDetailModle;
import com.xnews.bean.NewModle;
import com.xnews.callback.NewsDetailCallback;
import com.xnews.http.HttpRequest;
import com.xnews.http.json.NewDetailJson;
import com.xnews.utils.NetWorkHelper;
import com.xnews.utils.SharedPreferencesUtils;
import com.xnews.utils.ToastUtils;
import com.xnews.view.ProgressPieView;
import com.xnews.view.htmltextview.HtmlTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;


public class DetailsActivity extends BaseActivity
//        implements ImageLoadingListener,
//        ImageLoadingProgressListener
{

    protected ImageView mPlay;
    @Bind(R.id.back)
    TextView back;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.title_bar_divider)
    View titleBarDivider;
    @Bind(R.id.new_title)
    TextView newTitle;
    @Bind(R.id.new_time)
    TextView newTime;
    @Bind(R.id.new_img)
    ImageView newImg;
    @Bind(R.id.wb_details)
    HtmlTextView wbDetails;
    @Bind(R.id.img_count)
    TextView imgCount;
    @Bind(R.id.progressPieView)
    ProgressPieView mProgressPieView;
    @Bind(R.id.play)
    ImageView play;
    @Bind(R.id.scrollview)
    ScrollView scrollview;
    @Bind(R.id.root_view)
    RelativeLayout rootView;
    private String newUrl;
    private NewModle newModle;
    private String newID;
    private String imgCountString;


    private NewDetailModle newDetailModle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        initWebView();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void initData() {
        try {
            newModle = (NewModle) getIntent().getExtras().getSerializable("newModle");
            newID = newModle.getDocid();
            newUrl = getUrl(newID);

            getDataFromNet(newID, newUrl);

//            imageLoader = ImageLoader.getInstance();
//            options = Options.getListOptions();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从网络获取数据
     */
    private void getDataFromNet(String newID, String newUrl) {
        if (NetWorkHelper.isNetworkAvailable(mContext)) {
            HttpRequest.getInstance().getNewsDetailData(new NewsDetailCallback(mContext, newID, newUrl) {

                @Override
                public void onBefore(Request request) {
                    super.onBefore(request);
//                    if (index == 0 && isFirst) {
                    mProgressPieView.setVisibility(View.VISIBLE);
//                    }
                }

                @Override
                public void onAfter() {
                    super.onAfter();
                    if (mProgressPieView != null) {
                        mProgressPieView.setVisibility(View.GONE);
                    }
//                    if (listview != null) {
//                        listview.onBottomComplete();
//                    }
//                    if (swipeContainer != null) {
//                        swipeContainer.setRefreshing(false);
//                    }
                }

                @Override
                public void onError(Request request, Exception e) {
                    if (mProgressPieView != null) {
                        mProgressPieView.setVisibility(View.GONE);
                    }
                    ToastUtils.showLong(mContext, "请求失败！");
                }

                @Override
                public void onResponse(NewDetailModle newDetailModle) {
                    setData(newDetailModle);
                }
            }, newUrl);
        } else {
            String str = SharedPreferencesUtils.getString(mContext, newUrl);
            SharedPreferencesUtils.putString(mContext, newUrl, str);
            NewDetailModle newDetailModle = NewDetailJson.instance(mContext).readJsonNewModles(str,
                    newID);
            setData(newDetailModle);

        }
    }

    private void setData(NewDetailModle newDetailModle) {
        if (!"".equals(newDetailModle.getUrl_mp4())) {
            Picasso.with(mContext).load(newDetailModle.getCover()).into(newImg);
            newImg.setVisibility(View.VISIBLE);
        } else {
            if (newDetailModle.getImgList().size() > 0) {
                imgCountString = "共" + newDetailModle.getImgList().size() + "张";
                Picasso.with(mContext).load(newDetailModle.getImgList().get(0)).into(newImg);
                newImg.setVisibility(View.VISIBLE);
            }
        }
        newTitle.setText(newDetailModle.getTitle());
        newTime.setText("来源：" + newDetailModle.getSource() + " " + newDetailModle.getPtime());
        String content = newDetailModle.getBody();
        content = content.replace("<!--VIDEO#1--></p><p>", "");
        content = content.replace("<!--VIDEO#2--></p><p>", "");
        content = content.replace("<!--VIDEO#3--></p><p>", "");
        content = content.replace("<!--VIDEO#4--></p><p>", "");
        content = content.replace("<!--REWARD#0--></p><p>", "");
        wbDetails.setHtmlFromString(content, false);
        dismissProgressDialog();
    }

    public void initWebView() {
        try {
            mProgressPieView.setShowText(true);
            mProgressPieView.setShowImage(false);
            // WebSettings settings = webView.getSettings();
            // settings.setJavaScriptEnabled(true);// 设置可以运行JS脚本
            // settings.setDefaultFontSize(16);
            // settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
            // settings.setSupportZoom(false);// 用于设置webview放大
            // settings.setBuiltInZoomControls(false);
            // webView.setBackgroundResource(R.color.transparent);
            // webView.setWebViewClient(new MyWebViewClient());
            showProgressDialog();
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.new_img)
    public void imageMore(View view) {
        try {
            Bundle bundle = new Bundle();
            bundle.putSerializable("newDetailModle", newDetailModle);
            if (!"".equals(newDetailModle.getUrl_mp4())) {
                bundle.putString("playUrl", newDetailModle.getUrl_mp4());
                openActivity(VideoPlayActivity.class, bundle, 0);
            } else {
                openActivity(ImageDetailActivity.class, bundle, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 监听
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            view.getSettings().setJavaScriptEnabled(true);
            super.onPageFinished(view, url);
            // html加载完成之后，添加监听图片的点击js函数
            // progressBar.setVisibility(View.GONE);
            dismissProgressDialog();
            wbDetails.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            view.getSettings().setJavaScriptEnabled(true);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            // progressBar.setVisibility(View.GONE);
            dismissProgressDialog();
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }

//    @Override
//    public void onLoadingStarted(String imageUri, View view) {
//        mProgressPieView.setVisibility(View.VISIBLE);
//    }
//
//    @Override
//    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//        mProgressPieView.setVisibility(View.GONE);
//    }
//
//    @Override
//    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//        if (!"".equals(newDetailModle.getUrl_mp4())) {
//            mPlay.setVisibility(View.VISIBLE);
//        } else {
//            imgCount.setVisibility(View.VISIBLE);
//            imgCount.setText(imgCountString);
//        }
//        mProgressPieView.setVisibility(View.GONE);
//    }
//
//    @Override
//    public void onLoadingCancelled(String imageUri, View view) {
//        mProgressPieView.setVisibility(View.GONE);
//    }
//
//    @Override
//    public void onProgressUpdate(String imageUri, View view, int current, int total) {
//        int currentpro = (current * 100 / total);
//        if (currentpro == 100) {
//            mProgressPieView.setVisibility(View.GONE);
//            mProgressPieView.setShowText(false);
//        } else {
//            mProgressPieView.setVisibility(View.VISIBLE);
//            mProgressPieView.setProgress(currentpro);
//            mProgressPieView.setText(currentpro + "%");
//        }
//    }

    @Override
    public void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }
}
