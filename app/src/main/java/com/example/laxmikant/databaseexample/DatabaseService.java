package com.example.laxmikant.databaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseService extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "emp";
    private static final String TABLE_INFO = "info";
    private static final String KEY_NAME = "name";
    private static final String KEY_AGE = "age";
    private static final String KEY_COMPANY = "company";
    private static final String KEY_ROLE = "role";

    public DatabaseService(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_INFO + "(" + KEY_NAME + "TEXT," + KEY_AGE + "TEXT," + KEY_COMPANY + "TEXT," + KEY_ROLE + "TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_INFO);
        onCreate(sqLiteDatabase);
    }

    public void addInformationToData(EmployeeInfoModel infoModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, infoModel.getName());
        values.put(KEY_AGE, infoModel.getAge());
        values.put(KEY_COMPANY, infoModel.getCompany());
        values.put(KEY_ROLE, infoModel.getRole());
        db.insert(TABLE_INFO, null, values);
        db.close();
    }

    public List<EmployeeInfoModel> getAllInfo() {
        List<EmployeeInfoModel> infoList = new ArrayList<EmployeeInfoModel>();
        String selectQuery = "SELECT * FROM " + TABLE_INFO;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        while(cursor.moveToNext()) {
            EmployeeInfoModel infoModel = new EmployeeInfoModel();
            infoModel.setName(cursor.getString(0));
            infoModel.setAge(cursor.getString(1));
            infoModel.setCompany(cursor.getString(2));
            infoModel.setRole(cursor.getString(3));
            infoList.add(infoModel);
        }
        return infoList;
    }
}
