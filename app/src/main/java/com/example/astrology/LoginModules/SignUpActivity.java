package com.example.astrology.LoginModules;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.astrology.Common.HelperData;
import com.example.astrology.MainActivity;
import com.example.astrology.NameAndDOBActivity;
import com.example.astrology.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class SignUpActivity extends AppCompatActivity {
    TextView sendOTP, verifyOTP;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    EditText mobileNo, enterOTP;
    private String verificationId;
    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4;
    HelperData helperData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        initMethod();
        sendOTP.setOnClickListener(v -> {
            if (TextUtils.isEmpty(mobileNo.getText().toString())) {
                Toast.makeText(SignUpActivity.this, "Please enter a valid phone number.", Toast.LENGTH_LONG).show();
            } else {
                String phone = "+91" + mobileNo.getText().toString();
                sendVerificationCode(phone);
                linearLayout1.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.GONE);

                linearLayout3.setVisibility(View.VISIBLE);
                linearLayout4.setVisibility(View.VISIBLE);

                verifyOTP.setOnClickListener(view -> {
                    if (TextUtils.isEmpty(enterOTP.getText().toString())) {
                        Toast.makeText(SignUpActivity.this, "Please Enter OTP", Toast.LENGTH_LONG).show();
                    } else {
                        verifyCode(enterOTP.getText().toString());
                    }
                });
            }
        });
    }

    private void initMethod() {
        mobileNo = findViewById(R.id.mobileNo);
        enterOTP = findViewById(R.id.OTPEd);
        sendOTP = findViewById(R.id.OTP);
        verifyOTP = findViewById(R.id.verify);
        linearLayout1 = findViewById(R.id.linearLayout1);
        linearLayout2 = findViewById(R.id.linearLayout2);
        linearLayout3 = findViewById(R.id.linearLayout3);
        linearLayout4 = findViewById(R.id.linearLayout4);

        helperData = new HelperData(getApplicationContext());
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        FirebaseApp.initializeApp(this);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Intent i = new Intent(SignUpActivity.this, NameAndDOBActivity.class);
                        i.putExtra("mobile", mobileNo.getText().toString());
                        helperData.saveLogin(mobileNo.getText().toString(),"","","","","");
                        helperData.saveIsLogin(true);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void sendVerificationCode(String number) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallBack)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks

            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            final String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                enterOTP.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
        }
    };

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
        if (enterOTP.getText().toString().equals(code)) {
            signInWithCredential(credential);
        }
    }
}