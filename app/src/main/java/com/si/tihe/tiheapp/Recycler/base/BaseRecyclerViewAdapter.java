package com.si.tihe.tiheapp.recycler.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.si.tihe.tiheapp.recycler.RecyclerViewItemHolderCreator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created on 5.11.2016.
 *
 * BaseRecyclerViewAdapter
 *
 * @author Mišel Mojzeš
 */
public class BaseRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected List<BaseRecyclerViewItem> items = new ArrayList<>();
    private SparseArray<RecyclerViewItemHolderCreator<?>> viewTypesCreators = new SparseArray<>();

    private SparseArray<Footer> footersViewTypes = new SparseArray<>();
    private List<Footer> footersList = new ArrayList<>();

    private int viewTypeCounter = 0;

    // viewHolderId, adapterViewType
    private HashMap<String, Integer> viewTypes = new HashMap<>();

    /**
     * Adds or replaces a footer with the given id.
     *
     * @param footerId - id of the footer
     * @param item     - item associated with this footer
     */

    public void addOrReplaceFooter(int footerId, BaseRecyclerViewItem item) {
        removeFooter(footerId);

        // try to reuse already created view (after clear() is called)
        int viewType = -1;
        for (int i = 0; i < footersViewTypes.size(); i++) {
            Footer footer = footersViewTypes.get(footersViewTypes.keyAt(i));
            if (footer.id == footerId) {
                viewType = footer.viewType;
                break;
            }
        }

        if (viewType == -1) {
            viewType = generateViewType();
        }

        Footer footer = new Footer(footerId, viewType, item.getViewHolderCreator(), item);
        footersViewTypes.put(footer.viewType, footer);
        footersList.add(footer);
    }

    /**
     * Removes a footer with a specific id.
     *
     * @param footerId - id of the footer to remove
     * @return true if the footer is found and removed, false if not found.
     */

    public boolean removeFooter(int footerId) {
        Footer exFooter = findFooterById(footerId);
        if (exFooter != null) {
            footersList.remove(exFooter);
            return true;
        }

        return false;
    }

    /**
     * Searches for footer with a specific id within the list of footers.
     *
     * @param id - id to look for
     * @return - Footer if it's found, otherwise null
     */

    private Footer findFooterById(int id) {
        for (Footer f : footersList) {
            if (f.id == id) {
                return f;
            }
        }
        return null;
    }

    private boolean isFooterItem(int position) {
        int size = footersList.size();
        return size > 0 && position >= getItemCount() - size;
    }

    private int getFooterIndex(int position) {
        return position - getAddedItemsCount();
    }

    /**
     * Adds a recycler item.
     *
     * @param item - item to add
     */

    public void add(BaseRecyclerViewItem item) {
        item.setViewType(createViewType(item));
        items.add(item);
    }

    /**
     * Adds a view to the adapter.
     *
     * @param position - position where to add this view
     * @param item     - item to add
     * @param animate  - animated addition of this item
     */

    public void add(int position, BaseRecyclerViewItem item, boolean animate) {
        int itemCount = getItemCount();
        if (position >= itemCount) {
            position = Math.max(0, itemCount - 1);
        }

        item.setViewType(createViewType(item));
        items.add(position, item);

        if (animate) {
            notifyItemInserted(position);
        } else {
            notifyDataSetChanged();
        }
    }

    /**
     * Remove item without calling notifyDataSetChanged. Changes are not seen until notifyDataSetChanged is called!
     */
    public void removeOnly(int index) {
        if (index >= 0 && index < getAddedItemsCount()) {
            items.remove(index);
            onItemRemoved(index);
        }
    }

    /**
     * Removes item at index.
     *
     * @param index - index of the item to remove
     */

    public void remove(int index) {
        remove(index, true);
    }

    /**
     * Removes and animated the removal of a view
     *
     * @param index   - index of the item to remove
     * @param animate - if set to true, the removal will be animated.
     */

    public void remove(int index, boolean animate) {
        if (index >= 0 && index < getAddedItemsCount()) {
            items.remove(index);
            onItemRemoved(index);
            if (animate) {
                notifyItemRemoved(index);
            } else {
                notifyDataSetChanged();
            }
        }
    }


    /**
     * Moves item
     *
     * @param fromPosition from position
     * @param toPosition   to position
     * @param animate      use animation
     */
    public void moveItem(int fromPosition, int toPosition, boolean animate) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(items, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(items, i, i - 1);
            }
        }
        if (animate) {
            notifyItemMoved(fromPosition, toPosition);
        } else {
            notifyDataSetChanged();
        }
    }

    /**
     * Notify subclass that an item with this index has been removed.
     * It is up to the subclass if it will implement this or not..
     *
     * @param index - where the item has been removed.
     */

    protected void onItemRemoved(int index) {
    }

    /**
     * Returns recycler item at position
     *
     * @param position - position
     * @return - item at position
     */
    public BaseRecyclerViewItem getItem(int position) {
        if (isFooterItem(position)) {
            return footersList.get(getFooterIndex(position)).item;
        }
        return items.get(position);
    }

    /**
     * Returns the index of the item
     *
     * @param item - item
     * @return - index of item
     */
    public int getItemPosition(BaseRecyclerViewItem item) {
        int i = 0;
        for (BaseRecyclerViewItem it : items) {
            if (it == item) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public void clear() {
        // views stays in recycler view - need to keep view creators and view types to avoid conflicts
        items.clear();
    }

    public int getAddedItemsCount() {
        return items.size();
    }

    /**
     * Use {@link #getAddedItemsCount() getAddedItemsCount()} for items count only.
     *
     * @return items.size() + footer if added
     */
    @Override
    public int getItemCount() {
        return items.size() + footersList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isFooterItem(position)) {
            return footersList.get(getFooterIndex(position)).viewType;
        }
        return items.get(position).getViewType();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isFooterItem(position)) {
            footersList.get(getFooterIndex(position)).item.onBindViewHolder(holder, position);
        } else {
            items.get(position).onBindViewHolder(holder, position);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Footer footer = footersViewTypes.get(viewType);
        if (footer != null) {
            return footer.creator.create(parent);
        }

        return viewTypesCreators.get(viewType).create(parent);
    }

    private int createViewType(BaseRecyclerViewItem item) {
        String holderId = item.getViewHolderId();
        Integer adapterType = viewTypes.get(holderId);

        if (adapterType == null) {
            adapterType = generateViewType();
            viewTypes.put(holderId, adapterType);
            viewTypesCreators.put(adapterType, item.getViewHolderCreator());
        }

        return adapterType;
    }

    private int generateViewType() {
        viewTypeCounter++;
        if (viewTypeCounter >= Integer.MAX_VALUE) {
            viewTypeCounter = 0;
        }

        return viewTypeCounter;
    }

    private static class Footer {
        private int id;
        private int viewType;
        private RecyclerViewItemHolderCreator<?> creator;
        private BaseRecyclerViewItem item;

        public Footer(int id, int viewType, RecyclerViewItemHolderCreator<?> creator, BaseRecyclerViewItem item) {
            this.id = id;
            this.viewType = viewType;
            this.creator = creator;
            this.item = item;
        }
    }

}
