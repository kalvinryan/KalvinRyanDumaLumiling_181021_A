package com.example.kalvinryandumalumiling_181021_a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ViewData extends AppCompatActivity {
    TextView txtStb,txtNama,txtAlamat,txtJKL,txtTLP,txtTMPLahir,txtTanggalLahir,txtHobby;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        txtStb=findViewById(R.id.stb);
        txtNama=findViewById(R.id.nama);
        txtAlamat=findViewById(R.id.alamat);
        txtJKL=findViewById(R.id.jeniskelamin);
        txtTLP=findViewById(R.id.tlp);
        txtTMPLahir=findViewById(R.id.tmpLahir);
        txtTanggalLahir=findViewById(R.id.tglLahir);
        txtHobby=findViewById(R.id.hobby);
        Bundle extra = getIntent().getExtras();
        if(extra!=null){
            String stb = extra.getString("STB");
            txtStb.setText("STAMBUK : "+stb);
            String nama = extra.getString("Nama");
            txtNama.setText("NAMA : "+nama);
            String alamat = extra.getString("Alamat");
            txtAlamat.setText("Alamat : "+alamat);
            String jkl = extra.getString("JKL");
            txtJKL.setText("Jenis Kelamin : "+jkl);
            String tlp = extra.getString("TLP");
            txtTLP.setText(" Telephone: "+tlp);
            String tmpLahir = extra.getString("TmpLahir");
            txtTMPLahir.setText("Tempat Lahir : "+tmpLahir);
            String tglLahir = extra.getString("TglLahir");
            txtTanggalLahir.setText("Tanggal Lahir : "+tglLahir);
            String hobby = extra.getString("Hoby");
            txtHobby.setText("Hobby : "+hobby);

        }else{
            Toast.makeText(this, "Data Belum Terisi", Toast.LENGTH_SHORT).show();
        }
    }
}