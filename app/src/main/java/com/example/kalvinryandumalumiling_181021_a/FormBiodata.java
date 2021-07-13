package com.example.kalvinryandumalumiling_181021_a;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalvinryandumalumiling_181021_a.database.Database;
import com.example.kalvinryandumalumiling_181021_a.modal.Mahasiswa;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class FormBiodata extends AppCompatActivity {
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    public static final int MY_PERMISSIONS_REQUEST_GALERI = 200;
    public static final String ALLOW_KEY = "ALLOWED";
    public static final String CAMERA_PREF = "camera_pref";
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormater;
    private TextView tvDateResult;
    private EditText btnDatePicker;
    EditText txtStb, txtNama, txtAlamat, txtTlp, txtTmpLahir, txtTgglLahir;
    Button btnDaftar, btnEdit, btnShow, btnSearch, btnDelete, btncapture, btngaleri;
    RadioGroup jenisKelamin;
    RadioButton rb_L, rb_P;
    CheckBox pilihan_1, pilihan_2, pilihan_3, pilihan_4, pilihan_5, pilihan_6;
    ImageView imagecamera;

    public void showDateDialog() {

        java.util.Calendar newCalender = java.util.Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                java.util.Calendar newDate = java.util.Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                tvDateResult.setText(dateFormater.format((newDate.getTime())));
            }
        }, newCalender.get(Calendar.YEAR), newCalender.get(Calendar.MONTH), newCalender.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_biodata);
        dateFormater = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        tvDateResult = (TextView) findViewById(R.id.txtTanggal);
        btnDatePicker = (EditText) findViewById(R.id.txtTanggal);
        btnSearch = (Button) findViewById(R.id.btn_search);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnShow = (Button) findViewById(R.id.btnShow);
        btnDaftar = (Button) findViewById(R.id.btnDaftar);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        rb_L = (RadioButton) findViewById(R.id.radiobtn_L);
        rb_P = (RadioButton) findViewById(R.id.radiobtn_P);
        txtStb = findViewById(R.id.txtNim);
        txtNama = findViewById(R.id.txtNama);
        txtAlamat = findViewById(R.id.txtAlamat);
        txtTlp = findViewById(R.id.txtTlp);
        txtTmpLahir = findViewById(R.id.txt_tempatLahir);
        txtTgglLahir = findViewById(R.id.txtTanggal);
        jenisKelamin = findViewById(R.id.jk);
        pilihan_1 = findViewById(R.id.chk_membaca);
        pilihan_2 = findViewById(R.id.chk_games);
        pilihan_3 = findViewById(R.id.chk_bersepeda);
        pilihan_4 = findViewById(R.id.chk_kumpul);
        pilihan_5 = findViewById(R.id.chck_gunung);
        pilihan_6 = findViewById(R.id.chk_glow);
        btncapture = findViewById(R.id.btnCamera);
        btngaleri = findViewById(R.id.btnGaleri);
        imagecamera = findViewById(R.id.imagecamera);

        btncapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, MY_PERMISSIONS_REQUEST_CAMERA);
            }
        });

        btngaleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, MY_PERMISSIONS_REQUEST_GALERI);
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormBiodata.this, ViewData.class);
                startActivity(intent);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database database = new Database(getApplicationContext(), null, null, 1);

                String Stambuk;

                Stambuk = txtStb.getText().toString();
                database.onOpen();
                Mahasiswa mahasiswa = database.getmahasiswa(Stambuk);
                if (mahasiswa != null) {
                    displayToast("Kalvin_Ryan_Duma_Lumiling-181021_A : Data Ditemukan!!");
                    txtNama.setText(mahasiswa.getNama());
                    txtAlamat.setText(mahasiswa.getAlamat());
                    String Jk = mahasiswa.getJkl();
                    if (Jk.equals("Laki laki")) {
                        rb_L.setChecked(true);
                    } else {
                        rb_P.setChecked(true);
                    }
                    txtTlp.setText(mahasiswa.getTlp());
                    txtTmpLahir.setText(mahasiswa.getTmplahir());
                    txtTgglLahir.setText(mahasiswa.getTgllahir());
                    String hoby = mahasiswa.getHoby();
                    if (hoby.equals("Membaca")) {
                        pilihan_1.setChecked(true);
                    }
                } else {
                    displayToast("Kalvin_Ryan_Duma_Lumiling-181021_A : Data Kosong!!");
                }
                database.close();

            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database database = new Database(getApplicationContext(), null, null, 1);
                String stb = txtStb.getText().toString();
                String nama = txtNama.getText().toString();
                String alamat = txtAlamat.getText().toString();
                String tlp = txtTlp.getText().toString();
                String tmplahir = txtTmpLahir.getText().toString();
                String tglLahir = String.valueOf(tvDateResult.getText().toString());

                int yourGender = jenisKelamin.getCheckedRadioButtonId();
                RadioButton jk = (RadioButton) findViewById(yourGender);
                String jkl = String.valueOf(jk.getText().toString());
                String myhoby = "";
                if (pilihan_1.isChecked()) {
                    myhoby += pilihan_1.getText().toString();
                }

                if (pilihan_2.isChecked()) {
                    myhoby += pilihan_2.getText().toString();
                }

                if (pilihan_3.isChecked()) {
                    myhoby += pilihan_3.getText().toString();
                }

                if (pilihan_4.isChecked()) {
                    myhoby += pilihan_4.getText().toString();
                }

                if (pilihan_5.isChecked()) {
                    myhoby += pilihan_5.getText().toString();
                }

                if (pilihan_6.isChecked()) {
                    myhoby += pilihan_6.getText().toString();
                }

                database.onOpen();
                database.updateMahasiswa(
                        Long.parseLong(stb), nama, alamat, jkl, tlp, tmplahir, tglLahir, myhoby
                );
                database.close();
                displayToast("Kalvin_Ryan_Duma_Lumiling-181021_A : Data Berhasil Di Update!!!!");
            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database database = new Database(getApplicationContext(), null, null, 1);
                String stb = txtStb.getText().toString();
                String nama = txtNama.getText().toString();
                String alamat = txtAlamat.getText().toString();
                String tlp = txtTlp.getText().toString();
                String tmplahir = txtTmpLahir.getText().toString();
                String tglLahir = String.valueOf(tvDateResult.getText().toString());

                int yourGender = jenisKelamin.getCheckedRadioButtonId();
                RadioButton jk = (RadioButton) findViewById(yourGender);
                String jkl = String.valueOf(jk.getText().toString());
                String myhoby = "";
                if (pilihan_1.isChecked()) {
                    myhoby += pilihan_1.getText().toString();
                }

                if (pilihan_2.isChecked()) {
                    myhoby += pilihan_2.getText().toString();
                }

                if (pilihan_3.isChecked()) {
                    myhoby += pilihan_3.getText().toString();
                }

                if (pilihan_4.isChecked()) {
                    myhoby += pilihan_4.getText().toString();
                }

                if (pilihan_5.isChecked()) {
                    myhoby += pilihan_5.getText().toString();
                }

                if (pilihan_6.isChecked()) {
                    myhoby += pilihan_6.getText().toString();
                }
                Mahasiswa mahasiswa = new Mahasiswa(stb, nama, alamat, jkl, tlp, tmplahir, tglLahir, myhoby);

                database.onOpen();
                database.insertData(mahasiswa);
                database.close();
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
                displayToast("Kalvin_Ryan_Duma_Lumiling-181021_A : Success menambahkan data mahasiswa");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database database = new Database(getApplicationContext(), null, null, 1);
                String stb = txtStb.getText().toString();
                database.onOpen();
                boolean result = database.deleteMahasiswa(stb);
                if (result) {
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
                } else {
                    displayToast("Data Tidak Bisa Dihapus!!!");
                }
                database.close();
            }
        });

        dateFormater = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        tvDateResult = (TextView) findViewById(R.id.txtTanggal);
        btnDatePicker = (EditText) findViewById(R.id.txtTanggal);
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
    }
    public void displayToast(String toast){
        Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) { case(MY_PERMISSIONS_REQUEST_CAMERA) :
            if(resultCode == Activity.RESULT_OK) { // result code sama, save gambar ke bitmap
                Bitmap bitmap;
                bitmap = (Bitmap) data.getExtras().get("data");
                imagecamera.setImageBitmap(bitmap);
            }
            break;
            case(MY_PERMISSIONS_REQUEST_GALERI) : if(resultCode == Activity.RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    imagecamera.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace(); Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
            } break;
        }
    }
    public static void saveToPreferences(Context context, String key, Boolean allowed) {
        SharedPreferences myPrefs = context.getSharedPreferences(CAMERA_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = myPrefs.edit(); prefsEditor.putBoolean(key, allowed);
        prefsEditor.commit();
    }
    public static Boolean getFromPref(Context context, String key) {
        SharedPreferences myPrefs = context.getSharedPreferences(CAMERA_PREF, Context.MODE_PRIVATE);
        return (myPrefs.getBoolean(key, false));
    }
    private void showAlert() {
        androidx.appcompat.app.AlertDialog alertDialog = new androidx.appcompat.app.AlertDialog.Builder(FormBiodata.this).create();
        alertDialog.setTitle("Alert"); alertDialog.setMessage("App needs to access the Camera.");
        alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { dialog.dismiss(); finish();
            }
        });
        alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE, "ALLOW", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                ActivityCompat.requestPermissions(FormBiodata.this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
            }
        }); alertDialog.show();
    } private void showSettingsAlert() {
        androidx.appcompat.app.AlertDialog alertDialog = new androidx.appcompat.app.AlertDialog.Builder(FormBiodata.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("App needs to access the Camera.");
        alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { dialog.dismiss();
                //finish();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SETTINGS", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { dialog.dismiss();
                startInstalledAppDetailsActivity(FormBiodata.this);
            }
        });
        alertDialog.show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                for (int i = 0, len = permissions.length; i < len; i++) {
                    String permission = permissions[i];
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
                        if (showRationale) {
                            showAlert();
                        } else if (!showRationale) {
                            saveToPreferences(FormBiodata.this, ALLOW_KEY, true);
                        }
                    }
                }
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    public static void startInstalledAppDetailsActivity(final FormBiodata context) {
        if (context == null) {
            return;
        }
        final Intent i = new Intent();
        i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setData(Uri.parse("package:" + context.getPackageName()));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(i);
    }


}
