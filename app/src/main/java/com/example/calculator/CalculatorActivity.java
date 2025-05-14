package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class CalculatorActivity extends AppCompatActivity {

    private EditText inputEditText;
    private String currentInput = "";
    private double result = 0;
    private char operator = ' ';


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
                if (!currentInput.isEmpty() && operator != ' ') {
                    double secondNumber = Double.parseDouble(currentInput);
                    result = calculate(result, secondNumber, operator);
                    DecimalFormat decimalFormat = new DecimalFormat("#.####");
                    inputEditText.setText(decimalFormat.format(result));
                    currentInput = "";
                    operator = ' ';
                }
            }
        });
    }

    private void setupNumberButton(int buttonId, final String number) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput += number;
                inputEditText.setText(currentInput);
            }
        });
    }

    private void setupOperatorButton(int buttonId, final char op) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.isEmpty()) {
                    if (operator == ' ') {
                        result = Double.parseDouble(currentInput);
                    } else {
                        double secondNumber = Double.parseDouble(currentInput);
                        result = calculate(result, secondNumber, operator);
                    }
                    currentInput = "";
                    operator = op;
                    inputEditText.setText(result + String.valueOf(operator));
                }
            }
        });
    }

    private double calculate(double num1, double num2, char op) {
        switch (op) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    return 0; // 处理除数为零的情况
                }
            default:
                return num2;
        }
    }
}