package com.example.loginpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText editTextLogin,editTextPassword;
    private Button btnLog,btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        registration();
        login();
    }
    private void init(){
        btnLog = findViewById(R.id.btn_log);
        btnReg = findViewById(R.id.btn_pass);
        editTextLogin = findViewById(R.id.edit_text_login);
        editTextPassword = findViewById(R.id.edit_text_password);
    }
    private void registration(){
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editTextLogin.getText().toString().equals("")| !editTextPassword.getText().toString().equals("")){

                    try {
                        FileOutputStream fileOutputStream = openFileOutput("Login", MODE_PRIVATE);
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                        BufferedWriter bw = new BufferedWriter(outputStreamWriter);
                        bw.write(editTextLogin.getText().toString());
                        bw.close();
                        FileOutputStream OutputStream = openFileOutput("Password", MODE_PRIVATE);
                        OutputStreamWriter passOutputStreamWriter = new OutputStreamWriter(OutputStream);
                        BufferedWriter pbw = new BufferedWriter(passOutputStreamWriter);
                        pbw.write(editTextPassword.getText().toString());
                        pbw.close();
                        Toast.makeText(getApplicationContext(),"Вы зарегистрировались",Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Ошибка",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void login(){
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fileInputStream = openFileInput("Login");
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                    BufferedReader login = new BufferedReader(inputStreamReader);
                    String log = login.readLine();
                    login.close();
                    FileInputStream InputStream = openFileInput("Password");
                    InputStreamReader StreamReader = new InputStreamReader(InputStream);
                    BufferedReader password = new BufferedReader(StreamReader);
                    String pass = password.readLine();
                    password.close();
                    if(log.equals(editTextLogin.getText().toString()) & pass.equals(editTextPassword.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Добро пожаловать "+editTextLogin.getText().toString(),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Вы ввели неверные данные",Toast.LENGTH_SHORT).show();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
