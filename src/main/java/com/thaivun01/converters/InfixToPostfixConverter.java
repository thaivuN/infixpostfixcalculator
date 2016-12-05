package com.thaivun01.converters;

import com.thaivun01.collections.CustomQueue;
import com.thaivun01.collections.CustomStack;

/**
 *  Class handling the conversion of infix queues to postfix queues
 * 
 * @author Thai-Vu Nguyen
 */
public class InfixToPostfixConverter {
    
    /**
     * Default constructor.
     */
    public InfixToPostfixConverter(){
    }
    
    /**
     * Converts an infix queue into a postfix queue
     * @param infixQueue CustomQueue<String>
     * @return A postfix queue
     */
    public  CustomQueue<String> convertToPostFix(CustomQueue<String> infixQueue) {
        
        CustomStack<String> operatorStack =new CustomStack();
        
        CustomQueue<String> postFix = new CustomQueue();
        
        //Counters for the parenthesis
        int leftParenthesisCount = 0;
        int rightParenthesisCount = 0;
        //Variable to store the previous value
        String previousValue = "";
        
        while (infixQueue.isEmpty() == false){
            
            String value = infixQueue.remove();
            
            //Validate the value
            if (validateValue (value, previousValue) == false)
                throw new IllegalArgumentException();
            
            if (isOperator(value)){
                //Here, we have an operator
                
                if (operatorStack.empty()){
                    //Automatically add the operator to the stack if the stack is empty
                    //If the operator is a (, increment leftParenthesisCount
                    if (value.equals("("))
                        leftParenthesisCount++;
                    
                    operatorStack.push(value);
                } 
                else{
                    
                    if (value.equals("(")){
                        leftParenthesisCount++;
                        //Add the parenthesis
                        operatorStack.push(value);
                    }
                   else if (value.equals(")")){
                       rightParenthesisCount++;
                        //Pop everything until we can find the opening (
                        while (operatorStack.peek().equals("(") == false)
                        {
                            postFix.add(operatorStack.pop());
                           
                        }
                        //Flush out the (
                        operatorStack.pop();
                    }
                    else if (precedence(value) <= precedence(operatorStack.peek())){
                        //Current operator has smaller or same precendence of the operator at the top of the stack.
                        //Pop everything in the stack until an operator with smaller precendence 
                        //than the current one is found on the stack.
                        //Push those operators into the postfix queue.
                        //Pop the current operator into the stack.
                        while(operatorStack.empty() == false && precedence(value) <= precedence(operatorStack.peek()))
                            postFix.add(operatorStack.pop());
                        operatorStack.push(value);
                    }
                    else{
                        //Add the operator into the stack
                        operatorStack.push(value);
                    }
                }
            }
            else{
                //Add the operand into the queue
                postFix.add(value);
            }
            
            //Save the current value
            previousValue = value;
            
        }
        
        //Check if the number of left hand parenthesis matches with the right hand ones.
        if (leftParenthesisCount != rightParenthesisCount){
            throw new IllegalArgumentException();
        }
        
        //Get the remaining operators in the stack
        while(operatorStack.empty() == false){
            postFix.add(operatorStack.pop());
        }

        return postFix;
    }
    
    /**
     * Returns a precendence value of an operator
     * @param operator String
     * @return an integer value
     */
    private int precedence(String operator) {
        switch (operator){
            case "(":
            case ")":
                return 0;
            case "*":
            case "/":
                return 2;
            case "+":
            case "-":
                return 1;
            default:
                throw new IllegalArgumentException();
        }
        
    }

    /**
     * Validates if the string value is a basic operator.
     * Valid operators: * / - + ()
     * @param value String
     * @return true if the value is a valid operator
     */
    private boolean isOperator(String value) {
        return value.equals("*") || value.equals("/") || value.equals("-") || value.equals("+") || value.equals("(") || value.equals(")");
    }
    
    /**
     * Checks if the value is numeric.
     * @param value String
     * @return true if the value numeric
     */
    private boolean isNumeric(String value){
        try{
            double attempt = Double.parseDouble(value);
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }
    }
    
    /**
     * Checks if the current value is valid
     * @param currentValue String
     * @param previousValue String
     * @return True if the current value is valid
     */
    private boolean validateValue(String currentValue, String previousValue){
        if (currentValue == null || currentValue.isEmpty())
            return false;
        //By assuring the currentValue is not empty, 
        //the only valid time previousValue is empty is at the beginning of the operation
        if (previousValue.isEmpty()){
         
            //Logically, this should be the first value of the infix queue
            //Therefore, we should allow a ( or a numeric value
            return (currentValue.equals("(") || isNumeric(currentValue));
               
        }
        
        //At this point, we have a non-empty previousValue
        
        //We now check what the current value is
        if (currentValue.equals("(")){
          //The previous value for a '(' should be an operator that is not ')'
          if(isOperator(previousValue) == false || previousValue.equals(")") || isNumeric(previousValue))
              return false;
        }
        else if (currentValue.equals(")")){
            //The previous value for a ')' can either be numeric, ')''
            if(!(previousValue.equals(")"))){
                if (isNumeric(previousValue) == false)
                    return false;
            }
        }
        else if (isOperator(currentValue)){
            //Logically, the operator is not '(' or ')'
            //Previous value should either be ')' or numeric
            if ( !(previousValue.equals(")") || isNumeric(previousValue) ) ){
                if (isOperator(previousValue))
                    return false;
            }
        }
        else if (isNumeric(currentValue)){
            //Allow only operators that is not ')'
            if (isNumeric(previousValue))
                return false;
            if (previousValue.equals(")"))
                return false;
            if (isOperator(previousValue) == false)
                return false;
        }
        else{
            //Neither an operator or numeric
            //Invalid
            return false;
        }
        
        //Passed validation rules
        return true;
        
    }
    
 
    
}
