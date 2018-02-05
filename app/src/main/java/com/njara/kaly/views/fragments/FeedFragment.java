package com.njara.kaly.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.njara.kaly.R;
import com.njara.kaly.listeners.AuthenticationListener;
import com.njara.kaly.models.Food;
import com.njara.kaly.services.AccountService;
import com.njara.kaly.services.FeedService;
import com.njara.kaly.services.IFeedService;
import com.njara.kaly.views.FeedItemDecoration;
import com.njara.kaly.views.adapters.FoodAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nfidiman on 04/02/2018.
 */

public class FeedFragment extends Fragment {

    private IFeedService feedService;
    private RecyclerView recyclerView;
    private List<Food> foodList;
    private FoodAdapter foodAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.feedService =new FeedService(getActivity().getApplicationContext());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        this.recyclerView=(RecyclerView) view.findViewById(R.id.feed_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new FeedItemDecoration(getActivity(),2, 10, true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        this.feedService.GetFeeds(recyclerView);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


    }
}
