package com.example.kalvinryandumalumiling_181021_a.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.kalvinryandumalumiling_181021_a.modal.Mahasiswa;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="db_kalvinryandumalumiling_181012_A";
    public final static String Tabel_name="tb_biodata";
    public final static String Column_1="id";
    public final static String Column_2="stb";
    public final static String Column_3="nama";
    public final static String Column_4="alamat";
    public final static String Column_5="jkl";
    public final static String Column_6="tlp";
    public final static String Column_7="tmplahir";
    public final static String Column_8="tgllahir";
    public final static String Column_9="hoby";
    SQLiteDatabase db;
    public Database(@Nullable Context context,String name,SQLiteDatabase.CursorFactory factory,int version) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =  "CREATE TABLE tb_biodata(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "stb VARCHAR(50)," +
                "nama VARCHAR(50)," +
                "alamat VARCHAR(50)," +
                "jkl VARCHAR(50)," +
                "tlp VARCHAR(50),"+
                "tmplahir VARCHAR(50),"+
                "tgllahir VARCHAR(50),"+
                "hoby VARCHAR(50));";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tb_biodata");
        onCreate(db);

    }
    public void onOpen(){
        super.onOpen(db);
        db = this.getWritableDatabase();
    }
    @Override
    public synchronized void close(){
        super.close();
    }

    public void insertData(Mahasiswa mahasiswa){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column_1,mahasiswa.getId());
        contentValues.put(Column_2,mahasiswa.getStb());
        contentValues.put(Column_3,mahasiswa.getNama());
        contentValues.put(Column_4,mahasiswa.getAlamat());
        contentValues.put(Column_5,mahasiswa.getJkl());
        contentValues.put(Column_6,mahasiswa.getTlp());
        contentValues.put(Column_7,mahasiswa.getTmplahir());
        contentValues.put(Column_8,mahasiswa.getTgllahir());
        contentValues.put(Column_9,mahasiswa.getHoby());

        db.insert(Tabel_name,null,contentValues);
    }

    public Cursor getAllMahasiswa(){
        Cursor cursor = db.query(Tabel_name,new String[]{Column_2,Column_3,Column_4,Column_5,Column_6,Column_7,Column_8,Column_9},null,null,null,null,null);
        return cursor;
    }

    public Mahasiswa getmahasiswa(String stambuk){
        String query = "SELECT * FROM " + Tabel_name + " WHERE " + Column_2 + "=\"" + stambuk + "\"";
        Cursor cursor = db.rawQuery(query,null);
        Mahasiswa mahasiswa = new Mahasiswa();
        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            mahasiswa.setStb(cursor.getString(1));
            mahasiswa.setNama(cursor.getString(2));
            mahasiswa.setAlamat(cursor.getString(3));
            mahasiswa.setJkl(cursor.getString(4));
            mahasiswa.setTlp(cursor.getString(5));
            mahasiswa.setTmplahir(cursor.getString(6));
            mahasiswa.setTgllahir(cursor.getString(7));
            mahasiswa.setHoby(cursor.getString(8));
        }
        else {
            mahasiswa = null;
        }
        return mahasiswa;
    }

    public boolean updateMahasiswa(long Stambuk,String newName,String newAlamat,String newJKL,String newTLP,String newTMPLAHIR
    ,String newTGLLAhir,String newHoby){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column_3,newName);
        contentValues.put(Column_4,newAlamat);
        contentValues.put(Column_5,newJKL);
        contentValues.put(Column_6,newTLP);
        contentValues.put(Column_7,newTMPLAHIR);
        contentValues.put(Column_8,newTGLLAhir);
        contentValues.put(Column_9,newHoby);
        return db.update(Tabel_name,contentValues,Column_2 + " = " + Stambuk,null)>0;
    }

    public boolean deleteMahasiswa(String Stambuk){
        boolean result =false;
        String query = "SELECT * FROM "+Tabel_name+" WHERE "+Column_2+" = \""+ Stambuk +"\"";
        Cursor cursor = db.rawQuery(query,null);
        Mahasiswa mahasiswa = new Mahasiswa();
        if (cursor.moveToFirst()){
            mahasiswa.setStb(cursor.getString(1));
            db.delete(Tabel_name,Column_2+" = ?",new String[]{mahasiswa.getStb()});
            cursor.close();
            result =true;
        }
        return result;
    }
}
