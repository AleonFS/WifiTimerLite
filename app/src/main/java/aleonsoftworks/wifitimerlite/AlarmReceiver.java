package aleonsoftworks.wifitimerlite;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.TextView;

/**
 * Created by Alejandro Leon on 22/05/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        //Intent intent = new Intent(context, AlarmService.class.getName());
        context.startService(intent);
        setResultCode(Activity.RESULT_OK);
        WifiCtrl.switchWifiStatus(context);

        Activity vista = MainActivity.instance();

        TextView switchTime = (TextView)vista.findViewById(R.id.switchTime);
        CharSequence text = switchTime.getText();
        switchTime.setText(text+" LISTO!");

        updater(vista,context);
    }

    public void updater(final Activity activity, final Context context){

        new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    TextView wifiStatus = (TextView)activity.findViewById(R.id.wifiStatus);
                    String state = WifiCtrl.WifiState(context);
                    wifiStatus.setText(state);
                }
            }, 2000);
    }
}
