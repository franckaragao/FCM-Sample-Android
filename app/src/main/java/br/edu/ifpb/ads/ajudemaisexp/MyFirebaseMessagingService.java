package br.edu.ifpb.ads.ajudemaisexp;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Franck Aragão on 2/9/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService{

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d("Ajude Mais!", "From: " + remoteMessage.getFrom());
        Log.d("Ajude Mais!", "Notification Message Body: " + remoteMessage.getNotification().getBody());
    }
}
