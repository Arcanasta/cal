package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_user_name, et_psw, et_pswAgain;
    private RadioGroup rg_gender;
    private Button btn_register;

    private String userName, psw, pswAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    public void init() {
        et_user_name = findViewById(R.id.et_user_name);
        et_psw = findViewById(R.id.et_psw);
        et_pswAgain = findViewById(R.id.et_pswAgain);
        rg_gender = findViewById(R.id.rg_gender);
        btn_register = findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getString();
                int sex;
                int sexChosenId = rg_gender.getCheckedRadioButtonId();
                switch (sexChosenId) {
                    case R.id.girl:
                        sex = 0;
                        break;
                    case R.id.boy:
                        sex = 1;
                        break;
                    default:
                        sex = -1;
                        break;
                }

                // 验证输入
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(RegisterActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(psw)) {
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(pswAgain)) {
                    Toast.makeText(RegisterActivity.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                } else if (sex < 0) {
                    Toast.makeText(RegisterActivity.this, "请选择性别", Toast.LENGTH_SHORT).show();
                } else if (!psw.equals(pswAgain)) {
                    Toast.makeText(RegisterActivity.this, "输入密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        // 对密码进行加密
                        String hashedPassword = hashPassword(psw);

                        // 保存用户信息
                        SharedPreferences sharedPreferences = getSharedPreferences("SEND", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putString(userName, hashedPassword);
                        edit.apply();

                        // 提示注册成功并跳转到登录页面
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        intent.putExtra("UserName", userName);
                        intent.putExtra("psw", hashedPassword); // 传递加密后的密码
                        startActivity(intent);
                        RegisterActivity.this.finish();
                    } catch (NoSuchAlgorithmException e) {
                        Toast.makeText(RegisterActivity.this, "密码加密失败，请重试", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }

            private void getString() {
                userName = et_user_name.getText().toString().trim();
                psw = et_psw.getText().toString().trim();
                pswAgain = et_pswAgain.getText().toString().trim();
            }

            // 哈希密码的方法
            private String hashPassword(String password) throws NoSuchAlgorithmException {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(password.getBytes());
                return Base64.encodeToString(hash, Base64.DEFAULT).trim();
            }
        });
    }
}
