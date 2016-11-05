package com.si.tihe.tiheapp.recycler.items;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.si.tihe.tiheapp.R;
import com.si.tihe.tiheapp.recycler.RecyclerViewItemHolderCreator;
import com.si.tihe.tiheapp.recycler.base.BaseRecyclerViewHolder;

/**
 * Created on 5.11.2016.
 *
 * Navigation item holder
 *
 * @author Mišel Mojzeš
 */
public class NavigationItemHolder extends BaseRecyclerViewHolder {

    public static class Creator implements RecyclerViewItemHolderCreator<NavigationItemHolder> {

        @Override
        public NavigationItemHolder create(ViewGroup parent) {
            return new NavigationItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_item, parent, false));
        }
    }

    public ImageView imageView;
    public TextView titleText;
    public TextView descriptionText;

    public NavigationItemHolder(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.navigation_imageview);
        titleText = (TextView) itemView.findViewById(R.id.navigationItemText);
        descriptionText = (TextView) itemView.findViewById(R.id.navigationItemDescription);
    }
}
