package com.alex.baulin.flashlight;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import com.alex.baulin.test.R;

public class WhiteScreen extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whitescreen);
        fixScreenOrientation();
        setMaxBrightness();
    }

    private void fixScreenOrientation() {
        int orientation = this.getResources().getConfiguration().orientation;
        Log.d("myLogs", "orientation = " + orientation);
        if(orientation == Configuration.ORIENTATION_PORTRAIT) setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        else if(orientation == Configuration.ORIENTATION_LANDSCAPE) setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    private void setMaxBrightness() {
        Window w = getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_FULL;
        w.setAttributes(lp);
    }
}
