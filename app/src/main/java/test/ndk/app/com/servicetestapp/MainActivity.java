package test.ndk.app.com.servicetestapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button buttonStart, buttonStop, buttonBind, buttonUnBind, buttonGetNo;
    Intent intentService;

//    int count  = 0;
//    private MyAsyncTask myAsyncTask;
//    private MyService myService;
//    private boolean isServiceBound;
//    private ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("onCreate", "In onCreate, Thread id : "+Thread.currentThread().getId());

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
//            case R.id.buttonBind:stopService(intentService);break;
//            case R.id.buttonUnBind:stopService(intentService);break;
//            case R.id.buttonGetNo:stopService(intentService);break;
        }
    }
/*
    private void bindServices(){
        if(serviceConnection == null){
         serviceConnection = new ServiceConnection() {
             @Override
             public void onServiceConnected(ComponentName name, IBinder service) {

             }

             @Override
             public void onServiceDisconnected(ComponentName name) {

             }
         }
        }
    }
    private void unBindServices(){

    }
    private void getRandomNo(){

    }*/


}
