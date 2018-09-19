package com.mvvm.login.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.mvvm.login.BR;
import com.mvvm.login.model.User;
import com.mvvm.login.view.ProfileActivity;


public class UserViewModel extends BaseObservable
{

    private User user;
    private Context context;
    private int busy;


    /**
     * Constructor for UserViewModel
     * @param context
     * @param user
     */
    public UserViewModel(Context context, User user)
    {
        this.user = user;
        this.context = context;
        this.busy = View.GONE;
    }

    /**
     * Bind email filed with xml layout
     * @return
     */
    @Bindable
    public String getEmail()
    {
        return this.user.getEmail();
    }

    public void setEmail(String email)
    {
        user.setEmail(email);
        notifyPropertyChanged(BR.email);
    }

    /**
     * Bind password filed with xml layout
     * @return
     */
    @Bindable
    public String getPassword()
    {
        return this.user.getPassword();
    }

    public void setPassword(String password)
    {
        user.setPassword(password);
        notifyPropertyChanged(BR.password);
    }


    /**
     * Bind progress bar with xml layout
     * @return
     */
    @Bindable
    public int getBusy()
    {
        return this.busy;
    }

    public void setBusy(int busy)
    {
        this.busy = busy;
        notifyPropertyChanged(BR.busy);
    }

    /**
     * Bind welcome message for profile UI
     * @param email
     */
    public void setWelcome(String email)
    {
        notifyPropertyChanged(BR.welcome);
    }

    @Bindable
    public String getWelcome()
    {
        return this.user.getWelcomeMessage();
    }


    /**
     * Event generated for login button
     * @return
     */
    public View.OnClickListener onLoginClick()
    {
        return new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                if(!user.isValidEmail())
                {
                    Toast.makeText(view.getContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                }

                else if(!user.isValidPassword())
                {
                    Toast.makeText(view.getContext(), "Password Should be Minimum 6 Characters", Toast.LENGTH_SHORT).show();
                }

                else if(user.isValidCredential())
                {
                    setBusy(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run()
                        {
                            setBusy(View.GONE);
                            Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();

                            //setEmail(null);
                            //setPassword(null);

                            Intent intent = new Intent(context, ProfileActivity.class);
                            intent.putExtra("USER_OBJ", user);
                            context.startActivity(intent);

                            ((Activity) context).finish();
                        }
                    }, 500);
                }

                else
                {
                    Toast.makeText(context, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
}