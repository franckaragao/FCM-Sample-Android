package br.edu.ifpb.ads.ajudemaisexp;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Franck Aragão on 2/9/17.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    public static final String TOKEN_BROADCAST = "fcmToken";

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("Ajude Mais!", "Refreshed token: " + refreshedToken);

        getApplicationContext().sendBroadcast(new Intent(TOKEN_BROADCAST));
        storageToken(refreshedToken);
    }

    private void storageToken(String token) {
        SharedPrefManager.getInstance(getApplicationContext()).storeToken(token);
    }
}
