package edu.communication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConnectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        final EditText portText = (EditText) findViewById(R.id.portText);
        Button connectButton = (Button) findViewById(R.id.connectButton);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent connectIntent = new Intent(ConnectActivity.this, MainActivity.class);
                connectIntent.putExtra("PORT", Integer.parseInt(portText.getText().toString()));
                ConnectActivity.this.startActivity(connectIntent);
            }
        });
    }

}
