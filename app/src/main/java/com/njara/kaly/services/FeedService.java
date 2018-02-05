package com.njara.kaly.services;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.njara.kaly.models.Food;
import com.njara.kaly.providers.RealTimeDatabaseProvider;
import com.njara.kaly.views.adapters.FoodAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nfidiman on 04/02/2018.
 */

public class FeedService  extends  RealTimeDatabaseProvider implements IFeedService {

    private RealTimeDatabaseProvider realTimeDatabaseProvider;;
    private List<Food> foodList;
    private Context context;
    public FeedService(){

        this.realTimeDatabaseProvider=new RealTimeDatabaseProvider();
    }

    public FeedService(Context context){

        this.realTimeDatabaseProvider=new RealTimeDatabaseProvider();
        this.context=context;
    }
    @Override
    public List<Food> GetFeeds(final RecyclerView recyclerView ) {
        foodList = new ArrayList<>();

        DatabaseReference dataBaseRef = this.database.getReference("foods");
        dataBaseRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("RealTimeDatabase", "Value is 1: " +  dataSnapshot.getValue());

                Food food= dataSnapshot.getValue(Food.class);
                food.locationName="Food Court";
                foodList.add(food);

                FoodAdapter foodAdapter = new FoodAdapter(context, foodList);
                recyclerView.setAdapter(foodAdapter);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("RealTimeDatabase", "Failed to read value.", error.toException());
            }

        });
        return null;
    }
}
