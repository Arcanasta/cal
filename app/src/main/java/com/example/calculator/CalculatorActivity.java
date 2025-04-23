package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_title;
    private EditText et_show;
    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_plus;
    private Button btn_minus;
    private Button btn_times;
    private Button btn_divided;
    private Button btn_equal;
    private Button btn_point;
    private Button btn_clear;
    private int flag = 0;
    private String text1 = "0", text2 = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        Intent intent = getIntent();
        String title = intent .getStringExtra("username")+"的计算器";
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText(title);
        et_show = findViewById(R.id.et_show);
        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_times = findViewById(R.id.btn_times);
        btn_divided = findViewById(R.id.btn_divided);
        btn_equal = findViewById(R.id.btn_equal);
        btn_point = findViewById(R.id.btn_point);
        btn_clear = findViewById(R.id.btn_clear);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_times.setOnClickListener(this);
        btn_divided.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_0:
                et_show.append("0");
                break;
            case R.id.btn_1:
                et_show.append("1");
                break;
            case R.id.btn_2:
                et_show.append("2");
                break;
            case R.id.btn_3:
                et_show.append("3");
                break;
            case R.id.btn_4:
                et_show.append("4");
                break;
            case R.id.btn_5:
                et_show.append("5");
                break;
            case R.id.btn_6:
                et_show.append("6");
                break;
            case R.id.btn_7:
                et_show.append("7");
                break;
            case R.id.btn_8:
                et_show.append("8");
                break;
            case R.id.btn_9:
                et_show.append("9");
                break;
            case R.id.btn_plus:
                flag = 1;
                text1 = et_show.getText().toString();
                et_show.setText("");
                break;
            case R.id.btn_minus:
                flag = 2;
                text1 = et_show.getText().toString();
                et_show.setText("");
                break;
            case R.id.btn_times:
                flag = 3;
                text1 = et_show.getText().toString();
                et_show.setText("");
                break;
            case R.id.btn_divided:
                flag = 4;
                text1 = et_show.getText().toString();
                et_show.setText("");
                break;
            case R.id.btn_point:
                et_show.append(".");
                break;
            case R.id.btn_equal:
                switch(flag){
                    case 0:
                        et_show.append("0");
                        break;
                    case 1:
                        text2 = et_show.getText().toString();
                        Double res1 = Double.parseDouble(text1) + Double.parseDouble(text2);
                        et_show.setText(res1 + "");
                        break;
                    case 2:
                        text2 = et_show.getText().toString();
                        Double res2 = Double.parseDouble(text1) - Double.parseDouble(text2);
                        et_show.setText(res2 + "");
                        break;
                    case 3:
                        text2 = et_show.getText().toString();
                        Double res3 = Double.parseDouble(text1) * Double.parseDouble(text2);
                        et_show.setText(res3 + "");
                        break;
                    case 4:
                        text2 = et_show.getText().toString();
                        Double res4 = Double.parseDouble(text1) / Double.parseDouble(text2);
                        et_show.setText(res4 + "");
                        break;
                    default:
                        break;
                }
                break;
            case R.id.btn_clear:
                flag = 0;
                text1 = "0";
                text2 = "0";
                et_show.setText("");
                break;
            default:
                break;
        }
    }
}