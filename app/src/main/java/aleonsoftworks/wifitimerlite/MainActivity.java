package aleonsoftworks.wifitimerlite;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import aleonsoftworks.wifitimerlite.config.SWLConfig;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    public SWLConfig swlconfig = new SWLConfig();

    private TimePicker alarmTimePicker;
    private PendingIntent pendingIntent;
    private static MainActivity inst;
    AlarmManager alarmManager;

    public static MainActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWifiState();
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(swlconfig.primaryColor,swlconfig.secondaryColor);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    public void ClickUpdate(View view){
        mSwipeRefreshLayout.setRefreshing(true);
        refresher();
    }

    public void ClickToAdd(View view){
        TextView pickerOn = (TextView)findViewById(R.id.literalStartAt);
        TextView addStart = (TextView)findViewById(R.id.addStart);
        alarmTimePicker.setVisibility(view.VISIBLE);
        pickerOn.setVisibility(view.VISIBLE);
        addStart.setVisibility(view.VISIBLE);
    }

    public void clickSwitch(View view){
        wifiSwitch();
    }

    public void wifiSwitch(){
        mSwipeRefreshLayout.setRefreshing(true);
        WifiCtrl.switchWifiStatus(this);
        refresher();
    }

    public void getWifiState(){
        TextView wifiStatus = (TextView)findViewById(R.id.wifiStatus);
        String state = WifiCtrl.WifiState(this);
        wifiStatus.setText(state);
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        refresher();
    }

    public void refresher(){
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                getWifiState();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    /*Alarma*/
    public void onStartAlarm(View view) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
        calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
        calendar.set(Calendar.SECOND, 0);

        Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, 0);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);

        Toast toast = Toast.makeText(this,"Programado!",Toast.LENGTH_LONG);
        toast.show();

        TextView pickerOn = (TextView)findViewById(R.id.literalStartAt);
        TextView addStart = (TextView)findViewById(R.id.addStart);
        alarmTimePicker.setVisibility(view.INVISIBLE);
        pickerOn.setVisibility(view.INVISIBLE);
        addStart.setVisibility(view.INVISIBLE);

        TextView switchTime = (TextView)findViewById(R.id.switchTime);
        String minutes="";
        if(alarmTimePicker.getCurrentMinute()<10){
            minutes = "0"+alarmTimePicker.getCurrentMinute().toString();
        }else{
            minutes = alarmTimePicker.getCurrentMinute().toString();
        }
        switchTime.setText(alarmTimePicker.getCurrentHour()+":"+minutes);

    }

}
