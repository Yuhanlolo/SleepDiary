package app.sleepdiary.com.sleepdiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yuhan on 9/29/15.
 */
public class SleepdiaryDBHepler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Contact.db";
    private static final String TABLE_NAME = "Sleepdiary";
    //private static final int COLUMN_day = "day";
    private static final String COLUMN_coffee = "no_coffee";
    private static final String COLUMN_wine = "no_wine";
    private static final String COLUMN_smoke = "no_smoke";
    private static final String COLUMN_naptime = "no_naptime";
    private static final String COLUMN_sleepdurationday = "sleepdurationday";
    private static final String COLUMN_pilltime = "time_pill";
    private static final String COLUMN_pillname = "name_pill";


    SQLiteDatabase db;
    private static final String TABLE_CREATE = "Create Table Contacts ( no_coffee integer not null, no_wine integer not null, no_smoke integer not null, no_naptime integer not null, sleepdurationday text not null, time_pill text not null, name_pill text not null);";


    public SleepdiaryDBHepler (Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertColumn(SleepdiaryInfo s)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(COLUMN_day, s.getNo_coffee());
        values.put(COLUMN_coffee, s.getNo_coffee());
        values.put(COLUMN_wine, s.getNo_wine());
        values.put(COLUMN_smoke, s.getNo_smoke());
        values.put(COLUMN_naptime,s.getNo_naptime());
        values.put(COLUMN_sleepdurationday, s.getSleepdurationday());
        values.put(COLUMN_pilltime, s.getPilltime());
        values.put(COLUMN_pillname,s.getPillname());


        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS"+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

}
