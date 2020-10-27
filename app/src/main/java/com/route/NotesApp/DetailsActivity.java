package com.route.NotesApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class DetailsActivity extends AppCompatActivity {
    TextView title;
    TextView content;
    TextView date;
    String title_I;
    String content_I;
    String date_I;
    int id_I;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        init();
        title_I=getIntent().getStringExtra("title_I");
        content_I=getIntent().getStringExtra("content_I");
        date_I=getIntent().getStringExtra("date_I");
        id_I=getIntent().getIntExtra("id_I",-1);
        title.setText(title_I);
       content.setText(content_I);
       date.setText(date_I);


    }
    public void init(){
        title=findViewById(R.id.title_detail);
        content=findViewById(R.id.content_detail);
        date=findViewById(R.id.date);

    }

    public void updateNote(View view) {
        Intent intent=new Intent(DetailsActivity.this,UpdateActivity.class);
        intent.putExtra("title_I",title_I);
        intent.putExtra("content_I",content_I);
        intent.putExtra("date_I",date_I);
        intent.putExtra("id_I",id_I);
        startActivity(intent);
    }
}


