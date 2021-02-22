package com.amit.mvcexample.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

// Created by AMIT JANGID on 20/02/21.
public class User
{
    @SerializedName("data")
    ArrayList<User> data;

    @SerializedName("id")
    int id;

    @SerializedName("email")
    String email;

    @SerializedName("")
    String avatar;

    @SerializedName("last_name")
    String lastName;

    @SerializedName("first_name")
    String firstName;

    public ArrayList<User> getData()
    {
        return data;
    }

    public void setData(ArrayList<User> data)
    {
        this.data = data;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }
}
