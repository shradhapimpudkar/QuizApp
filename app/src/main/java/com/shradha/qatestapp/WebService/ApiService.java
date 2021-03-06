package com.shradha.qatestapp.WebService;


import com.shradha.qatestapp.Model.QuizResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.Query;

//Api Interface class for sub part of URL maintatined here
public interface ApiService {


     @POST(ApiConstant.QUIZ_URL)
     Call<QuizResponse> quiz(@Query("key") String key);

   }
