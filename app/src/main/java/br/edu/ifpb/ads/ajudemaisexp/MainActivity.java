package br.edu.ifpb.ads.ajudemaisexp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import br.edu.ifpb.ads.ajudemaisexp.fcm.MyFirebaseInstanceIDService;
import br.edu.ifpb.ads.ajudemaisexp.storage.SharedPrefManager;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    private TextView tvToken;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvToken = (TextView) findViewById(R.id.tv_token);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                tvToken.setText(SharedPrefManager.getInstance(MainActivity.this).getToken());
            }
        };

        if(SharedPrefManager.getInstance(this).getToken() != null){
            tvToken.setText(SharedPrefManager.getInstance(MainActivity.this).getToken());
        }

        registerReceiver(broadcastReceiver, new IntentFilter(MyFirebaseInstanceIDService.TOKEN_BROADCAST));
    }
}
