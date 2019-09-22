package com.pyong.tutorial_database;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    SQLiteDatabase db;

    Button addbt, selectbt;
    EditText edit_name, edit_tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            db = dbHelper.getReadableDatabase();
        }
        edit_name = (EditText)findViewById(R.id.edit_name);
        edit_tel = (EditText)findViewById(R.id.edit_tel);

        addbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert(v);
                Toast.makeText(MainActivity.this, "추가", Toast.LENGTH_SHORT).show();
            }
        });

        selectbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select(v);
            }
        });

    }

    public void insert(View view) {
        String name = edit_name.getText().toString();
        String tel = edit_tel.getText().toString();
        db.execSQL("INSERT INTO contacts VALUES (null, '"   + name + "', '" + tel + "');");
        Toast.makeText(this, "추가", Toast.LENGTH_SHORT).show();
    }

    public void select(View view) {
        String name = edit_name.getText().toString();
        Cursor cursor;
        cursor = db.rawQuery("SELECT name, tel FROM contacts WHERE name = '" + name + "';", null);
        Toast.makeText(this, "탐색", Toast.LENGTH_SHORT).show();

        while(cursor.moveToNext()) {
            String tel = cursor.getString(1);
            edit_tel.setText(tel);
        }
    }
}
