package com.appodeal.test.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.BannerCallbacks;
import com.appodeal.ads.UserSettings;
import com.appodeal.test.R;
import com.appodeal.test.util.Const;
import com.appodeal.test.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BannerActivity extends BaseActivity implements BannerCallbacks {

    @BindView(R.id.btnShow) Button btnShow;
    @BindView(R.id.btnHide) Button btnHide;
    @BindView(R.id.pb) ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
    }

    @Override
    protected void adInit() {
        super.adInit();
        Appodeal.initialize(this, Const.APP_KEY, Appodeal.BANNER);
        Appodeal.setBannerCallbacks(this);
        Appodeal.setBannerViewId(R.id.bv);
        showToast(R.string.loading);
    }

    @OnClick(R.id.btnLoad)
    public void btnLoad() {
        if(Appodeal.isLoaded(Appodeal.BANNER)) {
            showToast(R.string.load_yet);
            return;
        }
        adInit();
        pb.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btnShow)
    public void btnShow() {
        Appodeal.show(this, Appodeal.BANNER_BOTTOM);
    }

    @OnClick(R.id.btnHide)
    public void btnHide() {
        Appodeal.hide(this, Appodeal.BANNER);
    }

    @Override
    public void onBannerLoaded(int height, boolean isPrecache) {
        btnShow.setEnabled(true);
        btnHide.setEnabled(true);
        pb.setVisibility(View.GONE);
        showToast(String.format("onBannerLoaded, %sdp, isPrecache: %s", height, isPrecache));
    }

    @Override
    public void onBannerFailedToLoad() {
        showToast("onBannerFailedToLoad");
    }

    @Override
    public void onBannerShown() {
        showToast("onBannerShown");
    }

    @Override
    public void onBannerClicked() {
        showToast("onBannerClicked");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Appodeal.destroy(Appodeal.BANNER);
    }
}
