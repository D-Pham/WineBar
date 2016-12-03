package com.example.potato.winebar;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Julien on 11/5/2016.
 */

public class adapterReviews extends BaseAdapter {
    Context context;
    List<review> rowItems;

    adapterReviews(Context context, List<review> rowItems) {
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
        ImageView pic;
        TextView name;
        TextView description;
        TextView date;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_review_item, null);
            holder = new ViewHolder();

            holder.pic = (ImageView) convertView
                    .findViewById(R.id.pic);
            holder.name = (TextView) convertView
                    .findViewById(R.id.name);
            holder.description = (TextView) convertView.findViewById(R.id.description);
            holder.date = (TextView) convertView.findViewById(R.id.date);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        review row_pos = rowItems.get(position);
        int id = context.getResources().getIdentifier(row_pos.getPic(), "drawable", context.getPackageName());
        System.out.println(id);
        holder.pic.setImageResource(id);
        holder.name.setText(row_pos.name);
        holder.description.setText(row_pos.wine);
        String newstring = new SimpleDateFormat("yyyy-MM-dd").format((new Date(row_pos.date)));
        holder.date.setText(newstring);

        return convertView;
    }
}
