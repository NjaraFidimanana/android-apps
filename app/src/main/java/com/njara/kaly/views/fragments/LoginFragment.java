package com.njara.kaly.views.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.njara.kaly.R;

/**
 * Created by njara on 25/01/2018.
 */
public class LoginFragment extends Fragment {

    private FirebaseAuth fireBaseAuth;
    private Activity parentActivity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fireBaseAuth = FirebaseAuth.getInstance();

        String email="njr801@yahoo.com";
        String password="Testing123";

        fireBaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this.parentActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Test", "createUserWithEmail:success");
                            FirebaseUser user = fireBaseAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                           // Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                            //        Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_login, container, false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = fireBaseAuth.getCurrentUser();
        updateUI(currentUser);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        parentActivity =  activity;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void updateUI(FirebaseUser currentUser){

    }

}
