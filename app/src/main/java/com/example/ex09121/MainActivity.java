package com.example.ex09121;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * @author David Yusupov <dy3722@bs.amalnet.k12.il>
 * @version 1.0
 * @since 26/12/2025
 * A simple calculator
 */
public class MainActivity extends AppCompatActivity implements View.OnCreateContextMenuListener{

    TextView tv;
    EditText etOp1,etOp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        etOp1 = findViewById(R.id.etOp1);
        etOp2 = findViewById(R.id.etOp2);

        tv.setOnCreateContextMenuListener(this);
    }

    /**
     * Valid edit text boolean.
     *
     * @param str the str
     * @return true - if valid. else - false
     */
    public boolean validEditText(String str)
    {
        String regex = "^[+-]?(\\d+|\\d*\\.\\d+|\\d+\\.)$";
        return str.matches(regex);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Math Operations");
        menu.add("sum");
        menu.add("difference");
        menu.add("multiplication");
        menu.add("division");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (!(etOp1.getText().toString().isEmpty() || etOp2.getText().toString().isEmpty())) {
            if (validEditText(etOp1.getText().toString()) && validEditText(etOp2.getText().toString())) {
                double num1 = Double.parseDouble(etOp1.getText().toString());
                double num2 = Double.parseDouble(etOp2.getText().toString());
                String oper = item.getTitle().toString();

                if (oper.equals("sum")) {
                    tv.setText(num1+num2+"");
                }
                else if (oper.equals("difference")) {
                    tv.setText(num1-num2+"");
                }
                else if (oper.equals("multiplication")) {
                    tv.setText(num1*num2+"");
                }
                else {
                    if (num2 != 0) {
                        tv.setText(num1 / num2 + "");
                    }
                    else {
                        tv.setText("Can't division by 0");
                    }
                }
            }
            else
            {
                tv.setText("Not valid numbers!!");
            }
        }
        else
        {
            tv.setText("Can't be empty!");
        }
        return super.onContextItemSelected(item);
    }
}