package com.example.potato.winebar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Potato on 11/18/2016.
 */

public class ReviewItemAdapter extends BaseAdapter {
    private final List<ReviewItem> mItems = new ArrayList<ReviewItem>();
    private final Context mContext;

    public ReviewItemAdapter(Context context) {

        mContext = context;

    }

    public void add(ReviewItem item) {
        mItems.add(item);
        notifyDataSetChanged();
    }

    // Clears the list adapter of all items.

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    // Returns the number of ToDoItems

    @Override
    public int getCount() {
        return mItems.size();
    }

    // Retrieve the number of ToDoItems

    @Override
    public Object getItem(int pos) {
        return mItems.get(pos);
    }

    // Get the ID for the ToDoItem
    // In this case it's just the position

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    // Create a View for the ToDoItem at specified position
    // Remember to check whether convertView holds an already allocated View
    // before created a new View.
    // Consider using the ViewHolder pattern to make scrolling more efficient
    // See: http://developer.android.com/training/improving-layouts/smooth-scrolling.html

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // TODO - Get the current ToDoItem
        final ReviewItem badgeItem = (ReviewItem) getItem(position);


        // TODO - Inflate the View for this ToDoItem
        // from todo_item.xml

        LinearLayout itemLayout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.reviewitem,null);

        // Return the View you just created
        return itemLayout;

    }

}
