package com.example.projecttwo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class FavoriteSeasonsActivity extends AppCompatActivity {
    private ListView favoriteSeasonsLV;
    private Cursor cursor;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_favorite_seasons);
        MaterialToolbar toolbar = findViewById(R.id.burger);
        MaterialToolbar write = findViewById(R.id.note);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        MaterialToolbar Sharetoolbar = findViewById(R.id.share);
        Sharetoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Implicitintent = new Intent(Intent.ACTION_SEND);
                Implicitintent.setType("text/plain");


                String messageText ="Find Your Vacation Place App! Download Now";
                Implicitintent.putExtra(Intent.EXTRA_TEXT, messageText);
                startActivity(Implicitintent);

            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(GravityCompat.START);

            }
        });
        write.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FavoriteSeasonsActivity.this, WriteNoteActivity.class);
                startActivity(intent);



            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {

                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id)
                {

                    case R.id.nav_home:
                        Intent intent = new Intent(FavoriteSeasonsActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_share2:
                        Intent Implicitintent = new Intent(Intent.ACTION_SEND);
                        Implicitintent.setType("text/plain");


                        String messageText ="Find Your Vacation Place App! Download Now";
                        Implicitintent.putExtra(Intent.EXTRA_TEXT, messageText);
                        startActivity(Implicitintent);

                        break;
                    case R.id.nav_list:
                        Intent Listintent = new Intent(FavoriteSeasonsActivity.this, ViewNotesActivity.class);
                        startActivity(Listintent);

                    default:
                        return true;

                }
                return true;
            }
        });

        favoriteSeasonsLV = findViewById(R.id.favorite_seasons);
        SQLiteOpenHelper VacationSpotsDatabaseHelper = new VacationSpotsDatabaseHelper(this);

        try {
            db = VacationSpotsDatabaseHelper.getReadableDatabase();
            cursor = db.rawQuery("SELECT  * FROM SEASON WHERE IS_FAVORITE = 1", null);
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, R.string.database_unavailable, Toast.LENGTH_SHORT);
            toast.show();
        }
        favoriteSeasonsAdapter listAdapter = new favoriteSeasonsAdapter(this, cursor, 0);
        favoriteSeasonsLV.setAdapter(listAdapter);

    }

    private class favoriteSeasonsAdapter extends CursorAdapter {

        private LayoutInflater mLayoutInflater;
        private Context mContext;

        public favoriteSeasonsAdapter(Context context, Cursor c, int flags) {
            super(context, c, flags);
            mContext = context;
            mLayoutInflater = LayoutInflater.from(context);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

            View v = mLayoutInflater.inflate(R.layout.card, viewGroup, false);
            return v;
        }

        @Override
        public void bindView(View itemView, Context context, Cursor cursor) {
            TextView seasonNameTextView = itemView.findViewById(R.id.name_of_season);
            seasonNameTextView.setText(cursor.getString(cursor.getColumnIndexOrThrow("NAME")));

            ImageView imageView = itemView.findViewById(R.id.season_svg);
            imageView.setImageResource(cursor.getInt(cursor.getColumnIndexOrThrow("SEASON_SVG")));

            ImageView backgroundImage = itemView.findViewById(R.id.background_image);
            backgroundImage.setBackgroundColor(getResources().getColor(cursor.getInt(cursor.getColumnIndexOrThrow("COLOR"))));
        }
    }

}