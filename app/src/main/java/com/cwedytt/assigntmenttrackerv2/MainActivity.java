package com.cwedytt.assigntmenttrackerv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewAssignments;
    FloatingActionButton btnAdd;
    ArrayList<String> assignment_id, course, course_code, due_date, due_time, is_complete, completed_date, completed_time;


    DBAdapter dbAdapter;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewAssignments = findViewById(R.id.recyclerViewAssignments);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        dbAdapter = new DBAdapter(MainActivity.this);
        assignment_id = new ArrayList<>();
        course = new ArrayList<>();
        course_code = new ArrayList<>();
        due_date = new ArrayList<>();
        due_time = new ArrayList<>();
        is_complete = new ArrayList<>();
        completed_date = new ArrayList<>();
        completed_time = new ArrayList<>();

        getDataFromArrays();

        adapter = new MyAdapter(
                MainActivity.this,
                assignment_id,
                course,
                course_code,
                due_date,
                due_time,
                is_complete,
                completed_date,
                completed_time);

        recyclerViewAssignments.setAdapter(adapter);
        recyclerViewAssignments.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    void getDataFromArrays(){
        Cursor cursor = dbAdapter.getAllAssignments();
        if(cursor.getCount() == 0)
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        else{
            while(cursor.moveToNext()){
                assignment_id.add(cursor.getString(0));
                course.add(cursor.getString(1));
                course_code.add(cursor.getString(2));
                due_date.add(cursor.getString(3));
                due_time.add(cursor.getString(4));
                is_complete.add(cursor.getString(5));
                completed_date.add(cursor.getString(6));
                completed_time.add(cursor.getString(7));

            }

        }
    }
}