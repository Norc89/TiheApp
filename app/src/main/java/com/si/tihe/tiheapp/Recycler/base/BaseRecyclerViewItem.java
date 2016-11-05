package com.si.tihe.tiheapp.recycler.base;

import android.support.v7.widget.RecyclerView;

import com.si.tihe.tiheapp.recycler.RecyclerViewItemHolderCreator;

/**
 * Created on 5.11.2016.
 *
 * BaseRecyclerViewItem
 *
 * @author Mišel Mojzeš
 */
public abstract class BaseRecyclerViewItem {

    // The viewType this view is associated with
    private int viewType;
    private String viewHolderId;

    public BaseRecyclerViewItem() {
        // We need an unique ID for this item to avoid having to write view types every time
        // Class name seems to work perfectly
        viewHolderId = getClass().getName();
    }

    /**
     * Return view creator for id returned from getViewHolderId().
     */
    public abstract RecyclerViewItemHolderCreator<? extends RecyclerView.ViewHolder> getViewHolderCreator();

    // Called when this view is bound to it's view holder. It can use it's viewholder to manipulate the view the view holder has inflated:
    public abstract void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position);

    public final void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public final int getViewType() {
        return viewType;
    }

    /**
     * Used for matching view with item. If item has multiple views override this method.
     */
    public String getViewHolderId() {
        return viewHolderId;
    }

}

