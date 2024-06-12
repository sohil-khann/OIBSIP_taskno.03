package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvInput;
    private String input = "";
    private String operator = "";
    private double num1 = 0;
    private double num2 = 0;
    private boolean isNewOp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInput = findViewById(R.id.tvInput);

        Button btnClear = findViewById(R.id.btnClear);
        Button btnDelete = findViewById(R.id.btnDelete);
        Button btnDivide = findViewById(R.id.btnDivide);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnSubtract = findViewById(R.id.btnSubtract);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnEquals = findViewById(R.id.btnEquals);
        Button btnDecimal = findViewById(R.id.btnDecimal);
        Button btn00 = findViewById(R.id.btn00);
        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);

        View.OnClickListener listener = v -> {
            Button b = (Button) v;
            input += b.getText().toString();
            tvInput.setText(input);
        };
        btn00.setOnClickListener(listener);
        btn0.setOnClickListener(listener);
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
        btn4.setOnClickListener(listener);
        btn5.setOnClickListener(listener);
        btn6.setOnClickListener(listener);
        btn7.setOnClickListener(listener);
        btn8.setOnClickListener(listener);
        btn9.setOnClickListener(listener);
        btnDecimal.setOnClickListener(listener);

        btnClear.setOnClickListener(v -> {
            input = "";
            operator = "";
            num1 = 0;
            num2 = 0;
            isNewOp = true;
            tvInput.setText("0");
        });

        btnDelete.setOnClickListener(v -> {
            if (!input.isEmpty()) {
                input = input.substring(0, input.length() - 1);
                tvInput.setText(input.isEmpty() ? "0" : input);
            }
        });

        btnAdd.setOnClickListener(v -> handleOperator("+"));
        btnSubtract.setOnClickListener(v -> handleOperator("-"));
        btnMultiply.setOnClickListener(v -> handleOperator("*"));
        btnDivide.setOnClickListener(v -> handleOperator("/"));

        btnEquals.setOnClickListener(v -> {
            if (!input.isEmpty()) {
                num2 = Double.parseDouble(input);
                double result = 0;
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            tvInput.setText("Error");
                            return;
                        }
                        break;
                }
                tvInput.setText(String.valueOf(result));
                input = String.valueOf(result);
                isNewOp = true;
            }
        });
    }

    private void handleOperator(String op) {
        if (isNewOp) {
            num1 = Double.parseDouble(input);
            input = "";
            operator = op;
            isNewOp = false;
        } else {
            num2 = Double.parseDouble(input);
            switch (operator) {
                case "+":
                    num1 += num2;
                    break;
                case "-":
                    num1 -= num2;
                    break;
                case "*":
                    num1 *= num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        num1 /= num2;
                    } else {
                        tvInput.setText("Error");
                        return;
                    }
                    break;
            }
            tvInput.setText(String.valueOf(num1));
            input = "";
            operator = op;
        }
    }
}
