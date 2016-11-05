package com.si.tihe.tiheapp.recycler.renderer;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.si.tihe.tiheapp.recycler.base.BaseRecyclerViewAdapter;
import com.si.tihe.tiheapp.recycler.items.NavigationItem;

/**
 * Created on 5.11.2016.
 *
 * Navigation renderer
 *
 * @author Mišel Mojzeš
 */
public class NavigationRenderer {

    private BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter();

    public NavigationRenderer(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);

    }

    public void addNavigationItem(NavigationItem navigationItem) {
        adapter.add(navigationItem);
    }

    public void clear() {
        adapter.clear();
    }

}
