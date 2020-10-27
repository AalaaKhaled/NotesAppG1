package com.route.NotesApp;


import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.route.NotesApp.DataBase.Model.Note;
import com.route.NotesApp.DataBase.MyDataBase;
import com.route.NotesApp.base.BaseActivity;

import java.util.Calendar;


public class UpdateActivity extends BaseActivity implements View.OnClickListener {
  EditText title;
  EditText content;
  TextView date;
  String title_I;
  String content_I;
  String date_I;
  int id_I;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initi();
        title_I=getIntent().getStringExtra("title_I");
        content_I=getIntent().getStringExtra("content_I");
        date_I=getIntent().getStringExtra("date_I");
        id_I=getIntent().getIntExtra("id_I",-1);
        title.setText(title_I);
        content.setText(content_I);
        date.setText(date_I);

    }
    public void initi(){
        title=findViewById(R.id.title);
        content=findViewById(R.id.content);
        date=findViewById(R.id.datetime);
        date.setOnClickListener(UpdateActivity.this);
    }

    String noteTime= " ";
    @Override
    public void onClick(View view) {
    if (view.getId() == R.id.datetime) {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog datePickerDialog =new TimePickerDialog(
                this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                noteTime= hourOfDay+ " : "+minute;
                date.setText(noteTime);
            }
        },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE)
                ,false);
        datePickerDialog.show();
      }
    }

    public void updateNotes(View view) {
        String titleS = title.getText().toString();
        String contentS = content.getText().toString();
        String dateS=date.getText().toString();
        Note note =MyDataBase.getInstance(this).notesDao().searchNoteByID(id_I);
        note.setTitle(titleS);
        note.setContent(contentS);
        note.setDateTime(dateS);
        MyDataBase.getInstance(this).notesDao().updateNote(note);

        showMessage(R.string.Updated,R.string.ok);
        new Handler()
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent= new Intent(UpdateActivity.this,HomeActivity.class);
                        startActivity(intent);
                    }
                },2000);

    }


}
