package com.amit.mvcexample.controllers;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.amit.mvcexample.interfaces.LoadingInterface;
import com.amit.mvcexample.interfaces.UserResult;
import com.amit.mvcexample.models.User;
import com.amit.mvcexample.rest.ApiClient;
import com.amit.mvcexample.rest.ApiInterface;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Created by AMIT JANGID on 20/02/21.
public class UserController
{
    private final UserResult userResult;
    private final LoadingInterface mLoadingInterface;
    private static final String TAG = UserController.class.getSimpleName();
    private static final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

    public UserController(AppCompatActivity appCompatActivity)
    {
        this.userResult = (UserResult) appCompatActivity;
        this.mLoadingInterface = (LoadingInterface) appCompatActivity;
    }

    public void getUserListFromServer(int pageNo)
    {
        try
        {
            mLoadingInterface.showProgress();

            Call<User> userCall = apiService.getUserListApi(pageNo);
            userCall.enqueue(new Callback<User>()
            {
                @Override
                public void onResponse(@NotNull Call<User> call, @NotNull Response<User> userResponse)
                {
                    if (userResponse.body() != null && userResponse.code() == 200)
                    {
                        ArrayList<User> userArrayList = userResponse.body().getData();
                        userResult.onUserResult(userArrayList);
                        mLoadingInterface.hideProgress();
                    }
                }

                @Override
                public void onFailure(@NotNull Call<User> call, @NotNull Throwable t)
                {
                    userResult.onUserResultFailure();
                    mLoadingInterface.hideProgress();

                    Log.e(TAG, "onFailure: exception while getting data from server");
                    t.printStackTrace();
                }
            });
        }
        catch (Exception e)
        {
            userResult.onUserResultFailure();
            mLoadingInterface.hideProgress();

            Log.e(TAG, "UserController: exception while making api call for getting user list");
            e.printStackTrace();
        }
    }

    public void getUserDetails()
    {
        mLoadingInterface.showProgress();

        new Handler(Looper.getMainLooper()).postDelayed(mLoadingInterface::hideProgress, 2000);
    }
}
