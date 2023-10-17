package com.artem.calculator.models;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculationComponents {

    private Long id = 0L;

    private BigDecimal firstNumber = BigDecimal.ZERO;

    private BigDecimal secondNumber = BigDecimal.ZERO;

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
        result = firstNumber.divide(secondNumber, 6, RoundingMode.HALF_EVEN);
    }
}
