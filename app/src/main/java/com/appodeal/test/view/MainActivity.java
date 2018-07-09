package com.appodeal.test.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.appodeal.test.R;
import com.appodeal.test.view.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick(R.id.btnBanner)
    public void btnBanner() {
        startActivity(new Intent(this, BannerActivity.class));
    }

    @OnClick(R.id.btnVideo)
    public void btnVideo() {
        startActivity(new Intent(this, VideoActivity.class));
    }

    @OnClick(R.id.btnNative)
    public void btnNative() {
        startActivity(new Intent(this, NativeActivity.class));
    }
}
