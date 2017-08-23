package app.com.ravi.callrecorder.view;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by NetSupport on 23-08-2017.
 */

public class BaseApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}