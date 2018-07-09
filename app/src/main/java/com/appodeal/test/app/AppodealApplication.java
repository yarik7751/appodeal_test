package com.appodeal.test.app;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.UserSettings;
import com.appodeal.test.util.Const;

public class AppodealApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }
}
