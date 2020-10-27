package com.route.NotesApp;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.route.NotesApp.DataBase.Model.Note;
import com.route.NotesApp.DataBase.MyDataBase;
import com.route.NotesApp.base.BaseActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddNoteActivity extends BaseActivity implements View.OnClickListener {

    protected EditText title;
    protected EditText content;
    protected TextView datetime;
    protected Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_add_note);
        initView();
    }

    String noteTime= " ";
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add) {
            if(valid()){
            String titleS = title.getText().toString();
            String contentS = content.getText().toString();
            Note note =new Note(titleS,contentS,noteTime);
            MyDataBase.getInstance(this)
                    .notesDao()
                    .addNote(note);
            showMessage("Note added successfully","OK",
                    new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            },false);}

        } else if (view.getId() == R.id.datetime) {
            Calendar calendar = Calendar.getInstance();
            TimePickerDialog datePickerDialog =new TimePickerDialog(
                    this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    noteTime= hourOfDay+ " : "+minute;
                    datetime.setText(noteTime);
                }
            },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE)
            ,false);
            datePickerDialog.show();
        }
    }

    private void initView() {
        title = (EditText) findViewById(R.id.title);
        content = (EditText) findViewById(R.id.content);
        datetime = (TextView) findViewById(R.id.datetime);
        datetime.setOnClickListener(AddNoteActivity.this);
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(AddNoteActivity.this);
    }

    private boolean valid(){
        boolean isValid=true;
        String title_input=title.getText().toString().trim();
        if(title_input.isEmpty()){
            title.setError("Feild can not be Empty");
            isValid=false;
        }else{
            title.setError(null);
            isValid=true;
        }
        String content_input=content.getText().toString().trim();
        if(content_input.isEmpty()){
            content.setError("Feild can not be Empty");
            isValid=false;
        }else{
            content.setError(null);
            isValid=true;
        }
        String date_input=datetime.getText().toString().trim();
        if(date_input.isEmpty()){
            datetime.setError("Feild can not be Empty");
            isValid=false;
        }else{
            datetime.setError(null);
            isValid=true;
        }
        return isValid;
    }


}
