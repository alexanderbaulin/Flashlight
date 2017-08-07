package com.alex.baulin.flashlight;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.alex.baulin.test.R;


public class Menu extends Activity {
    private Camera camera;
    private boolean isFlashOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        camera = new Camera(getApplicationContext());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("isFlashOn", isFlashOn);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        isFlashOn = (boolean)savedInstanceState.get("isFlashOn");
        switchFlashButtonImage(isFlashOn);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        Log.d("myLogs", "onBackPressed");
        setFlashOn(false);
        super.onBackPressed();
    }

    public void flashLight(View v) {
        if(!isFlashFeatureAvailable()) {
            showAlertDialog(getResources().getString(R.string.flashNotSupported));
            return;
        }
        isFlashOn = !isFlashOn;
        setFlashOn(isFlashOn);

    }

    public void whiteScreen(View v) {
        startActivity(new Intent(this, WhiteScreen.class));
    }

    private void setFlashOn(boolean b) {
        try {
            camera.setFlashOn(b);
            switchFlashButtonImage(b);
        } catch (CameraAccessException e) {
            showAlertDialog(getResources().getString(R.string.cameraNotAvailable));
            Log.e("myLogs", e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            Log.e("myLogs", e.getMessage());
        }
    }

    private void switchFlashButtonImage(boolean b) {
        ImageButton btn = (ImageButton)findViewById(R.id.btnFlashLight);
        if(b) btn.setImageResource(R.drawable.ic_flash_off_white_36px);
        else  btn.setImageResource(R.drawable.ic_flash_on_white_36px);
    }

    private void showAlertDialog(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(s);
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    private boolean isFlashFeatureAvailable() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

}








