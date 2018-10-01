package com.example.coba.simple_notepad;

import android.app.ProgressDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coba.simple_notepad.Interfaces.NotepadAPI;
import com.example.coba.simple_notepad.Model.Values;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class newNote extends AppCompatActivity {

    public EditText NoteTitle, NoteBody;
    public FloatingActionButton SaveNoteBtn;
    public ProgressDialog PDialog;
    public String URL = "http://10.0.2.2/retrofit_CRUD/";
    private MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        NoteTitle = findViewById(R.id.NoteTitle);
        NoteBody = findViewById(R.id.NoteBody);
        SaveNoteBtn = findViewById(R.id.SaveNote);
        SaveNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewNotepad();
            }
        });

    }

    public void CreateNewNotepad(){
        PDialog = new ProgressDialog(this);
        PDialog.setCancelable(false);
        PDialog.setTitle("Please Wait");
        PDialog.setMessage("Connecting to database...");
        PDialog.show();

        final String notepad_title = NoteTitle.getText().toString();
        String notepad_body = NoteBody.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NotepadAPI api = retrofit.create(NotepadAPI.class);
        Call<Values> call = api.createNotepad(notepad_title, notepad_body);
        call.enqueue(new Callback<Values>() {

            @Override
            public void onResponse(Call<Values> call, Response<Values> response) {
                String kode = response.body().getKode();
                PDialog.dismiss();
                if (kode.equals(notepad_title)){
                    Log.i("kode", "1");
                    Toast.makeText(newNote.this,"Success",Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Log.i("kode","not 1");
                    Toast.makeText(newNote.this,"Failed",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Values> call, Throwable t) {

            }
        });



    }
}

