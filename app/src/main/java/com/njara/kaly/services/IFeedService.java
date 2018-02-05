package com.njara.kaly.services;

import android.support.v7.widget.RecyclerView;

import com.njara.kaly.models.Food;

import java.util.List;

/**
 * Created by nfidiman on 04/02/2018.
 */

public interface IFeedService {

    public List<Food> GetFeeds(RecyclerView recyclerView);
}
