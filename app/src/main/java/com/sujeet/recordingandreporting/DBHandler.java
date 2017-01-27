package com.sujeet.recordingandreporting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DBHandler  extends SQLiteOpenHelper{

    private Context context;

    private static final int VERSION = 11;
    private static final String DATABASE = "MyDB";

    private static final String TABLEA = "Annex3A";
    private static final String COL0A = "Id";
    private static final String COL1A = "NameRHF";
    private static final String COL2A = "Date";
    private static final String COL3A = "NamePatient";
    private static final String COL4A = "Age";
    private static final String COL5A = "Sex";
    private static final String COL6A = "Address";
    private static final String COL7A = "Disease";
    private static final String COL8A = "Site";
    private static final String COL9A = "Reason";
    private static final String COL10A = "PTN";
    private static final String COL11A = "NameOfficial";
    private static final String COL12A = "SIN";
    private static final String COL13A = "DateSputum";
    private static final String COL14A = "NameCollector";
    public static final String COLSA[] = {COL0A, COL1A, COL2A, COL3A, COL4A, COL5A, COL6A,
        COL7A, COL8A, COL9A, COL10A, COL11A, COL12A, COL13A, COL14A};
    private static final String TABLEB = "Annex3B";
    private static final String COL0B = "Id";
    private static final String COL1B = "NameandAddressRHF";
    private static final String COL2B = "NameandAddressWPR";
    private static final String COL3B = "Date";
    private static final String COL4B = "NamePatient";
    private static final String COL5B = "Age";
    private static final String COL6B = "Sex";
    private static final String COL7B = "CompleteAddress";
    private static final String COL8B = "DiseaseClassification";
    private static final String COL9B = "CategoryofTreatment";
    private static final String COL10B = "TypeofPatient";
    private static final String COL11B = "Signature";
    private static final String COL12B = "DateReferred";
    private static final String COL13B = "Designation";
    public static final String COLSB[] = {COL0B, COL1B, COL2B, COL3B, COL4B, COL5B, COL6B,
            COL7B, COL8B, COL9B, COL10B, COL11B, COL12B,COL13B};

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =  "CREATE TABLE "+TABLEA+
                " ( "+
                COL0A+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL1A+" TEXT, "+
                COL2A+" TEXT, "+
                COL3A+" TEXT, "+
                COL4A+" TEXT, "+
                COL5A+" TEXT, "+
                COL6A+" TEXT, "+
                COL7A+" TEXT, "+
                COL8A+" TEXT, "+
                COL9A+" TEXT, "+
                COL10A+" TEXT, "+
                COL11A+" TEXT, "+
                COL12A+" TEXT, "+
                COL13A+" TEXT, "+
                COL14A+" TEXT "+
                " );";
        db.execSQL(query);
        String query2 =  "CREATE TABLE "+TABLEB+
                " ( "+
                COL0B+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL1B+" TEXT, "+
                COL2B+" TEXT, "+
                COL3B+" TEXT, "+
                COL4B+" TEXT, "+
                COL5B+" TEXT, "+
                COL6B+" TEXT, "+
                COL7B+" TEXT, "+
                COL8B+" TEXT, "+
                COL9B+" TEXT, "+
                COL10B+" TEXT, "+
                COL11B+" TEXT, "+
                COL12B+" TEXT, "+
                COL13B+" TEXT "+
                " );";
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLEA+" ; ";
        String query2 = "DROP TABLE IF EXISTS "+TABLEB+" ; ";
        db.execSQL(query);
        db.execSQL(query2);
        onCreate(db);
    }

    public boolean addRowA(ContentValues cv) {

        long status = -1;

        try {
            SQLiteDatabase db = getWritableDatabase();
            status = db.insert(TABLEA, null, cv);
            db.close();
        } catch (Exception e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }

        if(status != -1)
            return true;
        else
            return false;
    }
    public boolean addRowB(ContentValues cv1) {

        long status = -1;

        try {
            SQLiteDatabase db = getWritableDatabase();
            status = db.insert(TABLEB, null, cv1);
            db.close();
        } catch (Exception e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }

        if(status != -1)
            return true;
        else
            return false;
    }

    public Cursor getCursor(String tableName) {

        if(tableName.equals(TABLEB)) {

            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cr = sqLiteDatabase.query(tableName, COLSB, null, null, null, null,
                    null);
            return cr;
        }
        else if(tableName.equals(TABLEA)) {

            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cr = sqLiteDatabase.query(tableName, COLSA, null, null, null, null,
                    null);
            return cr;
        }
        return null;
    }

    public void mark(String tableName, String id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(tableName, "Id = "+id, null);
        sqLiteDatabase.close();
    }

    public ArrayList<String> showTable(String tableName) {

        ArrayList<String> rows = new ArrayList<>();

        if(tableName.equals(TABLEA)) {
            Toast.makeText(context, tableName, Toast.LENGTH_SHORT).show();
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cr = sqLiteDatabase.query(tableName, COLSA, null, null, null , null,
                    null);
            for(cr.moveToFirst();!cr.isAfterLast();cr.moveToNext()) {
                StringBuilder sb = new StringBuilder("");
                sb.append(cr.getString(cr.getColumnIndex(COL0A)));
                sb.append(" ");
                sb.append(cr.getString(cr.getColumnIndex(COL3A)));
                rows.add(sb.toString());
            }
            sqLiteDatabase.close();
        }
        else  if(tableName.equals(TABLEB)) {
                Toast.makeText(context, tableName, Toast.LENGTH_SHORT).show();
                SQLiteDatabase sqLiteDatabase = getWritableDatabase();
                Cursor cr = sqLiteDatabase.query(tableName, COLSB, null, null, null , null,
                        null);
                for(cr.moveToFirst();!cr.isAfterLast();cr.moveToNext()) {
                    StringBuilder sb = new StringBuilder("");
                    sb.append(cr.getString(cr.getColumnIndex(COL1B)));
                    sb.append(" ");
                    sb.append(cr.getString(cr.getColumnIndex(COL3B)));
                    rows.add(sb.toString());
                }
                sqLiteDatabase.close();
        }
        else {
            Toast.makeText(context, "Table not found", Toast.LENGTH_SHORT).show();
        }

        return rows;
    }
}
