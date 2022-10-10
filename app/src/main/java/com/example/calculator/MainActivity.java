package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView process_textView, result_textView;
    MaterialButton button_c, button_open_bracket, button_close_bracket, button_divide,
    button_7, button_8, button_9, button_multiply, button_4, button_5, button_6, button_add,
    button_1, button_2, button_3, button_subtract, button_all_clear, button_0,
    button_dot, button_equal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result_textView = findViewById(R.id.result_textView);
        process_textView = findViewById(R.id.process_textView);

        assignId(button_0, R.id.button_0);
        assignId(button_1, R.id.button_1);
        assignId(button_2, R.id.button_2);
        assignId(button_3, R.id.button_3);
        assignId(button_4, R.id.button_4);
        assignId(button_5, R.id.button_5);
        assignId(button_6, R.id.button_6);
        assignId(button_7, R.id.button_7);
        assignId(button_8, R.id.button_8);
        assignId(button_9, R.id.button_9);
        assignId(button_c, R.id.button_c);
        assignId(button_all_clear, R.id.button_all_clear);
        assignId(button_add, R.id.button_add);
        assignId(button_subtract, R.id.button_subtract);
        assignId(button_multiply, R.id.button_multiply);
        assignId(button_divide, R.id.button_divide);
        assignId(button_open_bracket, R.id.button_open_bracket);
        assignId(button_close_bracket, R.id.button_close_bracket);
        assignId(button_dot, R.id.button_dot);
        assignId(button_equal, R.id.button_equal);
    }

    public void assignId(MaterialButton button, int id) {
        button = findViewById(id);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = process_textView.getText().toString();

        if (buttonText.equals("AC")) {
            process_textView.setText("");
            result_textView.setText("0");
            return;
        }
        else if (buttonText.equals("=")) {
            process_textView.setText(result_textView.getText());
            return;
        }
        else if (buttonText.equals("C")){
            //clear the last character of the text
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length()-1);
        }else {
            dataToCalculate = dataToCalculate + buttonText;
        }

        process_textView.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);
        if (!finalResult.equals("Error")) {
            result_textView.setText(finalResult);
        }
    }

    public String getResult(String data) {
        try {
         Context context = Context.enter();
         context.setOptimizationLevel(-1);
         Scriptable scriptable = context.initSafeStandardObjects();
         String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
         if (finalResult.endsWith(".0")) {
            finalResult = finalResult.replace(".0", "");
         }
            return finalResult;
        }catch (Exception e) {
            return "Error";
        }
    }


}