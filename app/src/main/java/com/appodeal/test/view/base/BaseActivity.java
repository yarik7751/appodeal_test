package com.appodeal.test.view.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.UserSettings;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(@StringRes  int res) {
        Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
    }

    protected void adInit() {
        Appodeal.getUserSettings(this)
                .setAge(25)
                .setGender(UserSettings.Gender.MALE);
        Appodeal.trackInAppPurchase(this, 10.0, "USD");
        Appodeal.setTesting(true);
    }
}
