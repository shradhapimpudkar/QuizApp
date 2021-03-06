package com.shradha.qatestapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.shradha.qatestapp.Model.Result;
import com.shradha.qatestapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {
    //Global variable declared
    Context context;
    List<Result> quizList;
    LayoutInflater lf;


    //Constructor applied for LookAdpater with data
    public QuizAdapter(Context context, List<Result> quizList) {
        this.context = context;
        this.quizList = quizList;
        lf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //View created
    @Override
    @NotNull
    public QuizAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_quiz, parent, false);
        return new ViewHolder(view);
    }

    //List count calculation done here
    @Override
    public int getItemCount() {
        return quizList.size();
    }

    //Initialize and access row id from pojo
    @Override
    public void onBindViewHolder(QuizAdapter.ViewHolder holder, final int position) {
        Result result = quizList.get(position);
        holder.txtQuizQues.setText(result.getQ());
        holder.txtQuizAns.setText(result.getOptions().get(position));

        if (position == result.getCorrectIndex()) {
            Toast.makeText(context, position + "" + result.getCorrectIndex(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "m not here buddy", Toast.LENGTH_SHORT).show();
        }
    }

    //View ids declaration
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtQuizQues, txtQuizAns;
        LinearLayout llInsType;

        public ViewHolder(View view) {
            super(view);
            txtQuizQues = view.findViewById(R.id.txtQuizQues);
            txtQuizAns = view.findViewById(R.id.txtQuizAns);
        }
    }
}
