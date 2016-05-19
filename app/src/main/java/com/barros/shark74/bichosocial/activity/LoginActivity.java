package com.barros.shark74.bichosocial.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.barros.shark74.bichosocial.R;
import com.barros.shark74.bichosocial.connection.AppConfig;
import com.barros.shark74.bichosocial.connection.AppController;
import com.barros.shark74.bichosocial.connection.SQLiteHandler;
import com.barros.shark74.bichosocial.connection.SessionManager;
import com.barros.shark74.bichosocial.util.Constants;
import com.barros.shark74.bichosocial.util.RegisterActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button btnLogin;
    private Button btnLinkToRegister;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    private SessionManager session;
    SQLiteHandler db;
    private Switch aSwitch;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.userEmail);
        inputPassword = (EditText) findViewById(R.id.userPassword);
        btnLogin = (Button) findViewById(R.id.acessar);
        btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        aSwitch = (Switch) findViewById(R.id.manterConectado);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        db = new SQLiteHandler(getApplicationContext());

        session = new SessionManager(getApplicationContext());

        if (session.isLoggedIn()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

//    @Override
//    public void onBackPressed() {
//        if(aSwitch.isChecked()){
//            session.isLoggedIn();
//        }session.setLogin(false);
//    }

    public void validaLogin(View view) {
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        if (!email.isEmpty() && !password.isEmpty()) {
            checkLogin(email, password);
        } else {
            Toast.makeText(getApplicationContext(), "Preencha os campos!", Toast.LENGTH_LONG)
                    .show();
        }

    }

    public void regitreNovoUsuario(View view) {
        Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(i);
        finish();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void checkLogin(final String email, final String password) {
        String tag_string_req = "req_login";

        pDialog.setMessage("Autenticando ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if (!error) {

                        session.setLogin(true);

                        String uid = jObj.getString("uid");

                        JSONObject user = jObj.getJSONObject("user");

                        String name = user.getString("name");
                        String email = user.getString("email");
                        String created_at = user.getString("created_at");

                        db.addUser(name, email, uid, created_at);

                        session.checkStatusWithUidToken(uid, aSwitch);

//                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
//                        SharedPreferences.Editor editor = sh.edit();
//                        double token = Math.random();
//                        if(aSwitch.isChecked()){
//                            editor.putString(Constants.STATUS_OK, "Token : " + token );
//                            editor.commit();
//                        }


                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        },   new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Login Error: " + error.getMessage());
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    hideDialog();
                }
            })  {

                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("email", email);
                        params.put("password", password);

                        return params;
                    }

                };

        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

}