package ludf.ik17255.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLiteDB extends SQLiteOpenHelper {

    private static final String DB_Name = "MyDatabase";
    private static final int DB_Version = 1;
    private static final String TABLE_NAME = "RECORDS";
    private static final String Create_Table = "CREATE TABLE " + TABLE_NAME + "("
            +"id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "SCALING_COEFFICIENT FLOAT, "
            + "Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP"
            +");";

    public SQLiteDB(Context context) {
        super(context, DB_Name, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_Name);
        onCreate(db);
    }

    public void addRecord(float scaling){
        SQLiteDatabase  db = this.getWritableDatabase();
        ContentValues rec = new ContentValues();
        rec.put("SCALING_COEFFICIENT", scaling);
        db.insert("RECORDS", null, rec);
    }

    public ArrayList<HashMap<String, String>> getValues(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<HashMap<String, String>> records = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do{
                HashMap<String,String> record = new HashMap<>();
                record.put("ID",String.valueOf(cursor.getInt(0)));
                record.put("Scaling",String.valueOf(cursor.getFloat(1)));
                record.put("Timestamp",cursor.getString(2));
                records.add(record);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return records;
    }

    public List<Float> getScalingValues(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SCALING_COEFFICIENT FROM " + TABLE_NAME, null);
        List<Float> res = new ArrayList<>();
        if (cursor.moveToFirst()){
            do{
                res.add(cursor.getFloat(0));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return res;
    }

    public void delete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", new String[] {id} );
    }

    public void clear(){
        SQLiteDatabase  db = this.getWritableDatabase();
        db.execSQL("DROP TABLE " + TABLE_NAME);
        db.execSQL(Create_Table);
    }



}
