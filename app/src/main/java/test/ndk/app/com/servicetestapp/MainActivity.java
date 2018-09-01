package test.ndk.app.com.servicetestapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button buttonStart, buttonStop, buttonBind, buttonUnBind, buttonGetNo;
    TextView textView;
    Intent intentService;

    int count  = 0;
    private MyAsyncTask myAsyncTask;
    private MyService myService;
    private boolean isServiceBound;
    private ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("onCreate", "In onCreate, Thread id : "+Thread.currentThread().getId());

        textView = findViewById(R.id.textView);
        buttonStart = findViewById(R.id.buttonStart);
        buttonStop = findViewById(R.id.buttonStop);
        buttonBind = findViewById(R.id.buttonBind);
        buttonUnBind = findViewById(R.id.buttonUnBind);
        buttonGetNo = findViewById(R.id.buttonGetNo);



        intentService = new Intent(getApplicationContext(), MyService.class);
//        startService(intentService);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
        buttonBind.setOnClickListener(this);
        buttonUnBind.setOnClickListener(this);
        buttonGetNo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonStart:startService(intentService);break;
            case R.id.buttonStop:stopService(intentService);break;
            case R.id.buttonBind:bindServices();break;
            case R.id.buttonUnBind:unBindServices();break;
            case R.id.buttonGetNo:setRandomNo();break;
        }
    }

    private void bindServices(){
        if(serviceConnection == null){
         serviceConnection = new ServiceConnection() {
             @Override
             public void onServiceConnected(ComponentName name, IBinder service) {

                 MyService.MyServiceBinder myServiceBinder = (MyService.MyServiceBinder)service;
                 myService = myServiceBinder.getService();
                 isServiceBound = true;

             }

             @Override
             public void onServiceDisconnected(ComponentName name) {

                 isServiceBound = false;
             }
         };
        }
        bindService(intentService, serviceConnection, Context.BIND_AUTO_CREATE);
    }
    private void unBindServices(){
        if(isServiceBound){
            unbindService(serviceConnection);
            isServiceBound = false;
        }
    }
    private void setRandomNo(){
        if(isServiceBound){
            textView.setText("Random Number : "+myService.getRandomNo());
        }else {
            textView.setText("Service is not bound.");
        }

    }

    private class MyAsyncTask extends AsyncTask<Integer, Integer, Integer>{

        private int customNumber;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            customNumber = 0;
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
        }
    }

}
