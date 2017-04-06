package com.example.jcobb.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by jcobb on 4/5/17.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ConnectivityStateCallback extends ConnectivityManager.NetworkCallback {

    private static final String TAG = "ConnectivityStateCallback";

    private static IConnectivityListener listener;
    private final ConnectivityManager mConnectivityManager;
    private final NetworkRequest mRequest;


    public ConnectivityStateCallback(Context context) {

        mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        mRequest = new NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build();

        registerCallback();

    }

    @Override
    public void onAvailable(Network network) {
        if (listener != null) {
            listener.onAvailable();
        }
    }

    @Override
    public void onLost(Network network) {
        if (listener != null) {
            listener.onLost();
        }
    }

    public void setListener(IConnectivityListener l) {
        listener = l;
    }

    public void registerCallback() {
        mConnectivityManager.registerNetworkCallback(mRequest, this);
    }

    public void unregisterCallback() {
        mConnectivityManager.unregisterNetworkCallback(this);
    }


}
