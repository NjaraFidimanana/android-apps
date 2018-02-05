package com.njara.kaly.services;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by njara on 25/01/2018.
 */
public interface IAccountService {

    public void addSession();

    public void UpdateUserProfile(FirebaseUser firebaseUser, String name , String Photo);
}