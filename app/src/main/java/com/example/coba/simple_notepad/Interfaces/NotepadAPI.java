package com.example.coba.simple_notepad.Interfaces;

import android.content.Intent;

import com.example.coba.simple_notepad.Model.Values;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NotepadAPI {
    @FormUrlEncoded
    @POST("insert.php")
    Call<Values> createNotepad(
            @Field("user_id") String user_id,
            @Field("notepad_title") String notepad_title,
            @Field("notepad_body") String notepad_body
    );

//    @GET("index.php")
//    Call<Values> index();

    @FormUrlEncoded
    @POST("index.php")
    Call<Values> index(
            @Field("user_id") String user_id
    );


    @FormUrlEncoded
    @POST("update.php")
    Call<Values> updateNotepad(
        @Field("notepad_id") String notepad_id,
        @Field("notepad_title") String notepad_title,
        @Field("notepad_body") String notepad_body
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<Values> delete(
            @Field("notepad_id") String notepad_id
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<Values> login(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<Values> register(
            @Field("username") String username,
            @Field("password") String password
    );

}
