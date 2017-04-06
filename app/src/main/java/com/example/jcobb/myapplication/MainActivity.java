package com.example.jcobb.myapplication;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements IConnectivityListener {

    private static final String TAG = "MainActivity";

    boolean mConnected = false;

    Button buttonCheckConnection;
    TextView textViewStatus;
    ConnectivityListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Tell it to use activity_main as the layout
        setContentView(R.layout.activity_main);

        buttonCheckConnection = (Button) findViewById(R.id.buttonCheckConnection);
        textViewStatus = (TextView) findViewById(R.id.textViewStatus);

        buttonCheckConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkConnection();
            }
        });

        listener = new ConnectivityListener(this.getApplicationContext());
        listener.setListener(this);
        listener.registerListener();

        mConnected = ConnectivityListener.isConnected();

        Log.d(TAG, "onCreate()");

    }

    public void updateUi() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textViewStatus.setText("connected (" + mConnected + ")");
            }
        });
    }

    private void checkConnection() {
        mConnected = ConnectivityListener.isConnected();
        updateUi();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
        listener.registerListener();
        updateUi();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
        listener.unregisterListener();
    }

    @Override
    public void onAvailable() {
        Log.d(TAG, "connected (True)");
        mConnected = true;
        updateUi();
    }

    @Override
    public void onLost() {
        Log.d(TAG, "connected (False)");
        mConnected = false;
        updateUi();
    }
}
