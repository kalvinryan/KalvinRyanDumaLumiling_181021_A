package com.example.kalvinryandumalumiling_181021_a;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalvinryandumalumiling_181021_a.database.Database;
import com.example.kalvinryandumalumiling_181021_a.modal.Mahasiswa;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class FormBiodata extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormater;
    private TextView tvDateResult;
    private EditText btnDatePicker;
    EditText txtStb,txtNama,txtAlamat,txtTlp,txtTmpLahir,txtTgglLahir;
    Button btnDaftar,btnEdit,btnShow,btnSearch,btnDelete;
    RadioGroup jenisKelamin;
    RadioButton rb_L,rb_P;
    CheckBox pilihan_1,pilihan_2,pilihan_3,pilihan_4,pilihan_5,pilihan_6;


    public void showDateDialog(){

        java.util.Calendar newCalender = java.util.Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                java.util.Calendar newDate = java.util.Calendar.getInstance();
                newDate.set(year,month,dayOfMonth);
                tvDateResult.setText(dateFormater.format((newDate.getTime())));
            }
        },newCalender.get(Calendar.YEAR),newCalender.get(Calendar.MONTH),newCalender.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_biodata);
        dateFormater = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        tvDateResult = (TextView) findViewById(R.id.txtTanggal);
        btnDatePicker=(EditText) findViewById(R.id.txtTanggal);
        btnSearch = (Button)findViewById(R.id.btn_search);
        btnEdit = (Button)findViewById(R.id.btnEdit);
        btnShow = (Button)findViewById(R.id.btnShow);
        btnDaftar=(Button)findViewById(R.id.btnDaftar);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        rb_L = (RadioButton)findViewById(R.id.radiobtn_L);
        rb_P = (RadioButton)findViewById(R.id.radiobtn_P);
        txtStb = findViewById(R.id.txtNim);
        txtNama = findViewById(R.id.txtNama);
        txtAlamat = findViewById(R.id.txtAlamat);
        txtTlp = findViewById(R.id.txtTlp);
        txtTmpLahir = findViewById(R.id.txt_tempatLahir);
        txtTgglLahir = findViewById(R.id.txtTanggal);
        jenisKelamin = findViewById(R.id.jk);
        pilihan_1=findViewById(R.id.chk_membaca);
        pilihan_2=findViewById(R.id.chk_games);
        pilihan_3=findViewById(R.id.chk_bersepeda);
        pilihan_4=findViewById(R.id.chk_kumpul);
        pilihan_5=findViewById(R.id.chck_gunung);
        pilihan_6=findViewById(R.id.chk_glow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormBiodata.this,ViewData.class);
                startActivity(intent);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database database = new Database(getApplicationContext(),null,null,1);

                String Stambuk;

                Stambuk= txtStb.getText().toString();
                database.onOpen();
                Mahasiswa mahasiswa= database.getmahasiswa(Stambuk);
                if (mahasiswa != null){
                    displayToast("Data Telah Terdaftar!!");
                    txtNama.setText(mahasiswa.getNama());
                    txtAlamat.setText(mahasiswa.getAlamat());
                    String Jk = mahasiswa.getJkl();
                    if (Jk.equals("Laki laki")){
                        rb_L.setChecked(true);
                    }else{
                        rb_P.setChecked(true);
                    }
                    txtTlp.setText(mahasiswa.getTlp());
                    txtTmpLahir.setText(mahasiswa.getTmplahir());
                    txtTgglLahir.setText(mahasiswa.getTgllahir());
                    String hoby=mahasiswa.getHoby();
                    if (hoby.equals("Membaca")){
                        pilihan_1.setChecked(true);
                    }
                }else{
                    displayToast("Data Kosong!!");
                }
                database.close();

            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database database = new Database(getApplicationContext(),null,null,1);
                String stb = txtStb.getText().toString();
                String nama = txtNama.getText().toString();
                String alamat = txtAlamat.getText().toString();
                String tlp = txtTlp.getText().toString();
                String tmplahir = txtTmpLahir.getText().toString();
                String tglLahir = String.valueOf(tvDateResult.getText().toString());

                int yourGender = jenisKelamin.getCheckedRadioButtonId();
                RadioButton jk =(RadioButton) findViewById(yourGender);
                String jkl=String.valueOf(jk.getText().toString());
                String myhoby="";
                if(pilihan_1.isChecked()){
                    myhoby += pilihan_1.getText().toString();
                }

                if(pilihan_2.isChecked()){
                    myhoby +=pilihan_2.getText().toString();
                }

                if(pilihan_3.isChecked()){
                    myhoby +=pilihan_3.getText().toString();
                }

                if(pilihan_4.isChecked()){
                    myhoby +=pilihan_4.getText().toString();
                }

                if(pilihan_5.isChecked()){
                    myhoby +=pilihan_5.getText().toString();
                }

                if(pilihan_6.isChecked()){
                    myhoby +=pilihan_6.getText().toString();
                }

                database.onOpen();
                database.updateMahasiswa(
                        Long.parseLong(stb),nama,alamat,jkl,tlp,tmplahir,tglLahir,myhoby
                );
                database.close();
                displayToast("Data Berhasil Di Update!!!!");
            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database database=new Database(getApplicationContext(),null,null,1);
                String stb = txtStb.getText().toString();
                String nama = txtNama.getText().toString();
                String alamat = txtAlamat.getText().toString();
                String tlp = txtTlp.getText().toString();
                String tmplahir = txtTmpLahir.getText().toString();
                String tglLahir = String.valueOf(tvDateResult.getText().toString());

                int yourGender = jenisKelamin.getCheckedRadioButtonId();
                RadioButton jk =(RadioButton) findViewById(yourGender);
                String jkl=String.valueOf(jk.getText().toString());
                String myhoby="";
                if(pilihan_1.isChecked()){
                    myhoby += pilihan_1.getText().toString();
                }

                if(pilihan_2.isChecked()){
                    myhoby +=pilihan_2.getText().toString();
                }

                if(pilihan_3.isChecked()){
                    myhoby +=pilihan_3.getText().toString();
                }

                if(pilihan_4.isChecked()){
                    myhoby +=pilihan_4.getText().toString();
                }

                if(pilihan_5.isChecked()){
                    myhoby +=pilihan_5.getText().toString();
                }

                if(pilihan_6.isChecked()){
                    myhoby +=pilihan_6.getText().toString();
                }
                Mahasiswa mahasiswa = new Mahasiswa(stb,nama,alamat,jkl,tlp,tmplahir,tglLahir,myhoby);

                database.onOpen();
                database.insertData(mahasiswa);
                txtStb.setText("");
                txtNama.setText("");
                rb_P.setChecked(false);
                rb_L.setChecked(false);
                txtAlamat.setText("");
                txtTlp.setText("");
                txtTmpLahir.setText("");
                txtTgglLahir.setText("");
                pilihan_1.setChecked(false);
                pilihan_2.setChecked(false);
                pilihan_3.setChecked(false);
                pilihan_4.setChecked(false);
                pilihan_5.setChecked(false);
                pilihan_6.setChecked(false);
                database.close();
                displayToast("Kalvin_Ryan_Duma_Lumiling-181021_A : Success menambahkan data mahasiswa");
            }
        });
        dateFormater = new SimpleDateFormat("dd-MM-yyyy",Locale.US);
        tvDateResult=(TextView)findViewById(R.id.txtTanggal);
        btnDatePicker=(EditText) findViewById(R.id.txtTanggal);
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database database = new Database(getApplicationContext(),null,null,1);
                String stb = txtStb.getText().toString();
                database.onOpen();
                boolean result = database.deleteMahasiswa(stb);
                if (result){
                    displayToast("Data berhasil Dihapus!!");
                    txtStb.setText("");
                    txtNama.setText("");
                    rb_P.setChecked(false);
                    rb_L.setChecked(false);
                    txtAlamat.setText("");
                    txtTlp.setText("");
                    txtTmpLahir.setText("");
                    txtTgglLahir.setText("");
                    pilihan_1.setChecked(false);
                    pilihan_2.setChecked(false);
                    pilihan_3.setChecked(false);
                    pilihan_4.setChecked(false);
                    pilihan_5.setChecked(false);
                    pilihan_6.setChecked(false);
                }else{
                    displayToast("Data Tidak Bisa Dihapus!!!");
                }
                database.close();
            }
        });
    }

    public void displayToast(String toast){
        Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
    }
}