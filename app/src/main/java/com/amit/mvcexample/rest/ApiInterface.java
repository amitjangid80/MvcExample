package com.amit.mvcexample.rest;

import com.amit.mvcexample.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

// Created by AMIT JANGID on 20/02/21.
public interface ApiInterface
{
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @GET("users")
    Call<User> getUserListApi(@Query("page") int pageNo);
}
