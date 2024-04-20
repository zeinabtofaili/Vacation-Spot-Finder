package com.example.projecttwo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ViewNotesFragment  extends Fragment {
    private Cursor cursor;
    private SQLiteDatabase db;
    View layout;






    // array objects
    String courseList[] = {"vacation message one", "vacation message two",
            "vacation message three", "vacation message four",
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       layout = inflater.inflate(R.layout.fragment_view_note, container, false);
////        SQLiteOpenHelper VacationSpotsDatabaseHelper = new VacationSpotsDatabaseHelper(this.getActivity());
////        try {
////            db = VacationSpotsDatabaseHelper.getReadableDatabase();
////            cursor = db.query("NOTE", new String[]{"_id", "BODY"}, null, null, null, null, null);
////            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(inflater.getContext(),
////                    R.layout.notes_view, cursor, new String[]{"BODY"}, new int[]{android.R.id.text1}, 0);
////            setListAdapter(listAdapter);
////        } catch (SQLiteException e) {
////            Toast toast = Toast.makeText(this.getActivity(), "Database unavailable", Toast.LENGTH_SHORT);
////            toast.show();
////        }

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(inflater.getContext(),R.layout.notes_view, R.id.listnew,courseList);
//        setListAdapter(adapter);
        //Inflate the layout for this fragment
        return layout;


    }


//    SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
//            android.R.layout.simple_list_item_1, cursor, new String[]{"NAME"}, new int[]{android.R.id.text1}, 0);
    @Override
    public void onStart() {
        super.onStart();

        SQLiteOpenHelper VacationSpotsDatabaseHelper = new VacationSpotsDatabaseHelper(this.getActivity());
        ListView listView = getView().findViewById(R.id.noteList);
        try {
            db = VacationSpotsDatabaseHelper.getReadableDatabase();
            cursor = db.query("NOTE", new String[]{"_id", "BODY"}, null, null, null, null, null);
            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(getActivity(),
                    android.R.layout.simple_list_item_1, cursor, new String[]{"BODY"}, new int[]{android.R.id.text1}, 0);
            listView.setAdapter(listAdapter);
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this.getActivity(),R.string.database_unavailable, Toast.LENGTH_SHORT);
            toast.show();
        }
//        //Create the listener
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listDrinks, View itemView, int position, long id) {
//                Intent intent = new Intent(getActivity(), deleteActivity.class);
//                intent.putExtra("NoteID", (int) id);
//                startActivity(intent);
                db.delete("NOTE", "_id" + "=" + (int)id, null);
                Intent intent = new Intent(getActivity(), MainActivity.class);
//                intent.putExtra("NoteID", (int) id);
                startActivity(intent);
              //  db.rawQuery("Delete  * FROM NOTE WHERE _id = " + (int)id, null);
                //Pass the drink the user clicks on to DrinkActivity
            }
        };
        //Assign the listener to the list view
        listView.setOnItemClickListener(itemClickListener);
    }




    @Override
    public void onDestroy() {
        super.onDestroy();

        cursor.close();
        db.close();
    }

}