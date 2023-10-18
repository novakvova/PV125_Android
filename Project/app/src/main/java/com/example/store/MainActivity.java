package com.example.store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText txtValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtValue = findViewById(R.id.txtValue);
    }

    public void onClickMeHandler(View view) {
        String text = txtValue.getText().toString();
        Log.d("my_text", "----Ви нажали кнопку----"+ text);
    }
}