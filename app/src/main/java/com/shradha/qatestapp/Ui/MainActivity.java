package com.shradha.qatestapp.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.shradha.qatestapp.Adapter.QuizAdapter;
import com.shradha.qatestapp.Model.QuizResponse;
import com.shradha.qatestapp.Model.Result;
import com.shradha.qatestapp.R;
import com.shradha.qatestapp.WebService.ApiService;
import com.shradha.qatestapp.WebService.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.Query;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {
    private QuizAdapter quizAdapter;
    private final ArrayList<Result> quizList = new ArrayList<>();
    private QuizResponse quizResponse;
    private RecyclerView rvQuiz;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        callQuizWS();
    }

    private void callQuizWS() {
        progressBar.setVisibility(View.VISIBLE);

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        Call<QuizResponse> call = apiService.quiz("123456");
        Log.i("TAG", "QuizUrl:" + call.request().url().toString());
        call.enqueue(new Callback<QuizResponse>() {

            @Override
            public void onResponse(@NonNull Call<QuizResponse> call, @NonNull Response<QuizResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("TAG", "QuizResp" + response);
                     quizResponse = response.body();
                    assert quizResponse != null;

                    Toast.makeText(MainActivity.this, quizResponse.getMsg(), Toast.LENGTH_LONG).show();
                    if (quizResponse.getCode().equalsIgnoreCase("200")) {
                    /*    List<Result> questionsModelList = response.body();
                        for (int i = 0; i < questionsModelList.size(); i++) {
                            String ques = quizList.get(i).getQ();
                            List<String> ans = quizList.get(i).getOptions();
                            quizList.add(ques);
                        }*/
                        quizAdapter=new QuizAdapter(MainActivity.this,quizList);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        rvQuiz.setLayoutManager(mLayoutManager);
                        rvQuiz.setAdapter(quizAdapter);
                        quizAdapter.notifyDataSetChanged();
                    }
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<QuizResponse> call, @NonNull Throwable t) {
                Log.i("@B2C", "PLPremierError: " + t.toString());
                Toast.makeText(MainActivity.this,t.getMessage(), LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });

    }


    private void initUi() {
        rvQuiz = findViewById(R.id.rvQuiz);
        progressBar = findViewById(R.id.progressbar);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        quizList.clear();
    }
}