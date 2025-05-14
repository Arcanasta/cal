package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CalculatorActivity extends AppCompatActivity {

    private EditText inputEditText;
    private String currentInput = "";
    private double result = 0;
    private char operator = ' ';
    private ArrayList<String> history = new ArrayList<>(); // 保存历史记录

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        inputEditText = findViewById(R.id.inputEditText);

        // 数字按钮点击事件
        setupNumberButton(R.id.btn0, "0");
        setupNumberButton(R.id.btn1, "1");
        setupNumberButton(R.id.btn2, "2");
        setupNumberButton(R.id.btn3, "3");
        setupNumberButton(R.id.btn4, "4");
        setupNumberButton(R.id.btn5, "5");
        setupNumberButton(R.id.btn6, "6");
        setupNumberButton(R.id.btn7, "7");
        setupNumberButton(R.id.btn8, "8");
        setupNumberButton(R.id.btn9, "9");
        setupNumberButton(R.id.btnDot, ".");

        // 运算符按钮点击事件
        setupOperatorButton(R.id.btnAdd, '+');
        setupOperatorButton(R.id.btnSubtract, '-');
        setupOperatorButton(R.id.btnMultiply, '*');
        setupOperatorButton(R.id.btnDivide, '/');

        // 科学计算按钮点击事件
        setupScientificButton(R.id.btnSin, "sin");
        setupScientificButton(R.id.btnCos, "cos");
        setupScientificButton(R.id.btnTan, "tan");
        setupScientificButton(R.id.btnLog, "log");
        setupScientificButton(R.id.btnLn, "ln");
        setupScientificButton(R.id.btnExp, "exp");

        // 清除按钮点击事件
        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput = "";
                result = 0;
                operator = ' ';
                inputEditText.setText("");
            }
        });

        // 等于按钮点击事件
        Button btnEqual = findViewById(R.id.btnEqual);
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.isEmpty()) {
                    double secondNumber = Double.parseDouble(currentInput);
                    result = calculate(result, secondNumber, operator);
                    DecimalFormat decimalFormat = new DecimalFormat("#.####");
                    String resultText = decimalFormat.format(result);
                    inputEditText.setText(resultText);

                    // 保存到历史记录
                    history.add(resultText);

                    currentInput = "";
                    operator = ' ';
                }
            }
        });

        // 查看历史记录按钮点击事件
        Button btnHistory = findViewById(R.id.btnHistory);
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder historyText = new StringBuilder();
                for (String record : history) {
                    historyText.append(record).append("\n");
                }
                inputEditText.setText(historyText.toString());
            }
        });

        // 单

