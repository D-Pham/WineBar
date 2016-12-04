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

public class adapterCart extends BaseAdapter {
    Context context;
    List<cartObject> rowItems;

    adapterCart(Context context, List<cartObject> rowItems) {
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
        TextView quantity;
        TextView totalPrice;
        TextView test;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        adapterCart.ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_cart_item, null);
            holder = new adapterCart.ViewHolder();

            holder.pic = (ImageView) convertView
                    .findViewById(R.id.pic);
            holder.name = (TextView) convertView
                    .findViewById(R.id.name);
            holder.quantity = (TextView) convertView.findViewById(R.id.quantity);
            holder.totalPrice = (TextView) convertView.findViewById(R.id.total_price);

            System.out.println(position);
          //  cartObject row_pos = rowItems.get(position);
          //  int id = context.getResources().getIdentifier(row_pos.getPic(), "drawable", context.getPackageName());
           // System.out.println(id);

            convertView.setTag(holder);
        } else {
            holder = (adapterCart.ViewHolder) convertView.getTag();
        }
        cartObject row_pos = rowItems.get(position);
        int id = context.getResources().getIdentifier(row_pos.getPic(), "drawable", context.getPackageName());
        System.out.println(id);

        holder.pic.setImageResource(id);
        holder.name.setText(row_pos.wineName);
        holder.quantity.setText("Quantity: " + row_pos.quantity);
        // String newstring = new SimpleDateFormat("yyyy-MM-dd").format((new Date(row_pos.date)));

       // Integer price = Integer.parseInt(row_pos.totalPrice);

        holder.totalPrice.setText("Price: $" + row_pos.totalPrice);


        return convertView;
    }
}
