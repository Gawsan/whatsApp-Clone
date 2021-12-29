package com.gawsan.whatsapp_clone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private EditText upno,ucode;
    private Button uverify;
    String mverficationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            signInWithPhoneAuthCredetial(phoneAuthCredential);
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

        }

        public void onCodeSent(String verficationId,PhoneAuthProvider.ForceResendingToken forceResendingToken){
            super.onCodeSent(verficationId,forceResendingToken);
            mverficationId=verficationId;
            uverify.setText("Verify code");
        }
    };

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
                startVerification();

            }
        });

    }

    private void signInWithPhoneAuthCredetial(PhoneAuthCredential phoneAuthCredential){
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                    UseIsLoggedIN();

            }
        });
    }

    private void UseIsLoggedIN() {
        FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();

        if(user!=null){
            startActivity(new Intent(getApplicationContext(),MainPage.class));
            finish();
            return;
        }

    }

    private void startVerification(){
        //1 st  one phone number next one timer
        PhoneAuthProvider.getInstance().verifyPhoneNumber(upno.getText().toString(),60, TimeUnit.SECONDS, this,mCallbacks);

    }
    
        private void startCall(){
        //calling option added
        PhoneAuthProvider.getInstance().verifyPhoneNumber(upno.getText().toString(),60, TimeUnit.SECONDS, this,mCallbacks);

    }



}
