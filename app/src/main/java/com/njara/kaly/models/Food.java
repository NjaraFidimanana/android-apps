package com.njara.kaly.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by nfidiman on 04/02/2018.
 */

@IgnoreExtraProperties
public class Food {

    public int id ;
    public String name;
    public String locationName;
    public String thumbnailUrl;
    public Food(){

    }
    public Food(int id , String name,String locationName , String thumbnailUrl){
            this.name=name;
        this.locationName=locationName;
        this.thumbnailUrl=thumbnailUrl;
    }
}
