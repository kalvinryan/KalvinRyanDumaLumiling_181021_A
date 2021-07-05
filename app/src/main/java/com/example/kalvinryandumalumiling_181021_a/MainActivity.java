package com.example.kalvinryandumalumiling_181021_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtNama,txtPassword;
    Button btnLogin,btnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNama=findViewById(R.id.txtNama);
        txtPassword=findViewById(R.id.txtPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnClose=findViewById(R.id.btnClose);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txtNama.getText().toString().equals("kalvin")&&txtPassword.getText().toString().equals("181021")){
                    Intent intent = new Intent(MainActivity.this,FormBiodata.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this,"Anda Belum Terdaftar!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}