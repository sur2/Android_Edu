package com.pyong.tutorial_database1;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    SQLiteDatabase db;
    EditText editName, editNumber;
    Button addButton, selectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        try {
            db = dbHelper.getWritableDatabase();
        }catch(SQLException e) {
            db = dbHelper.getReadableDatabase();
        }
        editName = (EditText)findViewById(R.id.editName);
        editNumber = (EditText)findViewById(R.id.editNumber);

        addButton = (Button)findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert(v);
                editName.setText("");
                editNumber.setText("");
            }
        });
        selectButton = (Button)findViewById(R.id.selectButton);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select(v);

            }
        });
    }

    public void insert(View view) {
        String name = editName.getText().toString();
        String tel = editNumber.getText().toString();
        db.execSQL("INSERT INTO contacts VALUES (null, '"   + name + "', '" + tel + "');");
        Toast.makeText(this, "추가", Toast.LENGTH_SHORT).show();
    }

    public void select(View view) {
        String name = editName.getText().toString();
        Cursor cursor;
        cursor = db.rawQuery("SELECT name, tel FROM contacts WHERE name = '" + name + "';", null);
        Toast.makeText(this, "탐색", Toast.LENGTH_SHORT).show();

        while(cursor.moveToNext()) {
            String tel = cursor.getString(1);
            editNumber.setText(tel);
        }
    }

}
