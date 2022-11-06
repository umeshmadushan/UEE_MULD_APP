package com.example.ueeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {

    EditText rename,remobile,reemail,repassword,regrepassword;
    Button register;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rename = (EditText) findViewById(R.id.regNameinput);
        remobile = (EditText) findViewById(R.id.regmobilnuminput);
        reemail = (EditText) findViewById(R.id.regemailinput);
        repassword = (EditText) findViewById(R.id.regpassinput);
        regrepassword = (EditText) findViewById(R.id.regrepassinput);
        register = (Button) findViewById(R.id.registerbtn);
        DB = new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = rename.getText().toString();
                String mobile = remobile.getText().toString();
                String email = reemail.getText().toString();
                String password = repassword.getText().toString();
                String repassword = regrepassword.getText().toString();

                if (name.equals("")||email.equals("")||mobile.equals("")||password.equals("")||repassword.equals(""))
                    Toast.makeText(register.this, "Please Enter ", Toast.LENGTH_SHORT).show();
                else {
                    if (password.equals(repassword)){
                        Boolean checkuser = DB.checkemail(email);
                        if (checkuser==false){
                            Boolean insert = DB.insertData(name,mobile, email,password);
                            if (insert==true){
                                Toast.makeText(register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Login.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(register.this, "Regitration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(register.this, "User Already Exists Please Sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(register.this, "Password Not Matched", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}