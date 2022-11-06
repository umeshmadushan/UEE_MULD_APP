package com.example.ueeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText lgemail,lgpassword;
    Button lglogin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lgemail = (EditText) findViewById(R.id.loginemailinput);
        lgpassword = (EditText) findViewById(R.id.loginpassinput);
        lglogin = (Button) findViewById(R.id.loginbtn);
        DB =new DBHelper(this);

        lglogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = lgemail.getText().toString();
                String password = lgpassword.getText().toString();

                if (email.equals("")||password.equals("")){
                    Toast.makeText(Login.this, "Please Fill All Fields", Toast.LENGTH_LONG).show();
                }
                else{
                    Boolean chechuserpass = DB.checkemailpassword(email,password);
                    if (chechuserpass==true){
                        Toast.makeText(Login.this, "Sign inSuccessfully", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}