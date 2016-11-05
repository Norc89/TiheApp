package com.si.tihe.tiheapp.recycler.items;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.si.tihe.tiheapp.recycler.RecyclerViewItemHolderCreator;
import com.si.tihe.tiheapp.recycler.base.BaseRecyclerViewItem;

/**
 * Created on 5.11.2016.
 *
 * Navigation item
 *
 * @author Mišel Mojzeš
 */
public class NavigationItem extends BaseRecyclerViewItem implements View.OnClickListener {


    public interface OnNavigationItemClick {
        void onNavigationItemClick(Class<?> targetActivity);
    }

    private OnNavigationItemClick navigationItemClickListener;
    private Class<?> target;
    private String titleText;
    private String descriptionText;
    private int imageRes;

    public NavigationItem(Class<?> target, String titleText, String descriptionText, int imageRes, OnNavigationItemClick callback) {
        this.target = target;
        this.titleText = titleText;
        this.descriptionText = descriptionText;
        this.imageRes = imageRes;
        this.navigationItemClickListener = callback;
    }

    @Override
    public RecyclerViewItemHolderCreator<? extends RecyclerView.ViewHolder> getViewHolderCreator() {
        return new NavigationItemHolder.Creator();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        NavigationItemHolder navigationItemHolder = (NavigationItemHolder) viewHolder;

        navigationItemHolder.titleText.setText(titleText);
        navigationItemHolder.descriptionText.setText(descriptionText);
        navigationItemHolder.imageView.setImageResource(imageRes);
        navigationItemHolder.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (navigationItemClickListener != null) {
            navigationItemClickListener.onNavigationItemClick(target);
        }
    }
}

