package com.alex.baulin.flashlight;


import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.util.Log;


class Camera {
    private final CameraManager manager;

    Camera(Context ctx) {
        manager = (CameraManager)ctx.getSystemService(Context.CAMERA_SERVICE);
    }

    void setFlashOn(boolean b) throws CameraAccessException {

        String[] cameraIdList = manager.getCameraIdList();

        for(String cameraId: cameraIdList) {
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
            if(isCameraBackFacing(characteristics) && isCameraFlashAvailable(characteristics)) {
                manager.setTorchMode(cameraId, b);
            }

            Log.d("myLogs", cameraId);
            Log.d("myLogs", "backFace = " + isCameraBackFacing(characteristics));
            Log.d("myLogs", "hasFlash = " + isCameraFlashAvailable(characteristics));
        }
    }

    private boolean isCameraBackFacing(CameraCharacteristics characteristics) {
        return characteristics.get(CameraCharacteristics.LENS_FACING) == CameraMetadata.LENS_FACING_BACK;
    }

    private boolean isCameraFlashAvailable(CameraCharacteristics characteristics) {
        return characteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
    }

}


