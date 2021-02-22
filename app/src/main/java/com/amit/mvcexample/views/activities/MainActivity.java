package com.amit.mvcexample.views.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amit.mvcexample.R;
import com.amit.mvcexample.controllers.UserController;
import com.amit.mvcexample.interfaces.LoadingInterface;
import com.amit.mvcexample.interfaces.UserListClickListener;
import com.amit.mvcexample.interfaces.UserResult;
import com.amit.mvcexample.models.User;
import com.amit.mvcexample.views.adapters.UserListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements UserResult, LoadingInterface, UserListClickListener
{
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView rvUsersList;
    private UserController userController;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // calling init activity
        initActivity();
    }

    private void initActivity()
    {
        try
        {
            rvUsersList = findViewById(R.id.rvUsersList);
            userController = new UserController(MainActivity.this);
            userController.getUserListFromServer(1);
        }
        catch (Exception e)
        {
            Log.e(TAG, "initActivity: exception while initializing activity");
        }
    }

    @Override
    public void onUserResult(ArrayList<User> userArrayList)
    {
        if (userArrayList != null && userArrayList.size() > 0)
        {
            UserListAdapter userListAdapter = new UserListAdapter(MainActivity.this, userArrayList);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
            rvUsersList.setLayoutManager(linearLayoutManager);
            rvUsersList.setAdapter(userListAdapter);
            rvUsersList.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onUserResultFailure()
    {
        rvUsersList.setVisibility(View.GONE);
    }

    @Override
    public void showProgress()
    {
        progressDialog = ProgressDialog.show(MainActivity.this, null, getResources().getString(R.string.app_name), false, false);
    }

    @Override
    public void hideProgress()
    {
        if (progressDialog != null)
        {
            progressDialog.hide();
        }
    }

    @Override
    public void onUserClicked(User user)
    {
        userController.getUserDetails();
    }
}
