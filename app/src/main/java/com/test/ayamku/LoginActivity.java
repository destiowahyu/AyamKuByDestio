package com.test.ayamku;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin,btnRegis;
    private EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        username = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.pass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegis = (Button) findViewById(R.id.btnRegis);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString(); //ambil dr android
                String pass = password.getText().toString();
                SharedPreferences prefer =
                        getSharedPreferences("MYDATA",MODE_PRIVATE);
                String username = prefer.getString("user",null); //dari prefer MYDATA
                String password = prefer.getString("pass",null);
                System.out.println("User : "+user+" = "+username + ", dan pass = "+pass+"="+
                        password);
                if ((username != null && password != null) && (username.equals(user) &&
                        password.equals(pass)))
                { //username and password are present, do your stuff
                    Intent iMain = new Intent(LoginActivity.this,
                            MainActivity.class);
                    startActivity(iMain);
                }else
                {       Toast.makeText(getApplicationContext(),
                        "Username / Password salah, silahkan register",
                        Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iRegis = new Intent(LoginActivity.this,
                        RegisterActivity.class);startActivity(iRegis);
                finish();
            }
        });
    }
}
