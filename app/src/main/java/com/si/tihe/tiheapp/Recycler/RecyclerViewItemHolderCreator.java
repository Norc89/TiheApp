package com.si.tihe.tiheapp.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created on 5.11.2016.
 *
 * RecyclerViewItemHolderCreator
 *
 * @author Mišel Mojzeš
 */
public interface RecyclerViewItemHolderCreator<T extends RecyclerView.ViewHolder> {
    /**
     * This is a call to the view holder to inflate it's view and return it.
     *
     * @param parent parent of the view in which the view holder should inflate it's view.
     * @return inflated view holder object
     */
    T create(ViewGroup parent);
}
