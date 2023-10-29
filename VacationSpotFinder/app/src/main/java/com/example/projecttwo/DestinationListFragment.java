package com.example.projecttwo;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class DestinationListFragment extends Fragment {
    private Cursor cursor;
    private SQLiteDatabase db;
    int seasonId = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_destination_list, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        SQLiteOpenHelper VacationSpotsDatabaseHelper = new VacationSpotsDatabaseHelper(this.getActivity());
        ListView listView = getView().findViewById(R.id.destList);
        TextView seasonName = getView().findViewById(R.id.seasonName);
        CheckBox visited = getView().findViewById(R.id.favorite);

        visited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new UpdateSeasonTask().execute(seasonId);
            }
        });
        try {
            db = VacationSpotsDatabaseHelper.getReadableDatabase();
            cursor = db.rawQuery("SELECT  * FROM DESTINATION WHERE SEASON_ID = " + seasonId, null);
            DestinationAdapter listAdapter = new DestinationAdapter(getActivity(), cursor, 0);
            listView.setAdapter(listAdapter);

            cursor = db.rawQuery("SELECT * FROM SEASON WHERE _id = " + seasonId, null);
            if (cursor.moveToFirst()) {
                seasonName.setText(cursor.getString(cursor.getColumnIndexOrThrow("NAME")));
                boolean isFavorite = (cursor.getInt(cursor.getColumnIndexOrThrow("IS_FAVORITE")) == 1);
                visited.setChecked(isFavorite);
            }

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this.getActivity(), R.string.database_unavailable, Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void setSeason(int seasonId) {
        this.seasonId = seasonId;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }

    private class UpdateSeasonTask extends AsyncTask<Integer, Void, Boolean> {
        ContentValues seasonValues;
        protected void onPreExecute() {
            CheckBox favorite = getActivity().findViewById(R.id.favorite);
            seasonValues = new ContentValues();
            seasonValues.put("IS_FAVORITE", favorite.isChecked());
        }

        protected Boolean doInBackground(Integer... seasons) {
            seasonId = seasons[0];
            SQLiteOpenHelper vacationSpotDatabaseHelper = new VacationSpotsDatabaseHelper(getActivity());

            try {
                SQLiteDatabase db = vacationSpotDatabaseHelper.getWritableDatabase();
                db.update("SEASON", seasonValues, "_id = ?", new String[]{Integer.toString(seasonId)});
                db.close();
                return true;
            } catch (SQLiteException e) {
                return false;
            }
        }

        protected void onPostExecute(Boolean success) {
            if (!success) {
                Toast toast = Toast.makeText(getActivity(), R.string.database_unavailable, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            seasonId = savedInstanceState.getInt("seasonId");
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seasonId", seasonId);
    }
}