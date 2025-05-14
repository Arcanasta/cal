package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 启动登录界面
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);

        // 结束当前 MainActivity
        finish();
    }
}
