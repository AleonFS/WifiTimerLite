package aleonsoftworks.wifitimerlite.config;

import android.support.v4.widget.SwipeRefreshLayout;

import aleonsoftworks.wifitimerlite.R;

/**
 * Created by Alejandro Leon on 19/05/2017.
 * Ejemplo para ver cómo crear archivos de configuración en OOP
 */

public class SWLConfig {


    public int primaryColor = R.color.colorPrimary;
    public int secondaryColor = R.color.colorAccent;
    public int size = SwipeRefreshLayout.DEFAULT;

    public Object setConf(int primaryColor,int secondaryColor,int size){
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.size = size;
        return this;
    }

}
