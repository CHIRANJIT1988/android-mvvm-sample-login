package com.mvvm.login.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.mvvm.login.R;
import com.mvvm.login.databinding.ActivityProfileBinding;
import com.mvvm.login.model.User;
import com.mvvm.login.viewmodel.UserViewModel;

public class ProfileActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        User user = (User) getIntent().getSerializableExtra("USER_OBJ");

        ActivityProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        binding.setUserViewModel(new UserViewModel(this, user));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return  true;
        }

        return super.onOptionsItemSelected(item);
    }
}