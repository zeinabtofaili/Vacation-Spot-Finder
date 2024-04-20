package com.example.projecttwo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class VacationSpotsDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "vacation spots";
    private static final int DB_VERSION = 1;
    private Context mContext;

    public VacationSpotsDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE SEASON (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "NUM_OF_DESTINATIONS INTEGER,"
                + "IMAGE_RESOURCE_ID INTEGER,"
                + "IS_FAVORITE NUMERIC,"
                + "COLOR INTEGER,"
                + "SEASON_SVG INTEGR);");
        sqLiteDatabase.execSQL("CREATE TABLE DESTINATION (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "CITY_NAME TEXT, "
                + "COUNTRY_NAME TEXT, "
                + "PRICE TEXT, "
                + "SEASON_ID INTEGER,"
                + "IMAGE_RESOURCE_ID INTEGER);"
        );
        sqLiteDatabase.execSQL("CREATE TABLE NOTE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "BODY TEXT);");
        insertNote(sqLiteDatabase,"Message1");
        insertNote(sqLiteDatabase,"Message2");
        insertNote(sqLiteDatabase,"Message3");
        insertNote(sqLiteDatabase,"Message4");
        insertNote(sqLiteDatabase,"Message5");



        insertDestination(sqLiteDatabase, mContext.getString(R.string.ontario), mContext.getString(R.string.canada), "1200$", R.drawable.ontario, 1);
        insertDestination(sqLiteDatabase, mContext.getString(R.string.mount_fuji), mContext.getString(R.string.japan), "1000$", R.drawable.mount_fuji, 1);
        insertDestination(sqLiteDatabase, mContext.getString(R.string.lodz), mContext.getString(R.string.poland), "950$", R.drawable.lodz, 1);
        insertDestination(sqLiteDatabase, mContext.getString(R.string.akureyri), mContext.getString(R.string.iceland), "1150$", R.drawable.akureyri, 1);
        insertDestination(sqLiteDatabase, mContext.getString(R.string.markt_artmannsdorf), mContext.getString(R.string.austria), "1300$", R.drawable.markt_hartmannsdorf, 1);
        insertDestination(sqLiteDatabase, mContext.getString(R.string.berlin), mContext.getString(R.string.germany), "2000$", R.drawable.berlin, 2);
        insertDestination(sqLiteDatabase, mContext.getString(R.string.ottawa), mContext.getString(R.string.canada), "3000$", R.drawable.ottawa, 2);
        insertDestination(sqLiteDatabase, mContext.getString(R.string.prague), mContext.getString(R.string.czech_republic), "800$", R.drawable.prague, 2);
        insertDestination(sqLiteDatabase, mContext.getString(R.string.amsterdam), mContext.getString(R.string.netherlands), "1000$", R.drawable.amsterdam, 2);
        insertDestination(sqLiteDatabase, mContext.getString(R.string.nagano), mContext.getString(R.string.japan), "1600$", R.drawable.nagano, 2);
        insertDestination(sqLiteDatabase, mContext.getString(R.string.reykjavik), mContext.getString(R.string.iceland), "1000$", R.drawable.reykjavik, 2);
        insertDestination(sqLiteDatabase, mContext.getString(R.string.edinburgh), mContext.getString(R.string.scotland), "700$", R.drawable.edinburgh, 2);
        insertDestination(sqLiteDatabase, mContext.getString(R.string.rome), mContext.getString(R.string.italy), "800$", R.drawable.rome, 3);
        insertDestination(sqLiteDatabase, mContext.getString(R.string.maldives), mContext.getString(R.string.maldives), "900$", R.drawable.maldives, 3);
        insertDestination(sqLiteDatabase, mContext.getString(R.string.marseille),  mContext.getString(R.string.france), "1800$", R.drawable.marseille, 3);
        insertDestination(sqLiteDatabase, mContext.getString(R.string.zermatt), mContext.getString(R.string.switzerland), "1500$", R.drawable.zermatt, 3);
        insertDestination(sqLiteDatabase, mContext.getString(R.string.whistler), mContext.getString(R.string.canada), "1000$", R.drawable.whistler, 3);
        insertDestination(sqLiteDatabase, mContext.getString(R.string.budapest), mContext.getString(R.string.hungary), "800$", R.drawable.budapest, 3);
        insertDestination(sqLiteDatabase, mContext.getString(R.string.johannesburg), mContext.getString(R.string.south_africa), "1800$", R.drawable.johannesburg, 4);
        insertDestination(sqLiteDatabase, mContext.getString(R.string.delhi), mContext.getString(R.string.india), "900$", R.drawable.delhi, 4);
        insertDestination(sqLiteDatabase, mContext.getString(R.string.provence), mContext.getString(R.string.france), "2000$", R.drawable.provence, 4);
        insertDestination(sqLiteDatabase, mContext.getString(R.string.kuala_lumpur), mContext.getString(R.string.malaysia), "850$", R.drawable.kuala_lumpur, 4);
        insertDestination(sqLiteDatabase, mContext.getString(R.string.istanbul), mContext.getString(R.string.turkey), "700$", R.drawable.istanbul, 4);
        insertSeason(sqLiteDatabase,  mContext.getString(R.string.fall), 5, R.drawable.fall_season, 0, R.color.orange, R.drawable.leaf_svg);
        insertSeason(sqLiteDatabase, mContext.getString(R.string.winter), 7, R.drawable.winter_season, 0, R.color.light_gray, R.drawable.snow_svg);
        insertSeason(sqLiteDatabase, mContext.getString(R.string.summer), 6, R.drawable.summer_season, 0, R.color.blue, R.drawable.sun_svg);
        insertSeason(sqLiteDatabase, mContext.getString(R.string.spring), 5, R.drawable.spring_season, 0, R.color.green, R.drawable.flower_svg);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private static void insertDestination(SQLiteDatabase db,
                                          String cityName,
                                          String countryName,
                                          String price,
                                          int resourceId,
                                          int seasonId) {
        ContentValues destinationValues = new ContentValues();
        destinationValues.put("CITY_NAME", cityName);
        destinationValues.put("COUNTRY_NAME", countryName);
        destinationValues.put("PRICE", price);
        destinationValues.put("SEASON_ID", seasonId);
        destinationValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert("DESTINATION", null, destinationValues);
    }

    private static void insertSeason(SQLiteDatabase db,
                                     String name,
                                     int numOfDestinations,
                                     int resourceId,
                                     int isFavorite,
                                     int color,
                                     int svg) {
        ContentValues seasonValues = new ContentValues();

        seasonValues.put("NAME", name);
        seasonValues.put("NUM_OF_DESTINATIONS", numOfDestinations);
        seasonValues.put("IMAGE_RESOURCE_ID", resourceId);
        seasonValues.put("IS_FAVORITE", isFavorite);
        seasonValues.put("COLOR", color);
        seasonValues.put("SEASON_SVG", svg);
        db.insert("SEASON", null, seasonValues);
    }
    private static void insertNote(SQLiteDatabase db,
                                     String  Note) {
        ContentValues NotesMessage = new ContentValues();

        NotesMessage.put("BODY", Note);

        db.insert("NOTE", null, NotesMessage);
    }
}

