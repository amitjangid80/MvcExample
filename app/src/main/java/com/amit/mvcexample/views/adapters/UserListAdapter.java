package com.amit.mvcexample.views.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.amit.mvcexample.R;
import com.amit.mvcexample.interfaces.UserListClickListener;
import com.amit.mvcexample.models.User;

import java.util.ArrayList;

// Created by AMIT JANGID on 20/02/21.
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListViewHolder>
{
    private static final String TAG = UserListAdapter.class.getSimpleName();

    private final ArrayList<User> mUserArrayList;
    private final UserListClickListener mUserListClickListener;

    public UserListAdapter(AppCompatActivity appCompatActivity, ArrayList<User> userArrayList)
    {
        this.mUserArrayList = userArrayList;
        this.mUserListClickListener = (UserListClickListener) appCompatActivity;
    }

    @NonNull
    @Override
    public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_list, parent, false);
        return new UserListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListViewHolder holder, int position)
    {
        try
        {
            User user = mUserArrayList.get(position);

            holder.tvLastName.setText(user.getLastName());
            holder.tvFirstName.setText(user.getFirstName());

            holder.itemView.setOnClickListener(view -> mUserListClickListener.onUserClicked(user));
        }
        catch (Exception e)
        {
            Log.e(TAG, "onBindViewHolder: exception while binding data to UI");
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount()
    {
        return mUserArrayList != null ? mUserArrayList.size() : 0;
    }

    static class UserListViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView tvFirstName, tvLastName;

        public UserListViewHolder(@NonNull View itemView)
        {
            super(itemView);

            tvLastName = itemView.findViewById(R.id.tvLastName);
            tvFirstName = itemView.findViewById(R.id.tvFirstName);
        }
    }
}
