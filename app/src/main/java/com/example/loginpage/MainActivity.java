package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    com.example.loginpage.DatabaseHelper db;
    EditText txtEmail, txtPassword;
    Button btnRegister, btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new com.example.loginpage.DatabaseHelper(this);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = txtEmail.getText().toString();
                String Password = txtPassword.getText().toString();
                if(Email.equals("")||Password.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkEmail = db.checkEmail(Email);
                    if(checkEmail==true){
                        Boolean insert = db.insert(Email,Password);
                        if(insert==true){
                            Toast.makeText(getApplicationContext(),"Register Successful",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Email already Exists",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Email = txtEmail.getText().toString();
                String Password = txtPassword.getText().toString();
                if(Email.equals("")||Password.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkEmail = db.checkEmail(Email);
                    if(checkEmail==false){
                        Toast.makeText(getApplicationContext(),"Email is correct",Toast.LENGTH_SHORT).show();
                                Boolean checkPassword = db.checkPassword(Email, Password);
                        if(checkPassword==true){
                            Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Incorrect Password",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}