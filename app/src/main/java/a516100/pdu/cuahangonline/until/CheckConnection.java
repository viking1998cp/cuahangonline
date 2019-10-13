package a516100.pdu.cuahangonline.until;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class CheckConnection {
    public static boolean checkConnect(Context context){
        boolean haveConnectWfi = false;
        boolean haveConnectMobile = false;
        ConnectivityManager cm =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = cm.getAllNetworkInfo();
        for(NetworkInfo ni: networkInfos){
            if(ni.getTypeName().equalsIgnoreCase("WIFI")){
                if(ni.isConnected()){
                    haveConnectWfi = true;
                }
            }
            if(ni.getTypeName().equalsIgnoreCase("MOBILE")){
                if(ni.isConnected()){
                    haveConnectMobile = true;
                }
            }
        }
        return haveConnectMobile || haveConnectWfi;
    }
    public static void show_Toast_short(String thongbao, Context context){
        Toast.makeText(context,thongbao,Toast.LENGTH_SHORT).show();
    }

}
