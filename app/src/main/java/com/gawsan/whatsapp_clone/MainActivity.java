package com.gawsan.whatsapp_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {
    private EditText upno,ucode;
    private Button uverify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        upno =findViewById(R.id.pno);
        ucode = findViewById(R.id.code);
        uverify=findViewById(R.id.verify);

        uverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPhoneNumberVerification();

            }
        });

    }

    private void startPhoneNumberVerification(){

    }
}