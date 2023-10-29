package com.example.projecttwo;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SeasonAdapter extends CursorAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public SeasonAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        View v = mLayoutInflater.inflate(R.layout.season_view, viewGroup, false);
        return v;
    }

    @Override
    public void bindView(View itemView, Context context, Cursor cursor) {

        TextView seasonNameTextView = itemView.findViewById(R.id.season_name);
        seasonNameTextView.setText(cursor.getString(cursor.getColumnIndexOrThrow("NAME")));

        String sumDest = cursor.getString(cursor.getColumnIndexOrThrow("NUM_OF_DESTINATIONS"))+" " + context.getString(R.string.destinations);
        TextView numDestTextView = itemView.findViewById(R.id.num_dest);
        numDestTextView.setText(sumDest);

        ImageView imageView = itemView.findViewById(R.id.season_image);
        imageView.setImageResource(cursor.getInt(cursor.getColumnIndexOrThrow("IMAGE_RESOURCE_ID")));
    }
}
