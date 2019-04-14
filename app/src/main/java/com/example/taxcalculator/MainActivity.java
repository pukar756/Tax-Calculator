package com.example.taxcalculator;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText etSalary;
    private TextView tvResult;
    private Button btnCalcTax, btnClear;

    private Double salary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSalary = (EditText) findViewById(R.id.etSalary);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnCalcTax = (Button) findViewById(R.id.btnTaxCalc);
        btnClear = (Button) findViewById(R.id.btnClear);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSalary.setText("");
                tvResult.setText("Tax Amount");
            }
        });

        btnCalcTax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etSalary.getText().toString())) {
                    etSalary.setError("Please Enter the Salary Amount");
                    etSalary.requestFocus();
                } else {
                    salary = Double.parseDouble(etSalary.getText().toString());
                    calcTax(salary);
                    hideKeyboard(MainActivity.this);
                }
            }
        });
    }

    //function that calculates the tax of given salary
    //and sets the tax amount value in the text view
    public void calcTax(Double salary){

        Double taxAmount = 0.00;

        //calculation of tax amount as per requirement
        if(salary <= 200000){
            taxAmount = (salary * 0.01);
        }
        else if(salary > 200000 && salary <= 350000){
            taxAmount = (200000 * 0.01 + ((salary - 200000)* 0.15));
        }
        else if(salary > 350000){
            taxAmount = (200000 * 0.01+ (150000* 0.15)+((salary - 350000)* 0.25));
            }
        tvResult.setText("Rs. " + String.valueOf(taxAmount));
        Toast.makeText(getApplicationContext(), "Rs:" + String.valueOf(taxAmount), Toast.LENGTH_SHORT).show();
    }

    //static method to hide keyboard
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}


























