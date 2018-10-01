package com.example.coba.simple_notepad;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.coba.simple_notepad.Adapter.RecycleViewAdapter;
import com.example.coba.simple_notepad.Interfaces.NotepadAPI;
import com.example.coba.simple_notepad.Model.Notepad;
import com.example.coba.simple_notepad.Model.Values;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton NewNoteBtn;
    private List<Notepad> notepads = new ArrayList<>();
    private RecycleViewAdapter viewAdapter;
    public String URL = "http://10.0.2.2/retrofit_CRUD/";
    public MainActivity ma;


    @BindView(R.id.recyclerView) RecyclerView recycleView;
    @BindView(R.id.progressBar) ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        NewNoteBtn = findViewById(R.id.NewNote);
        NewNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NewNotePage = new Intent(MainActivity.this,newNote.class);
                startActivity(NewNotePage);
            }
        });

        viewAdapter = new RecycleViewAdapter(MainActivity.this, notepads);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycleView.setLayoutManager(mLayoutManager);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.setAdapter(viewAdapter);

        loadNotepad();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ONRESUME","RESUMED");
        loadNotepad();
    }

    private void loadNotepad(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        NotepadAPI api = retrofit.create(NotepadAPI.class);

        Call<Values> call = api.index();
        call.enqueue(new Callback<Values>() {
            @Override
            public void onResponse(Call<Values> call, Response<Values> response) {
                String kode = response.body().getKode();
                Log.i("KODE GET", kode);
                progressBar.setVisibility(View.GONE);
                if (kode.equals("1")){
                    notepads = response.body().getNotepad();
                    if (notepads == null) Log.i("notepads", "NULL");
                    viewAdapter = new RecycleViewAdapter(MainActivity.this, notepads);
                    recycleView.setAdapter(viewAdapter);
                }
            }

            @Override
            public void onFailure(Call<Values> call, Throwable t) {

            }
        });
    }

}
