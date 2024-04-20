package com.example.projecttwo;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DestinationAdapter extends CursorAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public DestinationAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View v = mLayoutInflater.inflate(R.layout.destination_view, viewGroup, false);
        return v;
    }

    @Override
    public void bindView(View itemView, Context context, Cursor cursor) {
        TextView cityNameTextView = itemView.findViewById(R.id.city_name);
        cityNameTextView.setText(cursor.getString(cursor.getColumnIndexOrThrow("CITY_NAME")));

        TextView countryNameTextView = itemView.findViewById(R.id.country_name);
        countryNameTextView.setText(cursor.getString(cursor.getColumnIndexOrThrow("COUNTRY_NAME")));

        TextView priceTextView = itemView.findViewById(R.id.price_dest);
        priceTextView.setText(cursor.getString(cursor.getColumnIndexOrThrow("PRICE")));

        ImageView imageView = itemView.findViewById(R.id.destination_image);
        imageView.setImageResource(cursor.getInt(cursor.getColumnIndexOrThrow("IMAGE_RESOURCE_ID")));

    }
}
