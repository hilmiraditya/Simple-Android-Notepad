package com.example.coba.simple_notepad;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coba.simple_notepad.Interfaces.NotepadAPI;
import com.example.coba.simple_notepad.Model.Values;
import com.example.coba.simple_notepad.Session.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class login extends AppCompatActivity {

    EditText Username, Password;
    Button LoginBtn;
    SessionManager sessionManager;
    public String URL = "http://10.0.2.2/retrofit_CRUD/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        LoginBtn = findViewById(R.id.LoginBtn);

        sessionManager = new SessionManager(login.this);

//        if(sessionManager.isLoggedIn()){
//            Intent MainPage = new Intent(login.this, MainActivity.class);
//            startActivity(MainPage);
//            finish();
//        }

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    public void login(){

        final String usernameValue = Username.getText().toString();
        String passwordValue = Password.getText().toString();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        NotepadAPI api = retrofit.create(NotepadAPI.class);
        Call<Values> call = api.login(usernameValue, passwordValue);
        call.enqueue(new Callback<Values>() {
            @Override
            public void onResponse(Call<Values> call, Response<Values> response) {
                String kode = response.body().getKode();
                if (kode.toString() != ""){
                    Log.i("KODE LOGIN", kode);
                    sessionManager.setLogin(true);
                    sessionManager.setterUserId(kode);
                    Intent MainPage = new Intent(login.this, MainActivity.class);
                    startActivity(MainPage);
                    Toast.makeText(login.this,"Ter Log In Bung!",Toast.LENGTH_LONG).show();
                    finish();

                }else{
                    Log.i("KODE LOGIN", "gagal cuook");
                }
            }

            @Override
            public void onFailure(Call<Values> call, Throwable t) {

            }
        });
    }
}
