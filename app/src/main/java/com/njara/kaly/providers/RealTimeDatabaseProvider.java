package com.njara.kaly.providers;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.njara.kaly.models.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nfidiman on 04/02/2018.
 */

public class RealTimeDatabaseProvider  implements IRealTimeDatabaseProvider{

    public FirebaseDatabase database;
    public RealTimeDatabaseProvider(){

        database=FirebaseDatabase.getInstance();
    }
    @Override
    public void Write(String reference , Object object) {

        DatabaseReference dataBaseRef = database.getReference(reference);

        dataBaseRef.setValue(object);

    }

    @Override
    public Object Read(String reference , final Class objectClass) {
        final Object[] result = {null};

       /* DatabaseReference dataBaseRef = database.getReference(reference);
        dataBaseRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("RealTimeDatabase", "Value is 1: " +  dataSnapshot.getValue());

                result[0] = dataSnapshot.getValue(objectClass);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("RealTimeDatabase", "Failed to read value.", error.toException());
            }

        });*/

        return null;
    }
}
