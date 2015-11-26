package fr.wallforfry.bdesapp.BDD;

/**
 * Created by wallerand on 25/11/2015.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import fr.wallforfry.bdesapp.Object.CardBigPictureObject;
import fr.wallforfry.bdesapp.Object.CardGameObject;
import fr.wallforfry.bdesapp.Object.CardMediumRightObject;
import fr.wallforfry.bdesapp.Object.CardPictureOnlyObject;
import fr.wallforfry.bdesapp.R;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String NEWS_TABLE_NAME = "app_news";
    public static final String NEWS_COLUMN_ID = "id";
    public static final String NEWS_COLUMN_TYPE = "type";
    public static final String NEWS_COLUMN_TITLE = "title";
    public static final String NEWS_COLUMN_SUBTITLE = "subtitle";
    public static final String NEWS_COLUMN_CONTENT = "content";
    public static final String NEWS_COLUMN_PICTURE = "picture";
    public static final String NEWS_COLUMN_ACTION1 = "action1";
    public static final String NEWS_COLUMN_ACTION2 = "action2";
    public static final String NEWS_COLUMN_DATE = "date";
    private HashMap hp;
    int id_To_Update = 0;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table " + NEWS_TABLE_NAME +
                        "(id integer, type integer, title text, subtitle text, content text, picture text, action1 text, action2 text, date text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + NEWS_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertNews  (int id, int type, String title, String subtitle, String content, String picture,String action1, String action2, String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NEWS_COLUMN_ID, id);
        contentValues.put(NEWS_COLUMN_TYPE, type);
        contentValues.put(NEWS_COLUMN_TITLE, title);
        contentValues.put(NEWS_COLUMN_SUBTITLE, subtitle);
        contentValues.put(NEWS_COLUMN_CONTENT, content);
        contentValues.put(NEWS_COLUMN_PICTURE, picture);
        contentValues.put(NEWS_COLUMN_ACTION1, action1);
        contentValues.put(NEWS_COLUMN_ACTION2, action2);
        contentValues.put(NEWS_COLUMN_DATE, date);
        db.insert(NEWS_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+ NEWS_TABLE_NAME +" where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, NEWS_TABLE_NAME);
        return numRows;
    }

    public boolean updateNews (int id, int type, String title, String subtitle, String content, String picture,String action1, String action2, String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NEWS_COLUMN_ID, id);
        contentValues.put(NEWS_COLUMN_TYPE, type);
        contentValues.put(NEWS_COLUMN_TITLE, title);
        contentValues.put(NEWS_COLUMN_SUBTITLE, subtitle);
        contentValues.put(NEWS_COLUMN_CONTENT, content);
        contentValues.put(NEWS_COLUMN_PICTURE, picture);
        contentValues.put(NEWS_COLUMN_ACTION1, action1);
        contentValues.put(NEWS_COLUMN_ACTION2, action2);
        contentValues.put(NEWS_COLUMN_DATE, date);
        db.update(NEWS_TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteNews (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(NEWS_TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllNews()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + NEWS_TABLE_NAME, null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            array_list.add(res.getString(res.getColumnIndex(NEWS_COLUMN_TITLE)));
            res.moveToNext();
        }
        return array_list;
    }
}