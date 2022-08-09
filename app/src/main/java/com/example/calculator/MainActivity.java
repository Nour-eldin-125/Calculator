package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.calculator.databinding.ActivityMainBinding;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    String numbers = "0";
    NumberFormat numFormat;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        numFormat = new DecimalFormat("0.#####E0");

    }

    private void checkNumber (String num){
        int count = num.length();
        if (num.contains(".") || num.contains("-"))
            count--;
        if (count>10){
            numbers = (numFormat.format(Double.parseDouble(numbers))).toString();
            bindToTV(numbers);
        }
        else {
            bindToTV(num);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();


        binding.btn0.setOnClickListener(btnClicked);
        binding.btn1.setOnClickListener(btnClicked);
        binding.btn2.setOnClickListener(btnClicked);
        binding.btn3.setOnClickListener(btnClicked);
        binding.btn4.setOnClickListener(btnClicked);
        binding.btn5.setOnClickListener(btnClicked);
        binding.btn6.setOnClickListener(btnClicked);
        binding.btn7.setOnClickListener(btnClicked);
        binding.btn8.setOnClickListener(btnClicked);
        binding.btn9.setOnClickListener(btnClicked);



        binding.btnClr.setOnClickListener(clrMethodsClickec);
        binding.btnBack.setOnClickListener(clrMethodsClickec);


// ======================================================================================
//        ========================== Handling Sign Button ===============================
// ======================================================================================
        binding.btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Handling the sign toggling if the number is Integer or Double:

                if (!(numbers.equals("0") || numbers.length() == 0)){
                    if (numbers.contains(".")){
                        double value = Double.parseDouble(numbers);
                        value *= -1;
                        numbers = Double.toString(value);
                        bindToTV(numbers);
                    }
                    else {
                        int value = Integer.parseInt(numbers);
                        value *= -1;
                        numbers = Integer.toString(value);
                        bindToTV(numbers);
                    }
                }
            }
        });

// ======================================================================================
//        =========================== Handling Point Button =============================
// ======================================================================================

        binding.btnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (numbers.contains(".")) {
//                        Do Nothing
                } else {
                    numbers += ".";
                    bindToTV(numbers);
                }

            }
        });

    }


// ======================================================================================
//    the Implementation of the numbers buttons adding them to the text view :
// ======================================================================================

    View.OnClickListener btnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button btn = (Button) view;


//            handling the condition of having multiple 0's in the first of th eTextView
            if(numbers.equals("0")){
                numbers = btn.getText().toString();
            }
            else{
                numbers += btn.getText().toString();
            }
            checkNumber(numbers);
        }
    };

// ======================================================================================
//    the Implementation of the Clear button and the Back button :
// ======================================================================================

    View.OnClickListener clrMethodsClickec = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

//            getting the button clicked whether Clear button or Back button:

            switch (view.getId()){
                case R.id.btn_Clr:
                    numbers = "0";
                    bindToTV("");
                    break;

                case R.id.btn_Back:

//                    Handling the back if the length is Zero stop or the app crashes as the code
//                    will make substring from 0 to -1

                    if (numbers.length()==0){

                            }else {
                                numbers = numbers.substring(0,numbers.length()-1);
                        checkNumber(numbers);
                    }
                    break;

            }
        }
    };

// ======================================================================================
//    the implementation of the Basic operations  + - / * :
// ======================================================================================

    View.OnClickListener opClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO Add the basic operations of the Calculator :
        }
    };

    private void bindToTV(String numbers) {
        binding.tvCalculations.setText(numbers);
    }
}