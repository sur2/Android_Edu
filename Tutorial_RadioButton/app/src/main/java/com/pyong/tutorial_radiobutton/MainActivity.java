package com.pyong.tutorial_radiobutton;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioButton radioButton, red, blue;

    View.OnClickListener radioClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            radioButton = (RadioButton)v;
            Toast.makeText(MainActivity.this, radioButton.getText(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        red = (RadioButton)findViewById(R.id.redRadioButton);
        blue = (RadioButton)findViewById(R.id.blueRadioButton);

        red.setOnClickListener(radioClickListener);
        blue.setOnClickListener(radioClickListener);
    }
}
