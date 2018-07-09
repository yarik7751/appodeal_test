package com.appodeal.test.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.RewardedVideoCallbacks;
import com.appodeal.test.R;
import com.appodeal.test.util.Const;
import com.appodeal.test.view.base.BaseActivity;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialListener;

import butterknife.BindView;
import butterknife.OnClick;

public class VideoActivity extends BaseActivity implements RewardedVideoCallbacks {

    @BindView(R.id.btnShow) Button btnShow;
    @BindView(R.id.pb) ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        if(Appodeal.isLoaded(Appodeal.REWARDED_VIDEO)) {
            btnShow.setEnabled(true);
        }
    }

    @Override
    protected void adInit() {
        super.adInit();
        Appodeal.initialize(this, Const.APP_KEY, Appodeal.REWARDED_VIDEO);
        Appodeal.setRewardedVideoCallbacks(this);
        showToast(R.string.loading);
    }

    @OnClick(R.id.btnLoad)
    public void btnLoad() {
        if(Appodeal.isLoaded(Appodeal.REWARDED_VIDEO)) {
            showToast(R.string.load_yet);
            return;
        }
        adInit();
        pb.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btnShow)
    public void btnShow() {
        Appodeal.show(this, Appodeal.REWARDED_VIDEO);
    }

    @Override
    public void onRewardedVideoLoaded() {
        btnShow.setEnabled(true);
        pb.setVisibility(View.GONE);
        showToast("onRewardedVideoLoaded");
    }

    @Override
    public void onRewardedVideoFailedToLoad() {
        showToast("onRewardedVideoFailedToLoad");
    }

    @Override
    public void onRewardedVideoShown() {
        showToast("onRewardedVideoShown");
    }

    @Override
    public void onRewardedVideoFinished(int amount, String name) {
        showToast(String.format("onRewardedVideoFinished. Reward: %d %s", amount, name));
    }

    @Override
    public void onRewardedVideoClosed(boolean b) {
        showToast("onRewardedVideoClosed: " + b);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Appodeal.destroy(Appodeal.REWARDED_VIDEO);
    }
}
