package com.example.appdocbao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    ArrayList<User> listUsers;
    SQLHelperUser sqlHelperUser;

    TextView btnIntentR;
    TextInputLayout edtEmail, edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassWord);
        btnLogin = findViewById(R.id.btnLogin);
        btnIntentR = findViewById(R.id.btnIntentR);

        sqlHelperUser = new SQLHelperUser(getBaseContext());

        listUsers = sqlHelperUser.getAllUserAdvanced();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getEditText().getText().toString().trim();
                String password = edtPassword.getEditText().getText().toString().trim();
                if(!validateEmail()| !validatePassword()){
                    return;
                }
                if (checkLogin(email, password)){
                    edtPassword.setError(null);
                    Toast.makeText(getBaseContext(), "Đăng nhập thành công!!!", Toast.LENGTH_LONG).show();
                    Intent intentLogin = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intentLogin);
                } else{
                   edtPassword.setError("Tài khoản và mật khẩu không chính xác.");
                }
            }
        });

        btnIntentR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentR = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentR);
            }
        });
    }

    private boolean checkLogin(String email, String password){
        boolean checkUser = false;
        for(int i=0; i<listUsers.size(); i++){
            if(email.equals(listUsers.get(i).getEmail()) && password.equals(listUsers.get(i).getPassword())){
                checkUser = true;
            }
        }
        return checkUser;
    }

    private boolean validateEmail(){
        String email = edtEmail.getEditText().getText().toString().trim();

        if(email.isEmpty()){
            edtEmail.setError("Bạn chưa nhập Email");
            return false;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtEmail.setError("Sai định dạng");
            return false;
        } else {
            edtEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String password = edtPassword.getEditText().getText().toString().trim();

        if (password.isEmpty()) {
            edtPassword.setError("Trường Mật khẩu là bắt buộc.");
            return false;
        } else {
            edtPassword.setError(null);
            return true;
        }
    }
}
