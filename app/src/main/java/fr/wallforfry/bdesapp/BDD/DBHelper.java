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

import fr.wallforfry.bdesapp.Fragments.JeuxFragment;
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

    public static final String GAMES_TABLE_NAME = "app_games";
    public static final String GAMES_COLUMN_ID = "id";
    public static final String GAMES_COLUMN_IDDB = "iddb";
    public static final String GAMES_COLUMN_TYPE = "type";
    public static final String GAMES_COLUMN_TITLE = "title";
    public static final String GAMES_COLUMN_SUBTITLE = "subtitle";
    public static final String GAMES_COLUMN_PICTURE = "picture";
    public static final String GAMES_COLUMN_DATE = "date";

    private HashMap hp;
    int id_To_Update = 0;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table " + NEWS_TABLE_NAME +
                        "(id integer, type integer, title text, subtitle text, content text, picture text, action1 text, action2 text, date text)"
        );

        db.execSQL(
                "create table " + GAMES_TABLE_NAME +
                        "(id integer, iddb integer, type integer, title text, subtitle text, picture text, date text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + NEWS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GAMES_TABLE_NAME);
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


    public boolean insertGames  (int id, int iddb, int type, String title, String subtitle, String picture, String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GAMES_COLUMN_ID, id);
        contentValues.put(GAMES_COLUMN_IDDB, iddb);
        contentValues.put(GAMES_COLUMN_TYPE, type);
        contentValues.put(GAMES_COLUMN_TITLE, title);
        contentValues.put(GAMES_COLUMN_SUBTITLE, subtitle);
        contentValues.put(GAMES_COLUMN_PICTURE, picture);
        contentValues.put(GAMES_COLUMN_DATE, date);
        db.insert(GAMES_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getDataGames(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+ GAMES_TABLE_NAME +" where id="+ id +"", null );
        return res;
    }

    public String getGameSubtitle(Cursor cursor, String index){
        //SQLiteDatabase db = this.getReadableDatabase();
        cursor.moveToFirst();
        String subtitle = "plop";

        subtitle = cursor.getString(cursor.getColumnIndex(index));

        return subtitle;
    }

    public int getGameId(Cursor cursor, String index){
        //SQLiteDatabase db = this.getReadableDatabase();
        cursor.moveToFirst();
        int subtitle = 0;

        subtitle = cursor.getInt(cursor.getColumnIndex(index));

        return subtitle;
    }

    public void gameExist(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + GAMES_TABLE_NAME + " where id=" + id + "", null);
        cursor.getCount();
    }

    public int getRowGame(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery("select * from " + GAMES_TABLE_NAME + " where id=" + id + "", null);
        int res = cursor.getColumnIndex("id");
        return res;
    }

    public int numberOfRowsGames(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, GAMES_TABLE_NAME);
        return numRows;
    }

    public boolean updateGames (int id, int iddb, int type, String title, String subtitle, String picture, String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GAMES_COLUMN_ID, id);
        contentValues.put(GAMES_COLUMN_IDDB, iddb);
        contentValues.put(GAMES_COLUMN_TYPE, type);
        contentValues.put(GAMES_COLUMN_TITLE, title);
        contentValues.put(GAMES_COLUMN_SUBTITLE, subtitle);
        contentValues.put(GAMES_COLUMN_PICTURE, picture);
        contentValues.put(GAMES_COLUMN_DATE, date);
        db.update(GAMES_TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteGames (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(GAMES_TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllGames()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + GAMES_TABLE_NAME, null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            array_list.add(res.getString(res.getColumnIndex(GAMES_COLUMN_TITLE)));
            res.moveToNext();
        }
        return array_list;
    }
}
