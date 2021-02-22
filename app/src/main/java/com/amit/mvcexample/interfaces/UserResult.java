package com.amit.mvcexample.interfaces;

import com.amit.mvcexample.models.User;

import java.util.ArrayList;

// Created by AMIT JANGID on 20/02/21.
public interface UserResult
{
    void onUserResult(ArrayList<User> userArrayList);

    void onUserResultFailure();
}
