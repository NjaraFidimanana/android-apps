package com.njara.kaly.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.njara.kaly.R;

/**
 * Created by nfidiman on 05/02/2018.
 */

public class FoodHolder extends RecyclerView.ViewHolder {

    public TextView title , subTitle;
    public ImageView thumbnail , overflow;
    public FoodHolder(View itemView) {
        super(itemView);
        title=(TextView) itemView.findViewById(R.id.food_title);
        subTitle=(TextView) itemView.findViewById(R.id.food_subTitle);
        thumbnail=(ImageView) itemView.findViewById(R.id.food_thumbnail);
        overflow=(ImageView) itemView.findViewById(R.id.food_overflow);
    }
}
