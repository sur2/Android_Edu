package com.pyong.newmovie;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayMovie extends AppCompatActivity {

    private DBHelper mydb;
    TextView name;
    TextView director;
    TextView year;
    TextView genre;
    TextView rating;
    int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movie);

        name = (TextView) findViewById(R.id.editTextName);
        director = (TextView) findViewById(R.id.editTextDirector);
        year = (TextView) findViewById(R.id.editTextYear);
        genre = (TextView) findViewById(R.id.editTextGenre);
        rating = (TextView) findViewById(R.id.editTextRating);

        mydb = new DBHelper(this);

        Intent gIntent = getIntent();
        String mName = gIntent.getStringExtra("name");
        String mRelease = gIntent.getStringExtra("release");
        String mRating = gIntent.getStringExtra("rating");
        String mDirector = gIntent.getStringExtra("director");
        String mGenre = gIntent.getStringExtra("genre");

        name.setText(mName);
        year.setText(mRelease);
        rating.setText(mRating);
        director.setText(mDirector);
        genre.setText(mGenre);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                Cursor rs = mydb.getData(Value);
                id = Value;
                rs.moveToFirst();
                String n = rs.getString(rs.getColumnIndex(DBHelper.MOVIES_COLUMN_NAME));
                String d = rs.getString(rs.getColumnIndex(DBHelper.MOVIES_COLUMN_DIRECTOR));
                String y = rs.getString(rs.getColumnIndex(DBHelper.MOVIES_COLUMN_YEAR));
                String na = rs.getString(rs.getColumnIndex(DBHelper.MOVIES_COLUMN_GENRE));
                String r = rs.getString(rs.getColumnIndex(DBHelper.MOVIES_COLUMN_RATING));
                if (!rs.isClosed()) {
                    rs.close();
                }
                Button b = (Button) findViewById(R.id.button1);
                b.setVisibility(View.INVISIBLE);

                name.setText((CharSequence) n);
                director.setText((CharSequence) d);
                year.setText((CharSequence) y);
                genre.setText((CharSequence) na);
                rating.setText((CharSequence) r);
            }
        }
    }

    public void insert(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                if (mydb.updateMovie(id, name.getText().toString(), director.getText().toString(), year.getText().toString(), genre.getText().toString(), rating.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "수정되었음", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "수정되지 않았음", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (mydb.insertMovie(name.getText().toString(), director.getText().toString(), year.getText().toString(), genre.getText().toString(), rating.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "추가되었음", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "추가되지 않았음", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }
        }
    }

    public void delete(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                mydb.deleteMovie(id);
                Toast.makeText(getApplicationContext(), "삭제되었음", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "삭제되지 않았음", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void edit(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int value = extras.getInt("id");
            if (value > 0) {
                if (mydb.updateMovie(id, name.getText().toString(), director.getText().toString(), year.getText().toString(), genre.getText().toString(), rating.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "수정되었음", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "수정되지 않았음", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
