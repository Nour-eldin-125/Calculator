package com.example.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    Calculator calc;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        calc = new Calculator();
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    @Override
    protected void onStart() {
        super.onStart();

        binding.btn0.setOnClickListener(numClicked);
        binding.btn1.setOnClickListener(numClicked);
        binding.btn2.setOnClickListener(numClicked);
        binding.btn3.setOnClickListener(numClicked);
        binding.btn4.setOnClickListener(numClicked);
        binding.btn5.setOnClickListener(numClicked);
        binding.btn6.setOnClickListener(numClicked);
        binding.btn7.setOnClickListener(numClicked);
        binding.btn8.setOnClickListener(numClicked);
        binding.btn9.setOnClickListener(numClicked);

        binding.btnEql.setOnClickListener(eqlClicked);

        binding.btnClr.setOnClickListener(clrMethodsClicked);
        binding.btnBack.setOnClickListener(clrMethodsClicked);

        binding.btnMul.setOnClickListener(opClicked);
        binding.btnDiv.setOnClickListener(opClicked);
        binding.btnAdd.setOnClickListener(opClicked);
        binding.btnSub.setOnClickListener(opClicked);

        binding.btnSign.setOnClickListener(signClicked);

        binding.btnPoint.setOnClickListener(pointClicked);

        binding.btnMemAdd.setOnClickListener(memBtnClicked);
        binding.btnMemSub.setOnClickListener(memBtnClicked);
        binding.btnMemRecall.setOnClickListener(memBtnClicked);
        binding.btnMemClr.setOnClickListener(memBtnClicked);



    }


// ======================================================================================
//        ========================== Handling Sign Button ========================
// ======================================================================================

    View.OnClickListener signClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

//                Handling the sign toggling if the number is Integer or Double:
            if (!(calc.getNumbers().isEmpty()) || calc.getNumbers().equals("0") ){
                calc.appendSign();
                if (calc.isInOP()){
                    calc.getNum2FromNumbers();
                    showHint();
                }else {
                    calc.getNum1FromNumbers();
                }
                bindToTvCalc(calc.getNumRepresntation());
            }
        }
    };



// ======================================================================================
//        =========================== Handling Point Button ========================
// ======================================================================================
//
    View.OnClickListener pointClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!(calc.getNumbers().contains("."))) {
                calc.appendNumbers(".");
                bindToTvCalc(calc.getNumRepresntation());
            }
        }
    };

// ======================================================================================
//    the Implementation of the numbers buttons adding them to the text view :
// ======================================================================================

    View.OnClickListener numClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (binding.tvResults.getText().toString().isEmpty()) {}
            else {bindToTvResult("");}

            Button btn = (Button) view;

//            handling the condition of having multiple 0's in the first of th eTextView
            if (binding.tvCalculations.getText().toString().equals("0") && btn.getText().toString().equals("0")) {
                calc.setNumbers("0");
            }
            else {
                calc.appendNumbers(btn.getText().toString());
            }
            if (calc.isInOP()) {
                calc.getNum2FromNumbers();
                showHint();
            } else {
                calc.getNum1FromNumbers();
            }
            bindToTvCalc(calc.getNumRepresntation());

            Log.d("Calc_tag","1 :"+ calc.getNum1());
            Log.d("Calc_tag","2 :"+calc.getNum2());
            Log.d("Calc_tag","Numbers : "+calc.getNumbers());
            calc.setDone(false);
            bindToTvResult("");

        }

    };

// ======================================================================================
//    the Implementation of the Clear button and the Back button :
// ======================================================================================

    View.OnClickListener clrMethodsClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

//            getting the button clicked whether Clear button or Back button:
            switch (view.getId()){
                case R.id.btn_Clr:
                    calc.clearNumbers();
                    bindToTvCalc("");
                    bindToTvHint("");
                    bindToTvResult("");
                    calc.setInOP(false);
                    break;

                case R.id.btn_Back:
//                    Handling the back if the length is Zero stop or the app crashes as the code
//                    will make substring from 0 to -1

                    if (!(calc.isNumbersEmpty())){
                       calc.removeLastNumber();

                       bindToTvCalc(calc.getNumRepresntation());

                       if (calc.isInOP()) {
                           if (calc.isNumbersEmpty()) {
                               calc.setNum2(0);
                               bindToTvHint("");
                           } else {
                               calc.getNum2FromNumbers();
                               showHint();
                           }
                       }
                       else{
                           if (calc.isNumbersEmpty()) {
                               calc.setNum1(0);
                           }
                           else
                               calc.getNum1FromNumbers();
                       }
                   }
            }
        }
    };

// ======================================================================================
//    the implementation of the Basic operations  + - / * :
// ======================================================================================

    View.OnClickListener opClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!(calc.isInOP())) {
                if (calc.isDone()){
                    calc.setNum1(calc.getResult());
                }
                calc.clearNumbers();
                Button clicked = (Button) view;
                calc.setOperation(clicked.getText().toString());
                bindToTvCalc(clicked.getText().toString());
                calc.setInOP(true);
                Log.d("Calc_tag",calc.getOperation());

            }
        }
    };

// ======================================================================================
//    the implementation of the Equal operations :
// ======================================================================================

    View.OnClickListener eqlClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (calc.isInOP()){
                if (calc.getNum2()==0 && calc.getOperation().equals("\u00F7")){
                    Toast.makeText(MainActivity.this,"Error : Cannot divide by zero",Toast.LENGTH_SHORT).show();
                }else {
                    double result = calc.operate();
                    bindToTvResult(Double.toString(result));
                    bindToTvHint("");
                    Log.d("Calc_tag",Double.toString(calc.operate()));
                    calc.clearAll();
                    calc.setInOP(false);
                    bindToTvCalc("");
                    calc.setDone(true);
                    calc.setResult(result);
                }
            }
        }
    };


    View.OnClickListener memBtnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button btn = (Button) view;
            switch (btn.getText().toString()){
                case "mc":
                    calc.setMemory(0);
                    Log.d("Calc_tag","memory contains : " +Double.toString(calc.getMemory()));
                    break;
                case "mr":
                    if (calc.isInOP()){
                        calc.setNum2(calc.getMemory());
                        showHint();
                    }else {
                        calc.setNum1(calc.getMemory());
                    }
                    calc.setNumbers(Double.toString(calc.getMemory()));
                    Log.d("Calc_tag","memory contains : " +Double.toString(calc.getMemory()));
                    bindToTvCalc(calc.getNumRepresntation());
                    bindToTvResult("");
                    break;
                case "m+":
                    if (!(calc.isInOP())){
                        if (calc.isDone()){
                            calc.setMemory(calc.add(calc.getResult(),calc.getMemory()));
                        }
                        else {
                            calc.setMemory(calc.add(calc.getNum1(),calc.getMemory()));
                        }
                        calc.setNumbers(Double.toString(calc.getMemory()));
                        bindToTvResult(calc.getNumRepresntation());
                        Log.d("Calc_tag","memory contains : " +Double.toString(calc.getMemory()));

                        calc.clearAll();
                        bindToTvCalc("");
                    }
                    break;
                case "m-":
                    if (!(calc.isInOP())) {
                        if (calc.isDone()){
                            calc.setMemory(calc.add(calc.getResult(),calc.getMemory()));
                        }
                        else {
                            calc.setMemory(calc.sub(calc.getMemory(), calc.getNum1()));
                        }
                        calc.setNumbers(Double.toString(calc.getMemory()));
                        bindToTvResult(calc.getNumRepresntation());
                        Log.d("Calc_tag", "memory contains : " + Double.toString(calc.getMemory()));
                        calc.clearAll();
                        bindToTvCalc("");
                    }
                    break;
            }


        }
    };


// ======================================================================================
//    Bind with the Calculation TextView  :
// ======================================================================================

    private void bindToTvCalc(String numbers) {
        binding.tvCalculations.setText(numbers);
    }

    private void bindToTvHint(String numbers) {
        binding.tvHints.setText(numbers);
    }

    private void bindToTvResult(String numbers) {
        binding.tvResults.setText(numbers);
    }

    private void showHint (){
        if (calc.getNum2()==0 && calc.getOperation().equals("\u00F7")){
            Toast.makeText(MainActivity.this,"Error : Cannot divide by zero",Toast.LENGTH_SHORT).show();
        }else {
            bindToTvHint(Double.toString(calc.operate()));
        }
    }


}