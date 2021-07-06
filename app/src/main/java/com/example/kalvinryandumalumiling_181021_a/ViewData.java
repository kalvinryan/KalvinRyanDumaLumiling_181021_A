package com.example.kalvinryandumalumiling_181021_a;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalvinryandumalumiling_181021_a.database.Database;

public class ViewData extends AppCompatActivity {
    TextView txtData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        txtData=findViewById(R.id.DataMahasiswa);

        getAllData();

    }
    public void getAllData(){
        Database database = new Database(getApplicationContext(),null,null,1);
        database.onOpen();
        Cursor cursor = database.getAllMahasiswa();
        cursor.moveToFirst();
        String Title="";
        do{
            Title +=
                    "STAMBUK : " + cursor.getString(0) +"\n"+
                    "Nama : " + cursor.getString(1)+"\n"+
                    "Alamat : " + cursor.getString(2)+"\n"+
                    "Jenis Kelamin : " + cursor.getString(3)+"\n"+
                    "Telephone : " + cursor.getString(4)+"\n"+
                    "Tempat Lahir : " + cursor.getString(5)+"\n"+
                    "Tanggal Lahir : " + cursor.getString(6)+"\n"+
                    "Hoby : " + cursor.getString(7)+"\n\n";

        }while(cursor.moveToNext());
        txtData.setText(Title);
    }
}