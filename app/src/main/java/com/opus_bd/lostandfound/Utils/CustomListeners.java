package com.opus_bd.lostandfound.Utils;

import android.graphics.Bitmap;

public class CustomListeners {
    public static MyFaceDetectedListener myFaceDetectedListener;
    public static BitmapGeneratedListener bitmapGeneratedListener;
    public static imageReceiverListener imageReceiverListener_;
    //------------------------------

    public static void setMyFaceDetectedListener(MyFaceDetectedListener l) {
        CustomListeners.myFaceDetectedListener = l;
    }

    public static void setBitmapGeneratedListener(BitmapGeneratedListener l) {
        bitmapGeneratedListener = l;
    }

    public static void setImageReceiverListener(imageReceiverListener l) {
        imageReceiverListener_ = l;
    }

    //-----------------------------


    public interface MyFaceDetectedListener {
        void onFaceFound();
    }

    public interface BitmapGeneratedListener {
        void onBitmapGenerated(Bitmap image);
    }

    public interface imageReceiverListener {
        void onBitmapFound(Bitmap image);
    }

}
