package br.edu.ifpb.ads.ajudemaisexp;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Franck Aragão on 2/9/17.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    public static final String TOKEN_BROADCAST = "fcmToken";

    /**
     *
     */
    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        getApplicationContext().sendBroadcast(new Intent(TOKEN_BROADCAST));
        storageToken(refreshedToken);
        sendToken(refreshedToken);

    }

    /**
     *
     * @param token
     */
    private void storageToken(String token) {
        SharedPrefManager.getInstance(getApplicationContext()).storeToken(token);
    }

    /**
     *
     * @param token
     */
    private void sendToken(String token) {
        JSONObject doador = new JSONObject();
        try {
            doador.put("nome", "Franck");
            doador.put("tokenFCM", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        executePost("http://192.168.0.122:8080/doador", doador);

    }

    /**
     *
     * @param targetURL
     * @param jsonParam
     * @return
     */
    private static Boolean executePost(String targetURL, JSONObject jsonParam)
    {
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL(targetURL);
            connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.connect();

            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream ());
            wr.writeBytes(jsonParam.toString());
            wr.flush();
            wr.close ();

            int response = connection.getResponseCode();
            if (response >= 200 && response <=399){
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        } finally {

            if(connection != null) {
                connection.disconnect();
            }
        }
    }
}
