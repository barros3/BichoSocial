package com.barros.shark74.bichosocial.connection;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Switch;

import com.barros.shark74.bichosocial.activity.LoginActivity;
import com.barros.shark74.bichosocial.util.Constants;

/**
 * Created by shark74 on 25/04/16.
 */
public class SessionManager {

    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "AndroidHiveLogin";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);

        // commit changes
        editor.commit();

//        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    double token;

    public String checkStatusWithUidToken(String checkASwitchManterConectado, Switch aSwitchManterConectado){
        if(aSwitchManterConectado.isChecked()){
//            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences();
//                        SharedPreferences.Editor editor = sh.edit();

        }

        return "";
    }

}
