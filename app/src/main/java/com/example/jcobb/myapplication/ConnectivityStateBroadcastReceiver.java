package com.example.jcobb.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by jcobb on 4/5/17.
 */

public class ConnectivityStateBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "ConnectivityStateBroadcastReceiver";

    private static IConnectivityListener listener;
    private ConnectivityManager mConnectivityManager;
    private Context mContext;

    public ConnectivityStateBroadcastReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent arg1) {

        mContext = context;
        mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = mConnectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();


        if (listener != null) {
            if(isConnected)
                listener.onAvailable();
            else
                listener.onLost();
        }

    }

    public void setListener(IConnectivityListener l) {
        listener = l;
    }

    public void registerReceiver() {
        // TODO: Empty Function
    }

    public void unregisterReceiver() {
        mContext.unregisterReceiver(this);
    }
}