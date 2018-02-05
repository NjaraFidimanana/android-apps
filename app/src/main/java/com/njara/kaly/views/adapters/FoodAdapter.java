package com.njara.kaly.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.njara.kaly.R;
import com.njara.kaly.models.Food;
import com.njara.kaly.views.holders.FoodHolder;

import java.util.List;

/**
 * Created by nfidiman on 05/02/2018.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodHolder> {

    private Context mContext;
    private List<Food> foodList;

    @Override
    public FoodHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_card, parent, false);

        return new FoodHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FoodHolder holder, int position) {

        Food food = this.foodList.get(position);
        holder.title.setText(food.name);
        holder.subTitle.setText(food.locationName);
    }

    @Override
    public int getItemCount() {
        return this.foodList.size();
    }

    public FoodAdapter(Context mContext, List<Food> foodList) {
        this.mContext = mContext;
        this.foodList = foodList;
    }
}
