package com.thaivun01.evaluation;

import com.thaivun01.collections.CustomQueue;
import com.thaivun01.collections.CustomStack;
import com.thaivun01.converters.InfixToPostfixConverter;

/**
 *Class that handles the calculation logic
 * 
 * @author Thai-Vu Nguyen
 */
public class PostFixCalculator {


    /**
     * Default Constructor.
     */
    public PostFixCalculator() {
    }
    
    /**
     * Calculator logic taking in an infix queue and returns a queue with one value inside (the answer).
     * @param infixQueue CustomQueue<String>
     * @return The answer of the infix operation
     */
    public CustomQueue<String> calculate (CustomQueue<String> infixQueue){
        InfixToPostfixConverter converter = new InfixToPostfixConverter();
        
        CustomQueue<String> postFix = converter.convertToPostFix(infixQueue);
        CustomQueue<String> result = evaluatePostFix(postFix);
        return result;
    }
    
    /**
     * Evaluates the postfix queue
     * @param postfix CustomQueue<String>
     * @return The answer of the postfix operation
     */
    private CustomQueue<String> evaluatePostFix(CustomQueue<String>postfix){
        CustomStack<String> numberStack = new CustomStack();;
        CustomQueue<String> result = new CustomQueue();
        
        while (postfix.isEmpty() == false){
            String value = postfix.remove();
            if (isOperator(value)){
                //First popped value should be on the right hand side of the operator
                double val2 = Double.parseDouble(numberStack.pop());
                //Second popped value should be on the left hand side of the operator
                double val1 = Double.parseDouble(numberStack.pop());
                String operation_result = calculate(val1, val2, value);
                numberStack.push(operation_result);
            }
            else{
                //It should be a number
                numberStack.push(value);
            }
        }
        
        if (numberStack.empty())
            result.add("0");
        else
            result.add(numberStack.pop());
        
        return result;
    }
    
    /**
     * Checks if the string value is an operator
     * @param value String
     * @return boolean
     */
    private boolean isOperator(String value) {
        return value.equals("*") || value.equals("/") || value.equals("-") || value.equals("+") || value.equals("(") || value.equals(")");
    }
    
    /**
     * Evaluate a basic operation
     * @param num1 double
     * @param num2 double
     * @param operator String
     * @return The result of the operation
     */
    private String calculate (double num1, double num2, String operator){
        
        double value;
        switch(operator){
            case "+":
                value = num1 + num2;
                break;
            case "-":
                value = num1 - num2;
                break;
            case "*":
                value = num1 * num2;
                break;
            case "/":
                value = num1/num2;
                break;
            default:
                throw new IllegalArgumentException();
                
        }
        String result = value + "";
        return result;
    }


}
