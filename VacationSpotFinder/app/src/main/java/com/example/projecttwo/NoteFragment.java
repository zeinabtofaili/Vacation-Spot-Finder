package com.example.projecttwo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NoteFragment extends Fragment {
private EditText message;
private String land="";
private Button Send;
private View layout;
private Cursor cursor;
private SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (savedInstanceState != null) {
            land=savedInstanceState.getString("placeholder");



        }


    }
public void setText(View layout){
    message.setText(land);

}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        layout =inflater.inflate(R.layout.fragment_note, container, false);
//        View view=getView();
        message=layout.findViewById(R.id.message);

        setText(layout);


        return layout;
    }


    @Override
    public void onStart() {
        super.onStart();
        SQLiteOpenHelper VacationSpotsDatabaseHelper = new VacationSpotsDatabaseHelper(this.getActivity());
        Send=layout.findViewById(R.id.WriteNote);
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    db = VacationSpotsDatabaseHelper.getWritableDatabase();
                    String Mssg=message.getText().toString();
                    String Query="INSERT INTO NOTE (BODY) VALUES('"+Mssg+"');";
                    db.execSQL(Query);
                    Intent intent = new Intent(getActivity(), ViewNotesActivity.class);
//                intent.putExtra("NoteID", (int) id);
                    startActivity(intent);

                } catch (SQLiteException e) {
                    Toast toast = Toast.makeText(getActivity(),R.string.database_unavailable, Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });



    }
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("placeholder", message.getText().toString());
    }
}