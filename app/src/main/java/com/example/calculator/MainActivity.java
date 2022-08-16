package com.example.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    MyViewModel viewmodel;
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


        FragmentManager frag = getSupportFragmentManager();
        FragmentTransaction ftrans = frag.beginTransaction();
        CalculationFragment fragcalc = new CalculationFragment();
        ftrans.add(R.id.fragmentContainerView,fragcalc);
        ftrans.commit();

        viewmodel = new ViewModelProvider(this).get(MyViewModel.class);
        viewmodel.init();

    }

    @Override
    protected void onStart() {
        super.onStart();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // binding the buttons with its functions :

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

//    Saved Instances of the first and second numbers of the operation the live data to represent on the textview and the result and if the change in orintation happened
//    within a running operation or not

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArray("LiveData List",viewmodel.getmessege().getValue().toArray(new String[0]));

        outState.putBoolean("Is in op",calc.isInOP());

        outState.putDouble("Num1",calc.getNum1());
        outState.putDouble("Num2",calc.getNum2());
        outState.putString("op",calc.getOperation());
        outState.putDouble("res",calc.getResult());

        Log.d("Calc_tag", "onSaveInstanceState: "+viewmodel.getmessege().getValue().toArray(new String[0]));
    }


//    the return of the saved instances

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        viewmodel.sendMessegeNumebers(savedInstanceState.getStringArray("LiveData List")[0],0);
        viewmodel.sendMessegeNumebers(savedInstanceState.getStringArray("LiveData List")[1],1);
        viewmodel.sendMessegeNumebers(savedInstanceState.getStringArray("LiveData List")[2],2);
        calc.setInOP(savedInstanceState.getBoolean("Is in op"));
        calc.setNum1(savedInstanceState.getDouble("Num1"));
        calc.setNum2(savedInstanceState.getDouble("Num2"));
        calc.setOperation(savedInstanceState.getString("op"));
        calc.setResult(savedInstanceState.getDouble("res"));
        Log.d("Calc_tag", "onSaveInstanceState: "+viewmodel.getmessege().getValue().get(0));
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
                bindToTvCalculation(calc.getNumRepresntation());
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
                bindToTvCalculation(calc.getNumRepresntation());
            }
        }
    };

// ======================================================================================
//    the Implementation of the numbers buttons adding them to the text view :
// ======================================================================================

    View.OnClickListener numClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            clearRes();
            Button btn = (Button) view;

//            handling the condition of having multiple 0's in the first of th eTextView
            if (viewmodel.getmessege().getValue().equals("0") && btn.getText().toString().equals("0")) {
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
                clearHint();
            }
            bindToTvCalculation(calc.getNumRepresntation());

            Log.d("Calc_tag","1 :"+ calc.getNum1());
            Log.d("Calc_tag","2 :"+calc.getNum2());
            Log.d("Calc_tag","Numbers : "+calc.getNumbers());
            calc.setDone(false);

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
                    clearAll();
                    calc.setInOP(false);
                    break;

                case R.id.btn_Back:
//                    Handling the back if the length is Zero stop or the app crashes as the code
//                    will make substring from 0 to -1

                    if (!(calc.isNumbersEmpty())){
                        calc.removeLastNumber();

                        bindToTvCalculation(calc.getNumRepresntation());

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
                bindToTvCalculation(clicked.getText().toString());
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
                    bindToTvCalculation("");
                    calc.setDone(true);
                    calc.setResult(result);
                }
            }
        }
    };



// ======================================================================================
//    the implementation of the Memory operations :
// ======================================================================================

    View.OnClickListener memBtnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button btn = (Button) view;
            switch (btn.getText().toString()){
                // memory clear
                case "mc":
                    calc.setMemory(0);
                    Log.d("Calc_tag","memory contains : " +Double.toString(calc.getMemory()));
                    break;

//                    memory recall the only case that interacts with the Textview of the operands

                case "mr":
                    if (calc.isInOP()){
                        calc.setNum2(calc.getMemory());
                        showHint();
                    }else {
                        calc.setNum1(calc.getMemory());
                    }
                    calc.setNumbers(Double.toString(calc.getMemory()));
                    Log.d("Calc_tag","memory contains : " +Double.toString(calc.getMemory()));
                    bindToTvCalculation(calc.getNumRepresntation());
                    bindToTvResult("");
                    break;

//                    memory add adds the number of the first operation to the original memory value (Default = 0)

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
                        bindToTvCalculation("");
                    }
                    break;

//                    memory subtract subtracts the number of the first operation to the original memory value (Default = 0)

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
                        bindToTvCalculation("");
                    }
                    break;
            }


        }
    };


// ======================================================================================
//    Bind with the Calculation TextView  :
// ======================================================================================

    //    sending a string value to the livedata to represent it in the fragment :
    //    Text View of the calculations:
    private void bindToTvCalculation(String numbers) {
        viewmodel.sendMessegeNumebers(numbers, 0);
    }
    //    Text View of the Hints:
    private void bindToTvHint(String numbers) {
        viewmodel.sendMessegeNumebers(numbers,1);
    }
    //    Text View of the results:
    private void bindToTvResult(String numbers) {
        viewmodel.sendMessegeNumebers(numbers,2);
    }
    //    Clears all the Text Views :
    private void clearAll()
    {
        viewmodel.sendMessegeNumebers("",0);
        viewmodel.sendMessegeNumebers("",1);
        viewmodel.sendMessegeNumebers("",2);
    }
    private void clearRes  (){viewmodel.sendMessegeNumebers("",2);}
    private void clearHint (){viewmodel.sendMessegeNumebers("",1);}
    private void clearCalc (){viewmodel.sendMessegeNumebers("",0);}

//    checks if the user divides by zero in the hint part so it sends a warning to the user
    private void showHint (){
        if (calc.getNum2()==0 && calc.getOperation().equals("\u00F7")){
            Toast.makeText(MainActivity.this,"Error : Cannot divide by zero",Toast.LENGTH_SHORT).show();
        }else {
            bindToTvHint(Double.toString(calc.operate()));
        }
    }


}