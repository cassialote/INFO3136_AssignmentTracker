package com.cwedytt.assigntmenttrackerv2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList assignment_id, course, course_code, due_date, due_time, is_complete, completed_date, completed_time;;

    MyAdapter(Context context,
              ArrayList assignment_id,
              ArrayList course,
              ArrayList course_code,
              ArrayList due_date,
              ArrayList due_time,
              ArrayList is_complete,
              ArrayList completed_date,
              ArrayList completed_time) {

        this.context = context;
        this.assignment_id = assignment_id;
        this.course = course;
        this.course_code = course_code;
        this.due_date = due_date;
        this.due_time = due_time;
        this.is_complete = is_complete;
        this.completed_date = completed_date;
        this.completed_time = completed_time;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.assignment_id_text.setText(String.valueOf(assignment_id.get(position)));
        holder.course_text.setText(String.valueOf(course.get(position)));
        holder.course_code_text.setText(String.valueOf(course_code.get(position)));
        holder.due_date_text.setText(String.valueOf(due_date.get(position)));
        holder.due_time_text.setText(String.valueOf(due_time.get(position)));
        holder.is_complete_text.setText(String.valueOf(is_complete.get(position)));
        holder.completed_date_text.setText(String.valueOf(completed_date.get(position)));
        holder.completed_time_text.setText(String.valueOf(completed_time.get(position)));
        //Recyclerview onClickListener
//        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, UpdateActivity.class);
//                intent.putExtra("id", String.valueOf(assignment_id.get(position)));
//                intent.putExtra("course", String.valueOf(course.get(position)));
//                intent.putExtra("course_code", String.valueOf(course_code.get(position)));
//                intent.putExtra("due_date", String.valueOf(due_date.get(position)));
//                intent.putExtra("due_time", String.valueOf(due_time.get(position)));
//                activity.startActivityForResult(intent, 1);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return assignment_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView assignment_id_text, course_text, course_code_text, due_date_text, due_time_text, is_complete_text, completed_date_text, completed_time_text;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            assignment_id_text = itemView.findViewById(R.id.editTextAssignmentEdit);
            course_text = itemView.findViewById(R.id.editTextCourseEdit);
            course_code_text = itemView.findViewById(R.id.editTextCourseCodeEdit);
            due_date_text = itemView.findViewById(R.id.btnDatePicker_Edit);
            due_time_text = itemView.findViewById(R.id.btnTimePicker_Edit);
//            is_complete_text = itemView.findViewById(R.id.editTextAssignmentEdit);
//            completed_date_text = itemView.findViewById(R.id.editTextAssignmentEdit);
//            completed_time_text = itemView.findViewById(R.id.editTextAssignmentEdit);

        }
    }
}
