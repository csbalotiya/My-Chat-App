package com.example.mychatapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mychatapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    TextView text_signup;
    EditText login_email;
    EditText login_password;
    TextView signin_btn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        text_signup = findViewById(R.id.text_signup);
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        signin_btn = findViewById(R.id.sign_btn);
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        auth = FirebaseAuth.getInstance();

        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = login_email.getText().toString();
                String password = login_password.getText().toString();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "Enter Valid Data ", Toast.LENGTH_SHORT).show();

                }else if(!email.matches(emailPattern)){
                    login_email.setText("Invalid Email ");
                    Toast.makeText(LoginActivity.this, "Inavalid Email", Toast.LENGTH_SHORT).show();

                }else if(password.length() < 6){
                    login_email.setText("Invalid password ");
                    Toast.makeText(LoginActivity.this, "Please Enter Valid Password ", Toast.LENGTH_SHORT).show();

                }else{
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                            }else{
                                Toast.makeText(LoginActivity.this, "Error in login ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });


        text_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });
    }
}