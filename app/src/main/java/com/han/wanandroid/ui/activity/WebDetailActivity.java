package com.han.wanandroid.ui.activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.han.wanandroid.R;
import com.han.wanandroid.base.BaseActivity;
import com.han.wanandroid.base.BasePresenter;
import com.han.wanandroid.utils.Constant;
import com.han.wanandroid.utils.baseutils.LogUtils;
import com.han.wanandroid.widget.IconFontTextView;

import butterknife.BindView;


public class WebDetailActivity extends BaseActivity {


    @BindView(R.id.tv_return)
    IconFontTextView tvReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_topbar_layout)
    RelativeLayout rlTopbarLayout;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.webview_container)
    NestedScrollView webviewContainer;
    @BindView(R.id.web_detail_llayout)
    CoordinatorLayout webDetailLlayout;

    private WebView webDetailView;

    private String webUrl = "";

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void init() {
        initWebView();
        initWebViewSetting();
        initIntentData();
        initAppbar();
    }

    private void initAppbar() {
        tvReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initIntentData() {
        webUrl = getIntent().getStringExtra(Constant.WEB_DETAIL_URL);
        LogUtils.e(TAG, "weburl:" + webUrl);
        if (null == webUrl || webUrl.equalsIgnoreCase("")) {
            return;
        }
        webDetailView.loadUrl(webUrl);
    }

    private void initWebViewSetting() {
        WebSettings settings = webDetailView.getSettings();

        webDetailView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                tvTitle.setText(title);
                super.onReceivedTitle(view, title);
            }
        });
        webDetailView.setWebViewClient(new WebViewClient());

        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setBlockNetworkImage(false);
        settings.setDomStorageEnabled(true);
        settings.setSupportZoom(true);
        settings.setDisplayZoomControls(false);
        settings.setBuiltInZoomControls(true);
        //开启缓存
        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

    }

    private void initWebView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        webDetailView = new WebView(getApplicationContext());
        webDetailView.setLayoutParams(params);
        webDetailView.setInitialScale(100);
        webviewContainer.addView(webDetailView);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_web_detail;
    }


}
