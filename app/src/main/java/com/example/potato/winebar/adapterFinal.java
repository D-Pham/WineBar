package com.example.potato.winebar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.potato.winebar.R;
import com.example.potato.winebar.wines;

import java.util.List;

/**
 * Created by Julien on 11/5/2016.
 */

public class adapterFinal extends BaseAdapter {
    Context context;
    List<finalReview> rowItems;

    adapterFinal(Context context, List<finalReview> rowItems) {
        this.context = context;
        this.rowItems = rowItems;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }

    /* private view holder class */
    private class ViewHolder {
        TextView review;
        TextView rating;
        TextView notes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.final_list_row_thing, null);
            holder = new ViewHolder();

            holder.review = (TextView) convertView
                    .findViewById(R.id.asd);
            holder.rating = (TextView) convertView
                    .findViewById(R.id.Rating);
            holder.notes = (TextView) convertView.findViewById(R.id.Notes);


            finalReview row_pos = rowItems.get(position);
            System.out.println(row_pos);
            System.out.println(row_pos.review);
            holder.review.setText(row_pos.review);
            holder.rating.setText("Rating:  " + row_pos.rating);
            holder.notes.setText(row_pos.notes);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }
}
