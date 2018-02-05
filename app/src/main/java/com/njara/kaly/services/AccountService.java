package com.njara.kaly.services;



import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Arrays;
import java.util.List;

/**
 * Created by njara on 25/01/2018.
 */
public class AccountService implements IAccountService {


    @Override
    public void addSession() {

    }

    @Override
    public void UpdateUserProfile(FirebaseUser firebaseUser,String name, String Photo) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName("Jane Q. User")
                .build();

        firebaseUser.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("Rep", "User profile updated.");
                        }
                    }
                });
    }



}
