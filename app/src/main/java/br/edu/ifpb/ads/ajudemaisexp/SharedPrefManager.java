package br.edu.ifpb.ads.ajudemaisexp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Classe utilitaria para armazenamento em preferências do app.
 *
 * Created by Franck Aragão on 2/9/17.
 */

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "fcmSharedPref";

    private static final String KEY_ACCESS_TOKEN = "fcmToken";

    private static Context context;

    private static SharedPrefManager instance;

    /**
     * construtor padrão
     *
     * @param context
     */
    public SharedPrefManager(Context context){
        this.context = context;
    }

    /**
     * get single instance
     *
     * @param context
     * @return
     */
    public static  synchronized SharedPrefManager getInstance(Context context) {
        if(instance == null)
            instance = new SharedPrefManager(context);
        return instance;

    }

    /**
     * Salva token no arquivo de configuraçeõs do app.
     *
     * @param token
     * @return
     */
    public boolean storeToken(String token) {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ACCESS_TOKEN, token);
        editor.apply();
        return true;
    }

    /**
     * Obtem token
     *
     * @return token salvo nas preferencias do app.
     */
    public String getToken() {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, null);
    }

}
