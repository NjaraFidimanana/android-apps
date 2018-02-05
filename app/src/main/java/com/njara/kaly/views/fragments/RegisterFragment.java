package com.njara.kaly.views.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.njara.kaly.R;
import com.njara.kaly.listeners.AuthenticationListener;
import com.njara.kaly.services.AccountService;
import com.njara.kaly.services.IAccountService;

/**
 * Created by nfidiman on 29/01/2018.
 */

public class RegisterFragment extends Fragment {
    private Activity parentActivity;
    private AuthenticationListener textViewListener;
    private IAccountService accountService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*  fireBaseAuth.createUserWithEmailAndPassword(email, password)
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
                });*/
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        parentActivity =  activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        textViewListener=new AuthenticationListener(getActivity(),getActivity().getSupportFragmentManager());
        TextView textViewSignIn = (TextView) view.findViewById(R.id.link_signin);
        textViewSignIn.setOnClickListener(textViewListener);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        this.accountService=new AccountService();

    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        parentActivity =  (Activity)activity;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}
