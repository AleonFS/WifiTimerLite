package aleonsoftworks.wifitimerlite;

/**
 * Created by Alejandro Leon on 19/05/2017.
 */

import android.content.Context;
import android.net.wifi.WifiManager;
import android.widget.Toast;

public class WifiCtrl {

    public static boolean STATE;

    public static WifiManager setManager (Context context){
        WifiManager manager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        return manager;
    }

    public static String WifiState(Context context) {
        WifiManager manager ;//= (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        manager = setManager(context);
        int wifiState = manager.getWifiState();
        if (wifiState==3) {
            STATE=true;
            Toast toast = Toast.makeText(context,"ON!",Toast.LENGTH_LONG);
            toast.show();
            return "ON";
        }
        STATE=false;
        Toast toast = Toast.makeText(context,"Off!",Toast.LENGTH_LONG);
        toast.show();
        return "OFF";
    }

    public static void switchWifiStatus (Context context){
        boolean newState = !STATE;
        WifiManager manager ;//= (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        manager = setManager(context);

        manager.setWifiEnabled(newState);
    }
}
