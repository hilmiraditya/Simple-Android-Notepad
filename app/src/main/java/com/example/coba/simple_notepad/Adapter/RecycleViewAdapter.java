package com.example.coba.simple_notepad.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.coba.simple_notepad.MainActivity;
import com.example.coba.simple_notepad.Model.Notepad;
import com.example.coba.simple_notepad.R;
import com.example.coba.simple_notepad.editNote;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private Context context;
    private List<Notepad> notepads;

    public RecycleViewAdapter(Context context, List<Notepad> notepads) {
        this.context = context;
        this.notepads = notepads;
    }

    @NonNull
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.ViewHolder holder, int position) {
        Notepad notepad = notepads.get(position);
        holder.TitleField.setText(notepad.getNotepad_title());
        holder.BodyField.setText(notepad.getNotepad_body());
        holder.IDField.setText(notepad.getNotepad_id());

    }


    @Override
    public int getItemCount() {
        return notepads.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.notepad_id) TextView IDField;
        @BindView(R.id.notepad_title) TextView TitleField;
        @BindView(R.id.notepad_body) TextView BodyField;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String notepad_id = IDField.getText().toString();
                    String notepad_title = TitleField.getText().toString();
                    String notepad_body = BodyField.getText().toString();

                    Log.i("CARDS", "TERPECNET");
                    Intent gotoEditPage = new Intent(context, editNote.class);
                    gotoEditPage.putExtra("notepad_id", notepad_id);
                    gotoEditPage.putExtra("notepad_title", notepad_title);
                    gotoEditPage.putExtra("notepad_body", notepad_body);
                    context.startActivity(gotoEditPage);
                }
            });
        }

    }


}
