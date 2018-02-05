package com.njara.kaly.listeners;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.njara.kaly.MainActivity;
import com.njara.kaly.R;
import com.njara.kaly.views.fragments.LoginFragment;
import com.njara.kaly.views.fragments.RegisterFragment;

/**
 * Created by nfidiman on 31/01/2018.
 */

public class AuthenticationListener implements View.OnClickListener {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    public int frameId=R.id.content_main_frame;
    private Activity activity;
    public AuthenticationListener(Activity activity , FragmentManager fragmentManager){
        this.activity=activity;
        this.fragmentManager=fragmentManager;
    }

    @Override
    public void onClick(View view) {

        String title="";
        switch ( view.getId()){

            case R.id.link_signup:
                fragmentTransaction = this.fragmentManager.beginTransaction();

                title=this.activity.getResources().getString(R.string.ac_register);
                fragmentTransaction.replace(R.id.content_main_frame, new RegisterFragment(),title).commit();
                break;

            case R.id.link_signin:
                fragmentTransaction = this.fragmentManager.beginTransaction();
                title = this.activity.getResources().getString(R.string.ac_login);
                fragmentTransaction.replace(R.id.content_main_frame, new LoginFragment(),title).commit();
                break;

            case R.id.btn_login:
                boolean status =true;
                if(status){

                    Intent in = new Intent(this.activity,MainActivity.class);
                    this.activity.startActivity(in);
                    this.activity.finish();

                }
                break;

            case R.id.btn_header_login:

                fragmentTransaction = this.fragmentManager.beginTransaction();
                title = this.activity.getResources().getString(R.string.ac_login);
                fragmentTransaction.replace(R.id.content_main_frame, new LoginFragment(),title).commit();

                DrawerLayout drawer = (DrawerLayout) this.activity.findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                break;


            default:
                break;

        }

    }
}
