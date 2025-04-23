
package com.example.calculator;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText et_user_name,et_psw;
    private TextView tv_register;
    private Button btn_login;
    private String userName,psw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }
    public void init(){
        et_user_name = findViewById(R.id.et_user_name);
        et_psw = findViewById(R.id.et_psw);
        btn_login = findViewById(R.id.btn_login);
        tv_register = findViewById(R.id.tv_register);
        Intent intent = getIntent();
        String username = intent.getStringExtra("userName");
        String password= intent.getStringExtra("psw");
        if(!TextUtils.isEmpty(username)&& !TextUtils.isEmpty(password)){
            et_user_name.setText(username);
            et_psw.setText(password);
        }
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = et_user_name.getText().toString().trim();
                psw = et_psw.getText().toString().trim();
                SharedPreferences sharedPreferences = getSharedPreferences("SEND",MODE_PRIVATE);
                Map<String,?> map = sharedPreferences.getAll();
                if (usernameISValid(map,userName) && passwordISValid(map,psw)){
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    LoginActivity.this.finish();
                    Intent intent = new Intent(LoginActivity.this,CalculatorActivity.class);
                    intent.putExtra("userName",userName);
                    startActivity(intent);
                }
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, com.example.calculator.RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean passwordISValid(Map<String,?> map, String psw) {
        if(TextUtils.isEmpty(psw)){
            Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
        }else {
            for(Map.Entry<String,?> m : map.entrySet()){
                if (m.getValue().equals(psw)){
                    return  true;
                }
            }
            Toast.makeText(LoginActivity.this, "密码有误", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private boolean usernameISValid(Map<String,?> map, String userName) {
        if(TextUtils.isEmpty(userName)){
            Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
        }else {
            for (Map.Entry<String,?> m :map.entrySet()){
                if(m.getKey().equals(userName)){
                    return true;
                }
            }
            Toast.makeText(LoginActivity.this, "您未注册", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
