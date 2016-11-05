package com.si.tihe.tiheapp.recycler.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created on 5.11.2016.
 *
 * BaseRecyclerViewHolder
 *
 * @author Mišel Mojzeš
 */
public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {

    protected View root;

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        this.root = itemView;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        root.setEnabled(listener != null);
        root.setOnClickListener(listener);
    }

    public void setOnLongClickListener(View.OnLongClickListener listener) {
        root.setOnLongClickListener(listener);
    }

    public View getRootView() {
        return root;
    }

}
