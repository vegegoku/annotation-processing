package com.progressoft.fitnesse.fixtures;

import com.progressoft.fitnesse.Calculator;

public class CalculatorFixture {

    private double firstOperand;
    private double secondOperand;

    public double add(){
        return new Calculator().add(firstOperand, secondOperand);
    }

    public double multiply(){
        return new Calculator().multiply(firstOperand,secondOperand);
    }

    public double getFirstOperand() {
        return firstOperand;
    }

    public void setFirstOperand(double firstOperand) {
        this.firstOperand = firstOperand;
    }

    public double getSecondOperand() {
        return secondOperand;
    }

    public void setSecondOperand(double secondOperand) {
        this.secondOperand = secondOperand;
    }
}
