package com.example.mahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DbHelper dbHelper;
    private Button btnInput, btnGet;
    private EditText etname;
    private TextView tvnames;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(this);
        tvnames = findViewById(R.id.tvnames);
        btnInput = findViewById(R.id.btnInput);
        btnGet = findViewById(R.id.btnGet);
        etname = findViewById(R.id.etname);

        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.addStudentDetail(etname.getText().toString());
                etname.setText("");
                Toast.makeText(MainActivity.this, "Stored Succesfully!",
                        Toast.LENGTH_SHORT).show();
            }
        });
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList = dbHelper.getAllStudentsList();
                tvnames.setText("");
                for (int i = 0; i < arrayList.size(); i++){
                    tvnames.setText(tvnames.getText().toString()+", "+arrayList.get(i));
                }
            }
        });
    }
}