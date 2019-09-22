package com.pyong.edittext_attribute;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private EditText invisibleText;
    private EditText telText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        invisibleText = (EditText)findViewById(R.id.invisibleText);
        telText = (EditText)findViewById(R.id.telText);

        telText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    Toast.makeText(MainActivity.this, telText.getText().toString(), Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;
            }
        });

    }
}
