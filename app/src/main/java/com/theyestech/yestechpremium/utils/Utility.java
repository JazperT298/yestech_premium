package com.theyestech.yestechpremium.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.fragment.app.FragmentManager;


public class Utility {


//    public static boolean isNfcSupported(Context context)
//    {
//        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(context);
//        return (nfcAdapter != null);
//    }

    public static boolean haveNetworkConnection(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo;

        if (connectivityManager != null) networkInfo = connectivityManager.getActiveNetworkInfo();
        else networkInfo = null;

        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

//    public static boolean dateAndTimeDetection(Context context, FragmentManager fragmentManager){
//
//
//        if (Settings.Global.getInt(context.getContentResolver(), Settings.Global.AUTO_TIME, 0) == 0
//                && Settings.Global.getInt(context.getContentResolver(), Settings.Global.AUTO_TIME_ZONE, 0) == 0)
//        {
//            openSettingsDialog( context.getString(R.string.error_automatic_date_time), "Date and time is off", fragmentManager);
//            return false;
//        }
//        else if (Settings.Global.getInt(context.getContentResolver(), Settings.Global.AUTO_TIME, 0) == 0)
//        {
//            openSettingsDialog( context.getString(R.string.make_sure_to_turn_on_auto_date), "Auto date & time is off", fragmentManager);
//            return false;
//        }
//        else if (Settings.Global.getInt(context.getContentResolver(), Settings.Global.AUTO_TIME_ZONE, 0) == 0)
//        {
//            openSettingsDialog( context.getString(R.string.make_sure_to_turn_on_time_zone), "Time zone is off", fragmentManager);
//            return false;
//        } else {
//            return true;
//        }
//    }

    private static void openSettingsDialog(String message, String title, FragmentManager fragmentManager){
//
//        Bundle bundle = new Bundle();
//        bundle.putString("MESSAGE", message);
//        bundle.putString("TITLE", title);
//
//        OpenSettingsDialog customAlertDialog = new OpenSettingsDialog();
//        customAlertDialog.setArguments(bundle);
//        customAlertDialog.show(fragmentManager,"frag");
    }

}
