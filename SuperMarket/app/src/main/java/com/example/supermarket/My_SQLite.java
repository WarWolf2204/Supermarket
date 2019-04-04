package com.example.supermarket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class My_SQLite extends SQLiteOpenHelper {
    public static final String DATABASENAME ="market";
    public static final String TATBLE ="dicho";
    public static final String ID ="id";
    public static final String ngaygio = "ngaygio";
    public static final String tenmonan = "tenmonan";
    public static final String giamonan = "giamonan";

    private Context context;
    public My_SQLite( Context context) {
        super(context, DATABASENAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creatable = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, %s TEXT ,%s TEXT ,%s TEXT )",
                TATBLE,ID,ngaygio,tenmonan,giamonan);
        db.execSQL(creatable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void add (Model model)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values  = new ContentValues();
     //   values.put(ID,model.getID());

        values.put(tenmonan,model.getTenmon());
        values.put(giamonan,model.getGiatien());
        db.insert(TATBLE,null,values);
        db.close();

    }
   public List<Model> getALL()
   {
       List<Model> modelList = new ArrayList<Model>();
       String mQuery  = "SELECT * FROM " +TATBLE;
       SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
       Cursor cursor = sqLiteDatabase.rawQuery(mQuery,null);
       if(cursor.moveToFirst())
       {
           do {
               Model model = new Model();
               model.setID(cursor.getString(0));
               model.setThoigian(cursor.getString(1));
               model.setTenmon(cursor.getString(2));
               model.setGiatien(cursor.getString(3));
               modelList.add(model);

           }while (cursor.moveToNext());
           cursor.close();
           sqLiteDatabase.close();
       }
       return modelList;
   }
}
