package com.example.projecttwo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class SeasonListFragment extends Fragment {
    private Cursor cursor;
    private SQLiteDatabase db;

    private Button migrateButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_season_list, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();

        SQLiteOpenHelper VacationSpotsDatabaseHelper = new VacationSpotsDatabaseHelper(this.getActivity());
        try {
            db = VacationSpotsDatabaseHelper.getReadableDatabase();
            cursor = db.rawQuery("SELECT  * FROM SEASON", null);
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this.getActivity(), R.string.database_unavailable, Toast.LENGTH_SHORT);
            toast.show();
        }
        ListView listView = getView().findViewById(R.id.seasonList);
        SeasonAdapter listAdapter = new SeasonAdapter(getActivity(), cursor, 0);
        listView.setAdapter(listAdapter);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listDrinks, View itemView, int position, long id) {
                View fragmentContainer = getActivity().findViewById(R.id.fragment_container);
                if (fragmentContainer != null) {
                    DestinationListFragment details = new DestinationListFragment();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction(); //start transaction
                    details.setSeason( (int) id);
                    ft.replace(R.id.fragment_container, details);    //replace the fragment
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE); //set an animation for transition
                    ft.addToBackStack(null); //Add this transaction to the back stack.
                    ft.commit();    //commit this transaction
                } else {
                    Intent intent = new Intent(getActivity(), DestinationListActivity.class);
                    intent.putExtra("id_chosen", (int) id);
                    startActivity(intent);
                }
            }
        };
        listView.setOnItemClickListener(itemClickListener);

        migrateButton = getView().findViewById(R.id.myButton);
        migrateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FavoriteSeasonsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        cursor.close();
        db.close();
    }
}