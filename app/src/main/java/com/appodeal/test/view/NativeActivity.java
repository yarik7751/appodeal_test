package com.appodeal.test.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.Native;
import com.appodeal.ads.NativeAd;
import com.appodeal.ads.NativeCallbacks;
import com.appodeal.test.R;
import com.appodeal.test.util.Const;
import com.appodeal.test.view.adapter.NativeListAdapter;
import com.appodeal.test.view.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NativeActivity extends BaseActivity implements NativeCallbacks {

    @BindView(R.id.btnShow) Button btnShow;
    @BindView(R.id.btnHide) Button btnHide;
    @BindView(R.id.pb) ProgressBar pb;
    @BindView(R.id.llNative) LinearLayout llNative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);
        if(Appodeal.isLoaded(Appodeal.NATIVE)) {
            btnShow.setEnabled(true);
            btnHide.setEnabled(true);
        }
    }

    @Override
    protected void adInit() {
        super.adInit();
        Appodeal.setNativeCallbacks(this);
        Appodeal.initialize(this, Const.APP_KEY, Appodeal.NATIVE);
        buttonsEnabled(true);
    }

    @OnClick(R.id.btnLoad)
    public void btnLoad() {
        if(Appodeal.isLoaded(Appodeal.NATIVE)) {
            showToast(R.string.load_yet);
            return;
        }
        adInit();
        startLoad();
    }

    @OnClick(R.id.btnShow)
    public void btnShow() {
        //hideNativeAd();
        List<NativeAd> nativeAds = Appodeal.getNativeAds(5);

        NativeListAdapter nativeListViewAdapter = new NativeListAdapter(llNative,2);
        for (NativeAd nativeAd : nativeAds) {
            nativeListViewAdapter.addNativeAd(nativeAd);
        }
        llNative.setTag(nativeListViewAdapter);
        nativeListViewAdapter.rebuild();
    }

    @OnClick(R.id.btnHide)
    public void btnHide() {
        hideNativeAd();
    }

    private void endLoad() {
        pb.setVisibility(View.GONE);
    }

    private void startLoad() {
        pb.setVisibility(View.VISIBLE);
    }

    private void buttonsEnabled(boolean enabled) {
        btnShow.setEnabled(enabled);
        btnHide.setEnabled(enabled);
    }

    private void hideNativeAd() {
        llNative.removeAllViews();
        NativeListAdapter nativeListViewAdapter = (NativeListAdapter) llNative.getTag();
        if (nativeListViewAdapter != null) {
            for (int i = 0; i < nativeListViewAdapter.getCount(); i++) {
                NativeAd nativeAd = (NativeAd) nativeListViewAdapter.getItem(i);
                nativeAd.unregisterViewForInteraction();
            }
            nativeListViewAdapter.clear();
        }
    }

    @Override
    public void onNativeLoaded() {
        endLoad();
        buttonsEnabled(true);
        showToast("onNativeLoaded");
    }

    @Override
    public void onNativeFailedToLoad() {
        endLoad();
        buttonsEnabled(false);
        showToast("onNativeFailedToLoad");
    }

    @Override
    public void onNativeShown(NativeAd nativeAd) {
        showToast("onNativeShown");
    }

    @Override
    public void onNativeClicked(NativeAd nativeAd) {
        showToast("onNativeClicked");
    }
}
