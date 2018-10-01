package com.example.coba.simple_notepad;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.coba.simple_notepad.Interfaces.NotepadAPI;
import com.example.coba.simple_notepad.Model.Values;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class editNote extends AppCompatActivity {

    private EditText TitleForm, BodyForm;
    private String IDForm;
    private FloatingActionButton UpdateBtn, DeleteBtn;
    private ProgressDialog progressDialog;
    public String URL = "http://10.0.2.2/retrofit_CRUD/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        IDForm = getIntent().getStringExtra("notepad_id");

        TitleForm = findViewById(R.id.EditNoteTitle);
        TitleForm.setText(getIntent().getStringExtra("notepad_title"));
        BodyForm = findViewById(R.id.EditNoteBody);
        BodyForm.setText(getIntent().getStringExtra("notepad_body"));
        UpdateBtn = findViewById(R.id.UpdateNote);
        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateNotepad();
            }
        });

        DeleteBtn = findViewById(R.id.DeleteButton);
        DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteNotepad();
            }
        });

    }


    public void DeleteNotepad(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        NotepadAPI api = retrofit.create(NotepadAPI.class);
        Call<Values> call = api.delete(IDForm);
        call.enqueue(new Callback<Values>() {
            @Override
            public void onResponse(Call<Values> call, Response<Values> response) {
                String deleteKode = response.body().getKode();
                if (deleteKode.equals("1")){
                    progressDialog.dismiss();
                    Toast.makeText(editNote.this, "Deleted", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(editNote.this, "Delete Failed", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Values> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(editNote.this, "Server Error", Toast.LENGTH_LONG).show();
                finish();
            }
        });


    }

    public void UpdateNotepad(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        String updatedTitle = TitleForm.getText().toString();
        String updatedBody = BodyForm.getText().toString();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        NotepadAPI api = retrofit.create(NotepadAPI.class);
        Call<Values> call = api.updateNotepad(IDForm, updatedTitle, updatedBody);
        call.enqueue(new Callback<Values>() {

            @Override
            public void onResponse(Call<Values> call, Response<Values> response) {
                String updatekode = response.body().getKode();
                Log.i("update kode", updatekode);
                progressDialog.dismiss();
                if (updatekode.equals("1")){
                    Toast.makeText(editNote.this, "Updated", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(editNote.this, "Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Values> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(editNote.this, "Server Error", Toast.LENGTH_LONG).show();
                finish();
            }
        });


    }
}
