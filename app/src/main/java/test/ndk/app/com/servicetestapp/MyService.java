package test.ndk.app.com.servicetestapp;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

public class MyService extends Service {

    boolean mIsRandomGeneratorOn;
    int mRandomNumber;
    private final int MIN = 0;
    private final int MAX = 100;

/**
 *TO BIND COMPONENT WITH SERVICE
 */
    private IBinder mBinder = new MyServiceBinder();

    class MyServiceBinder extends Binder {

        public MyService getService(){
            return MyService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(getResources().getString(R.string.app_name), "In onStartCommand, Service started");
        mIsRandomGeneratorOn = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                startRandomNumberGenerator();
            }
        }).start();
//        return super.onStartCommand(intent, flags, startId);
        return START_STICKY;

    }

    private void startRandomNumberGenerator(){
        while (mIsRandomGeneratorOn) {
            try {
                Thread.sleep(1000);
                if (mIsRandomGeneratorOn) {
                    mRandomNumber = new Random().nextInt(MAX) + MIN;
                    Log.i(getResources().getString(R.string.app_name), " In MyService, Thread is : " + Thread.currentThread().getId()+
                    ", Random Number is : "+mRandomNumber);
                    if(mRandomNumber == 55){
                        stopSelf();
                    }
                }
            } catch (InterruptedException ie) {
                Log.e(getResources().getString(R.string.app_name), "#####Error : " + ie, ie);
            }
        }
    }

    private void stopRandomNumberGenerator(){
        mIsRandomGeneratorOn = false;
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(getResources().getString(R.string.app_name), "In onBind");
        return mBinder;
    }
//
//    @Override
//    public void onRebind(Intent intent) {
//        super.onRebind(intent);
//        Log.i(getResources().getString(R.string.app_name), "In onReBind");
//    }
//
//    @Override
//    public boolean onUnbind(Intent intent) {
//        Log.i(getResources().getString(R.string.app_name), "In unBind");
//        return super.onUnbind(intent);
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MyService", "In onDestroy, Service Stopped");
        stopRandomNumberGenerator();
    }


}
