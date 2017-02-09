package br.edu.ifpb.ads.ajudemaisexp;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Franck Aragão on 2/9/17.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("Ajude Mais!", "Refreshed token: " + refreshedToken);

        //sendRegistrationToServer(refreshedToken);
    }
}
