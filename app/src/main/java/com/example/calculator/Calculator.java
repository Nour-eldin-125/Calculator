package com.example.calculator;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Calculator {
    private String numbers;
    private String backUpNum1,backUpNum2;
    private NumberFormat numFormat;
    private NumberFormat eFormat;
    private double num1,num2;
    private String operation;
    private boolean inOP;
    private boolean isDoubleNum1,isDoubleNum2;
    private boolean numpPositve;
    private double result;
    private boolean done;
    private double memory;




    public Calculator() {
        numFormat = new DecimalFormat("#.######");
        eFormat = new DecimalFormat("0.#####E0");
        numbers = "";
        memory = 0;
        done = false;
        operation = "";
        numpPositve = true;
        inOP = false;
    }

    public double add (double first,double second){
        return first + second;
    }
    public double sub (double first,double second){
        return first - second;
    }
    public double mul (double first,double second){
        return first * second;
    }
    public double div (double first,double second){
        return first / second;
    }

    public double addNums (){
        return num1 + num2;
    }
    public double subNums (){
        return num1 - num2;
    }
    public double mulNums (){
        return num1 * num2;
    }
    public double divNums (){
        return num1 / num2;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getNumFormat(double d) {
        return numFormat.format(d).toString();
    }

    public void setNumFormat(NumberFormat numFormat) {
        this.numFormat = numFormat;
    }

    public double getNum1() {
        return num1;
    }

    public void setNum1(double num1) {
        this.num1 = num1;
    }

    public String getNumRepresntation() {
        return checkNumber(numbers);
    }
    public String getRepresntation(String num) {
        return checkNumber(num);
    }

    public double getNum2() {
        return num2;
    }

    public void setNum2(double num2) {
        this.num2 = num2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public boolean isInOP() {
        return inOP;
    }

    public void setInOP(boolean inOP) {
        this.inOP = inOP;
    }

    public boolean isDoubleNum1() {
        return isDoubleNum1;
    }

    public void setDoubleNum1(boolean doubleNum1) {
        isDoubleNum1 = doubleNum1;
    }

    public boolean isDoubleNum2() {
        return isDoubleNum2;
    }

    public void setDoubleNum2(boolean doubleNum2) {
        isDoubleNum2 = doubleNum2;
    }

    public boolean isNumpPositve() {
        return numpPositve;
    }

    public void setNumpPositve(boolean numpPositve) {
        this.numpPositve = numpPositve;
    }


    public String formatNumber (String num){
        return numFormat.format(Double.parseDouble(num));
    }

    public void seteFormat(NumberFormat eFormat) {
        this.eFormat = eFormat;
    }

    public String getBackUpNum2() {
        return backUpNum2;
    }

    public void setBackUpNum2(String backUpNum2) {
        this.backUpNum2 = backUpNum2;
    }

    public String getBackUpNum1() {
        return backUpNum1;
    }

    public void setBackUpNum1(String backUpNum1) {
        this.backUpNum1 = backUpNum1;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public double getMemory() {
        return memory;
    }


    public void setMemory(double memory) {
        this.memory = memory;
    }

    public void appendNumbers (String num){
        if (numbers.equals("0")){
            if (num.equals(".")) {numbers += num;}
            else {numbers = num;}
        }
        else
            numbers += num;
    }

    public void appendSign(){
        if(isNumpPositve()){
            numbers = "-"+numbers;
            numpPositve = false;
        }else {
            numbers = numbers.substring(1,numbers.length());
            numpPositve = true;
        }
    }

    public void clearNumbers(){
        numbers="";
    }

    public void clearAll(){
        numbers="";
        num1=0;
        num2=0;
    }

    public void getNum1FromNumbers (){
        if (numbers.equals("-") || numbers.equals(".")){
            num1 = 0;
        }else {
            num1 = Double.parseDouble(numbers);
        }
    }
    public void getNum2FromNumbers () {
        if (numbers.equals("-") || numbers.equals(".")) {
            num2 = 0;
        } else {
            num2 = Double.parseDouble(numbers);
        }
    }
    public void removeLastNumber(){
        if (numbers.length()==1){
            numbers="";
        }
        else if (numbers.contains("E")){
            Double num = Double.parseDouble(numbers);
            numbers = Double.toString(num);
            numbers = numbers.substring(0,numbers.length()-1);
        }
        else{
            numbers = numbers.substring(0,numbers.length()-1);
        }
    }


    public boolean isNumbersEmpty(){
        return numbers.isEmpty();
    }


    private String checkNumber (String numbers){
        String num = numbers;
        int count = num.length();
        if (num.contains(".") || num.contains("-"))
            count--;
        if (count>16){
            num = (eFormat.format(Double.parseDouble(num))).toString();
            return num;
        }
        else {
            return num;
        }
    }

    public Double operate () {
        double result = 0 ;
        String op = operation;
        switch (op){
            case "+":
                result=addNums();
                break;
            case "-":
                result =subNums();
                break;
//              Multiply
            case "\u0078":
                result = mulNums();
                break;
//              Divide
            case "\u00F7":
                if (num2 == 0){
                }else{
                    result = divNums();
                }
                break;
        }
        return result;
    }


}
