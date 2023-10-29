package com.example.projecttwo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "ViewNotesActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                Intent intent = new Intent(MainActivity.this, WriteNoteActivity.class);
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
//                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                        startActivity(intent);
                        break;
                    case R.id.nav_share2:
                        Intent Implicitintent = new Intent(Intent.ACTION_SEND);
                        Implicitintent.setType("text/plain");


                        String messageText ="Find Your Vacation Place App! Download Now";
                        Implicitintent.putExtra(Intent.EXTRA_TEXT, messageText);
                        startActivity(Implicitintent);

                        break;
                    case R.id.nav_list:
                        Intent Listintent = new Intent(MainActivity.this, ViewNotesActivity.class);
                        startActivity(Listintent);

                    default:
                        return true;

                }
                return true;
            }
        });
    }

}