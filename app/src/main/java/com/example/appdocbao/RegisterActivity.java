package com.example.appdocbao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,32}" +               //at least 4 characters
                    "$");

    private static final Pattern NAME_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=\\S+$)" +           //no white spaces
                    ".{0,32}" +               //at least 4 characters
                    "$");

    private static final Pattern NUMBERPHONE_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +
                    "(?=\\S+$)" +
                    ".{10}" +
                    "$");

    SQLHelperUser sqlHelperUser;
    TextInputLayout edtEmailR, edtPasswordR, edtNameR, edtSDTR;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtEmailR = findViewById(R.id.edtEmailR);
        edtPasswordR = findViewById(R.id.edtPassWordR);
        edtNameR = findViewById(R.id.edtHoTenR);
        edtSDTR = findViewById(R.id.edtSDTR);
        btnRegister = findViewById(R.id.btnRegister);

        sqlHelperUser = new SQLHelperUser(getBaseContext());

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmailR.getEditText().getText().toString().trim();
                String password = edtPasswordR.getEditText().getText().toString().trim();
                String name = edtNameR.getEditText().getText().toString().trim();
                String numberPhone = edtSDTR.getEditText().getText().toString().trim();
                if(!validateEmailR()| !validateHoTenR()| !validatePasswordR()| !validateSDTR()){
                    return;
                }else {
                    sqlHelperUser.insertNewUser(email, password, name, numberPhone);
                    Toast.makeText(getBaseContext(), "Bạn đã đăng ký thành công"+ email, Toast.LENGTH_LONG).show();
                    Intent intentRegister = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intentRegister);
                }
            }
        });

    }

    private boolean validateEmailR(){
        String email = edtEmailR.getEditText().getText().toString().trim();

        if(email.isEmpty()){
            edtEmailR.setError("Trường Email là bắt buộc.");
            return false;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtEmailR.setError("Trường Email sai định dạng.");
            return false;
        } else {
            edtEmailR.setError(null);
            return true;
        }
    }

    private boolean validatePasswordR(){
        String password = edtPasswordR.getEditText().getText().toString().trim();

        if(password.isEmpty()){
            edtPasswordR.setError("Trường Mật khẩu là bắt buộc.");
            return false;
        }else if(!PASSWORD_PATTERN.matcher(password).matches()){
            edtPasswordR.setError("Trường Mật khẩu sai định dạng.");
            return false;
        } else{
            edtPasswordR.setError(null);
            return true;
        }
    }

    private boolean validateHoTenR(){
        String name = edtNameR.getEditText().getText().toString().trim();

        if(name.isEmpty()){
            edtNameR.setError("Trường Họ tên là bắt buộc.");
            return false;
        } else if(!NAME_PATTERN.matcher(name).matches()){
            edtNameR.setError("Trường Họ tên sai định dạng.");
            return false;
        } else {
            edtNameR.setError(null);
            return true;
        }
    }

    private boolean validateSDTR(){
        String numberPhone = edtSDTR.getEditText().getText().toString().trim();

        if(numberPhone.isEmpty()){
            edtSDTR.setError("Trường Số điện thoại là bắt buộc.");
            return false;
        }else if (!NUMBERPHONE_PATTERN.matcher(numberPhone).matches()){
            edtSDTR.setError("Trường Số điện thoại sai định dạng.");
            return false;
        } else{
            edtSDTR.setError(null);
            return true;
        }
    }
}
