package com.njara.kaly.views.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by nfidiman on 29/01/2018.
 */

public class RegisterFragment extends Fragment {
    private Activity parentActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        parentActivity =  activity;
    }
}
