package com.njara.kaly.listeners;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.njara.kaly.R;
import com.njara.kaly.views.fragments.FeedFragment;
import com.njara.kaly.views.fragments.LoginFragment;

/**
 * Created by nfidiman on 04/02/2018.
 */

public class NavigationListener implements NavigationView.OnNavigationItemSelectedListener {

    private Activity activity;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public NavigationListener(Activity activity ,FragmentManager fragmentManager){

        this.activity=activity;
        this.fragmentManager=fragmentManager;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
         String title;
        switch (id){
            case R.id.nav_feed:

                fragmentTransaction = this.fragmentManager.beginTransaction();
                title = this.activity.getResources().getString(R.string.title_feed);
                fragmentTransaction.replace(R.id.content_main_frame, new FeedFragment(),title).commit();

                break;
            default:
                break;
        }
       /* if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
