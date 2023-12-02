package com.artem.calculator.models;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class CalculationComponents {

    private Long id = 0L;

    private BigDecimal firstNumber = BigDecimal.ZERO;

    private BigDecimal secondNumber = BigDecimal.ZERO;

    private BigDecimal thirdNumber = BigDecimal.ZERO;

    private BigDecimal fourthNumber = BigDecimal.ZERO;

    private BigDecimal result = BigDecimal.ZERO;



    public CalculationComponents() {
    }

    public CalculationComponents(String firstNumber, String secondNumber, String operation) {

        this.firstNumber = new BigDecimal(firstNumber);
        this.secondNumber = new BigDecimal(secondNumber);
        switch (operation){
            case "sum" -> sumResult();
            case "subtraction" -> subtractionResult();
            case "multiplication" -> multiplicationResult();
            case "division" -> divisionResult();
        }
    }

    public CalculationComponents(String firstNumberString, String secondNumberString,
                                 String thirdNumberString, String fourthNumberString, String[] operations) {
        firstNumber = new BigDecimal(firstNumberString);
        secondNumber = new BigDecimal(secondNumberString);
        thirdNumber = new BigDecimal(thirdNumberString);
        fourthNumber = new BigDecimal(fourthNumberString);

        result = operationResult(secondNumber, thirdNumber, operations[1]);
        if (operations[2].equals("multiplication") || operations[2].equals("division")){
            result = operationResult(result, fourthNumber, operations[2]);
            result = operationResult(firstNumber, result, operations[0]);
        } else {
            result = operationResult(firstNumber, result, operations[0]);
            result = operationResult(result, fourthNumber, operations[2]);
        }
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(BigDecimal firstNumber) {
        this.firstNumber = firstNumber;
    }

    public BigDecimal getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(BigDecimal secondNumber) {
        this.secondNumber = secondNumber;
    }

    public BigDecimal getResult() {
        return result;
    }

    public String getFormattedResult(){
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');
        symbols.setDecimalSeparator('.');
        DecimalFormat dfDecimal = new DecimalFormat("#0.##########");
        dfDecimal.setDecimalFormatSymbols(symbols);
        dfDecimal.setGroupingSize(3);
        dfDecimal.setGroupingUsed(true);
        return dfDecimal.format(new BigDecimal(result.stripTrailingZeros().toString()));
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public void sumResult(){
        result = firstNumber.add(secondNumber);
    }
    public void subtractionResult(){
        result = firstNumber.subtract(secondNumber);
    }
    public void multiplicationResult(){
        result = firstNumber.multiply(secondNumber);
    }
    public void divisionResult(){
        result = firstNumber.divide(secondNumber, 6, RoundingMode.HALF_UP);
    }

    private BigDecimal operationResult(BigDecimal firstNumber, BigDecimal secondNumber, String operation){
        BigDecimal result = BigDecimal.ZERO;
        switch (operation){
            case "sum" -> result = firstNumber.add(secondNumber);
            case "subtraction" -> result = firstNumber.subtract(secondNumber);
            case "multiplication" -> result = firstNumber.multiply(secondNumber);
            case "division" -> result = firstNumber.divide(secondNumber, 10, RoundingMode.HALF_UP);
        }
        return result;
    }
}
