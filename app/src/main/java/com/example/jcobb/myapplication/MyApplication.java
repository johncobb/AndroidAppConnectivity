// Credit Source: http://www.androidhive.info/2012/07/android-detect-internet-connection-status/

package com.example.jcobb.myapplication;

import android.app.Application;

/**
 * Created by jcobb on 4/4/17.
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }


}
