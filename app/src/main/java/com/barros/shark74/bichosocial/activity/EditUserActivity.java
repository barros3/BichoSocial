package com.barros.shark74.bichosocial.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.barros.shark74.bichosocial.R;
import com.barros.shark74.bichosocial.connection.AppConfig;
import com.barros.shark74.bichosocial.connection.AppController;
import com.barros.shark74.bichosocial.connection.SQLiteHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditUserActivity extends AppCompatActivity {

    ImageView imageView;

    ProgressDialog pDialog;
    SQLiteHandler db;
    String name;
    String email;
    String created_at;
    String telefone;
    EditText editNome;
    EditText editEmail;
    EditText editTelefone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        imageView = (ImageView) findViewById(R.id.userPhoto);

        editNome = (EditText) findViewById(R.id.userName);
        editNome.setText(name);
        editEmail = (EditText) findViewById(R.id.userEmail);
        editEmail.setText(email);
        editTelefone = (EditText)  findViewById(R.id.userTelefone);
        editTelefone.setText(telefone);
    }



}
