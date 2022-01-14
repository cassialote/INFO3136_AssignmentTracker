package com.cwedytt.assigntmenttrackerv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {

    EditText assignmentName, courseName, courseCode;
    DatePickerDialog datePickerDialog;
    Button btnAdd, btnDate, btnTime;
    int isComplete, hour, minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initDatePicker();

        assignmentName = findViewById(R.id.editTextAssignmentEdit);
        courseName = findViewById(R.id.editTextCourseEdit);
        courseCode = findViewById(R.id.editTextCourseCodeEdit);
        btnDate = findViewById(R.id.btnDatePicker_Edit);
        btnTime = findViewById(R.id.btnTimePicker_Edit);
        btnAdd = findViewById(R.id.btnSaveEdit);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBAdapter dbAdapter = new DBAdapter(AddActivity.this);
                dbAdapter.addAssignment(
                        assignmentName.getText().toString().trim(),
                        courseCode.getText().toString().trim(),
                        courseName.getText().toString().trim(),
                        btnDate.getText().toString().trim(),
                        btnTime.getText().toString().trim(),
                        0,
                        "",
                        ""
                );
            }
        });
    }

    private String getTodaysDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month = month + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    //date picker setup
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = makeDateString(dayOfMonth, month, year);
                btnDate.setText(date);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int dayOfMonth, int month, int year) {
        return getMonthFormat(month) + " " + dayOfMonth + " " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return getString(R.string.jan);
        if(month == 2)
            return getString(R.string.feb);
        if(month == 3)
            return getString(R.string.mar);
        if(month == 4)
            return getString(R.string.apr);
        if(month == 5)
            return getString(R.string.may);
        if(month == 6)
            return getString(R.string.jun);
        if(month == 7)
            return getString(R.string.jul);
        if(month == 8)
            return getString(R.string.aug);
        if(month == 9)
            return getString(R.string.sep);
        if(month == 10)
            return getString(R.string.oct);
        if(month == 11)
            return getString(R.string.nov);
        if(month == 12)
            return getString(R.string.dec);

        //default
        return getString(R.string.jan);
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    public void openTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hour = hourOfDay;
                minute = minute;
                btnTime.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };

        int style = AlertDialog.THEME_HOLO_LIGHT;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
}