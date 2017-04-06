/*
 * Copyright (C) 2017 The FullStackDev Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.example.jcobb.myapplication;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.ConnectivityManager.NetworkCallback;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;


/**
 * Created by JohnC (John Cobb) on 4/4/17.
 */


public class ConnectivityListener implements IConnectivityListener {

    private static final String TAG = "ConnectivityListener";

    private static IConnectivityListener listener;
    private ConnectivityStateBroadcastReceiver cnx_receiver;
    private ConnectivityStateCallback cnx_callback;

    public ConnectivityListener(Context context) {
        super();

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.d(TAG, "LOLLIPOP");
            cnx_callback = new ConnectivityStateCallback(context);
            cnx_callback.setListener(this);
        } else {
            Log.d(TAG, "40 PROOF()");
            cnx_receiver = new ConnectivityStateBroadcastReceiver();
            cnx_receiver.setListener(this);
        }
    }

    public static boolean isConnected() {

        ConnectivityManager
                cm = (ConnectivityManager) MyApplication.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }

    @Override
    public void onAvailable() {
        if (listener != null) {
            listener.onAvailable();
        }
    }

    @Override
    public void onLost() {
        if (listener != null) {
            listener.onLost();
        }
    }

    public void setListener(IConnectivityListener l) {
        listener = l;
    }

    public void registerListener() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cnx_callback.registerCallback();
        } else {
            cnx_receiver.registerReceiver();
        }
    }

    public void unregisterListener() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cnx_callback.unregisterCallback();
        } else {
            cnx_receiver.unregisterReceiver();
        }
    }





}

//public class ConnectivityListener implements IConnectivityListener{
//
//    public static IConnectivityListener listener;
//    ConnectivityStateBroadcastReceiver cnx_receiver;
//    ConnectivityStateCallback cnx_callback;
//
//    public ConnectivityListener() {
//        super();
//
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            cnx_callback = new ConnectivityStateCallback();
//            cnx_callback.setListener(this);
//        }
//
//        else {
//            cnx_receiver = new ConnectivityStateBroadcastReceiver();
//            cnx_receiver.setListener(this);
//        }
//
//    }
//
//    public static boolean isConnected() {
//
//        ConnectivityManager
//                cm = (ConnectivityManager) MyApplication.getInstance().getApplicationContext()
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        return activeNetwork != null
//                && activeNetwork.isConnectedOrConnecting();
//    }
//
//    @Override
//    public void onAvailable() {
//        if (listener != null) {
//            listener.onAvailable();
//        }
//    }
//
//    @Override
//    public void onLost() {
//        if (listener != null) {
//            listener.onLost();
//        }
//    }
//
//}


//public class ConnectivityListener extends BroadcastReceiver {
//
//    public static ConnectivityReceiverListener connectivityReceiverListener;
//
//    public ConnectivityListener() {
//        super();
//    }
//
//    @Override
//    public void onReceive(Context context, Intent arg1) {
//        ConnectivityManager cm = (ConnectivityManager) context
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        boolean isConnected = activeNetwork != null
//                && activeNetwork.isConnectedOrConnecting();
//
//        if (connectivityReceiverListener != null) {
//            connectivityReceiverListener.onNetworkConnectionChanged(isConnected);
//        }
//    }
//
//
//    public static boolean isConnected() {
//
//        ConnectivityManager
//                cm = (ConnectivityManager) MyApplication.getInstance().getApplicationContext()
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        return activeNetwork != null
//                && activeNetwork.isConnectedOrConnecting();
//    }
//
//
//    public interface ConnectivityReceiverListener {
//        void onNetworkConnectionChanged(boolean isConnected);
//    }
//
//
//}


//@TargetApi(Build.VERSION_CODES.LOLLIPOP)
//public class ConnectivityStateMonitor extends NetworkCallback {
//
//    final NetworkRequest request;
//
//
//    public ConnectivityStateMonitor() {
//        request = new NetworkRequest.Builder()
//                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
//                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
//                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build();
//    }
//
//    @Override
//    public void onAvailable(Network network) {
//        // TODO:
//    }
//
//    @Override
//    public void onLost(Network network) {
//        // TODO:
//    }
//
//}
