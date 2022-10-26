package com.example.w7_p2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "wordCorrectDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_WC = "wordCorrect";
    private static final String WRONG = "wrongWord";
    private static final String CORRECT = "correctWord";

    public DatabaseManager( Context context ) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    public void onCreate( SQLiteDatabase db ) {
        // build sql create statement
        String sqlCreate = "create table " + TABLE_WC + "( " + WRONG;
        sqlCreate += " text primary key , ";
        sqlCreate += CORRECT + " text )" ;

        db.execSQL( sqlCreate );
    }

    public void onUpgrade( SQLiteDatabase db,
                           int oldVersion, int newVersion ) {
        // Drop old table if it exists
        db.execSQL( "drop table if exists " + TABLE_WC );
        // Re-create tables
        onCreate( db );
    }

    public void dropTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("drop table if exists " + TABLE_WC);
        onCreate(db);
    }

    public void insert( String wrongWord, String correctWord ) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "insert into " + TABLE_WC;
        sqlInsert += " values( '" + wrongWord;
        sqlInsert += "', '" + correctWord + "' )";
        Log.d("debugging::", sqlInsert);

        db.execSQL( sqlInsert );
        db.close( );
    }

    public void deleteByWrongWord( String wrongWord ) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlDelete = "delete from " + TABLE_WC;
        sqlDelete += " where " + WRONG + " = " + "'" + wrongWord + "'";
        Log.d("debugging::", sqlDelete);

        db.execSQL( sqlDelete );
        db.close( );
    }

    public void updateByWrongWord( String wrongWord, String correctWord ) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlUpdate = "update " + TABLE_WC;
        sqlUpdate += " set " + CORRECT + " = '" + correctWord + "'";
        sqlUpdate += " where " + WRONG + " = '" + wrongWord + "'";
        Log.d("debugging::", sqlUpdate);

        db.execSQL( sqlUpdate );
        db.close( );
    }

    public ArrayList<String[]> selectAll( ) {
        String sqlQuery = "select * from " + TABLE_WC;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        ArrayList<String[]> wordCorrects = new ArrayList<String[]>();
        while( cursor.moveToNext( ) ) {
            String[] currentWordCorrect = new String[2];
            currentWordCorrect[0] = cursor.getString( 0 );
            currentWordCorrect[1] = cursor.getString( 1 );
            wordCorrects.add( currentWordCorrect );
        }
        db.close( );
        return wordCorrects;
    }

    public String[] selectByWrongWord( String wrongWord ) {
        String sqlQuery = "select * from " + TABLE_WC;
        sqlQuery += " where " + WRONG + " = " + "'" + wrongWord + "'";
        Log.d("debugging::", sqlQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        String[] words = new String[2];
        if (cursor.moveToFirst()) {
            words[0] = cursor.getString(0);
            words[1] = cursor.getString(1);
        }
        return words;
    }
}