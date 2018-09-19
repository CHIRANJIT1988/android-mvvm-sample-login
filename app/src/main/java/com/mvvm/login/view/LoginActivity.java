package com.mvvm.login.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mvvm.login.R;
import com.mvvm.login.databinding.ActivityLoginBinding;
import com.mvvm.login.model.User;
import com.mvvm.login.viewmodel.UserViewModel;

public class LoginActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        /**
         * Create instance for data binding auto generated class file
         */
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        /**
         * Create instance for ViewModel class
         */
        UserViewModel userViewModel = new UserViewModel(this, new User());

        /**
         * Set ViewModel instance to binding class
         */
        binding.setUserViewModel(userViewModel);
    }
}