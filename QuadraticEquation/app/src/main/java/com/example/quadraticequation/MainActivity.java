package com.example.quadraticequation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button resBtn = (Button) findViewById(R.id.computeBtn);

        resBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeybaord(v);
                EditText aCoefficient = (EditText) findViewById(R.id.aCoeff);
                EditText bCoefficient = (EditText) findViewById(R.id.bCoeff);
                EditText cCoefficient = (EditText) findViewById(R.id.cCoeff);
                TextView resultText = (TextView) findViewById(R.id.resultText);

                Double aCoeff = getFieldValue(aCoefficient);
                Double bCoeff = getFieldValue(bCoefficient);
                Double cCoeff = getFieldValue(cCoefficient);

                if(aCoeff != null & bCoeff != null & cCoeff != null) {
                    if(aCoeff != 0.0) {
                        resultText.setText(getQuadraticResult(aCoeff, bCoeff, cCoeff));
                    } else {
                        resultText.setText(getLinearResult(bCoeff, cCoeff));
                    }
                }
            }
        });
    }

    public Double getFieldValue(EditText field) {
        String sField = field.getText().toString();
        double number;
        if(sField.matches("")) {
            return 0.0;
        }
        try {
            number = Double.parseDouble(sField);
            return number;
        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Please input a valid number", Toast.LENGTH_LONG).show();
        }
        return null;
    }

    public String getQuadraticResult(double a, double b, double c) {
        double delta = b * b - 4 * a * c;
        if(delta < 0) {
            return String.format("Function has no roots. The discriminant is negative and equals: %s", delta);
        } else if (delta == 0) {
            double res = b * (-1) / (2 * a);
            return String.format("Function has one root equal: %s. The discriminant equals: %s", res, delta);
        }
        double res1 = (b * (-1) + Math.sqrt(delta)) / (2 * a);
        double res2 = (b * (-1) - Math.sqrt(delta)) / (2 * a);
        return String.format("Function is quadratic with two roots: %s and %s. The discriminant equals: %s", res1, res2, delta);
    }

    public String getLinearResult(double b, double c) {
        if(b == 0) {
            return String.format("Function is constant and equals: %s", c);
        }
        double res = c * (-1) / b;
        return String.format("Function is linear with one solution equal: %s", res);
    }

    private void hideKeybaord(View v) { //to hide the keyboard automatically after pressing "compute"
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }
}