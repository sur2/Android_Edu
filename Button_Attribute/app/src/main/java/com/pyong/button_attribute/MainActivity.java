package com.pyong.button_attribute;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button1;
    Button button2;
    Button buttonEnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        buttonEnable = (Button)findViewById(R.id.buttonEnable);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button1.getText() == "첫 번째 버튼") {
                    button1.setText("코드에서도 변경 가능");
                }
                else {
                    button1.setText("첫 번째 버튼");
                }
                Toast.makeText(MainActivity.this, "버튼 문구 변경", Toast.LENGTH_SHORT).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button2.setEnabled(false);
                Toast.makeText(MainActivity.this, "두 번째 버튼 비활성화", Toast.LENGTH_SHORT).show();
            }
        });

        buttonEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button2.setEnabled(true);
                Toast.makeText(MainActivity.this, "두 번째 버튼 활성화", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
