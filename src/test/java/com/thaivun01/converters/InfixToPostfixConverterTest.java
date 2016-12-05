package com.thaivun01.converters;

import com.thaivun01.collections.CustomQueue;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Test class to see check if the conversion from Infix to Postfix works properly
 * 
 * @author Thai-Vu Nguyen
 */
@RunWith(Parameterized.class)
public class InfixToPostfixConverterTest {
    
    @Parameterized.Parameters(name = "{index} plan[{0}]={1}]")
    public static Collection<Object[]> data() {
           return Arrays.asList(new Object[][]{
               {  new String[]{"1", "+", "2"}, new String[]{"1", "2", "+"} },
               {  new String[]{"3", "-", "3", "+", "5"}, new String[]{"3", "3", "-", "5", "+"}  },
               {  new String[]{"4", "+","6", "*", "8"}, new String[]{"4", "6", "8", "*", "+"}  },
               {  new String[]{"2", "*", "(" , "8" , "+", "9", ")"}, new String[]{"2", "8", "9", "+", "*"}  },
               {  new String[]{"(", "2", "+", "3", ")", "*","4"}, new String[]{"2", "3", "+", "4", "*"}  },
               {  new String[]{"(","2", "+", "3", ")", "*", "(", "4", "+", "9", ")"}, new String[]{"2", "3", "+", "4", "9", "+", "*"}  },
               {  new String[]{"(","4","*","9","+","4", "*", "(", "13", "+", "4", ")", ")", "+", "2"}, new String[]{"4", "9","*","4","13","4","+","*","+","2","+"}  }
                   
           });
    }
    
    private InfixToPostfixConverter converter;
    private CustomQueue<String> infixQueue;
    private String[] infix;
    private String[] expectedPostFix;
    
    public InfixToPostfixConverterTest(String[] infix, String[] expectedPostFix) {
   
        this.converter = new InfixToPostfixConverter();
        this.infix = infix;
        this.expectedPostFix = expectedPostFix;
    }
    
    @Before
    public void setUp() {
            this.infixQueue = new CustomQueue();
            for (int i = 0; i < infix.length; i++)
                infixQueue.add(infix[i]);
    }

    /**
     * Test of convertToPostFix method, of class InfixToPostfixConverter.
     */
    @Test
    public void testConvertToPostFix() {
        CustomQueue<String> postfixResults = converter.convertToPostFix(infixQueue);
        
        String[] realPostFix = new String[postfixResults.size()];
        for (int i = 0; i < realPostFix.length;i++)
            realPostFix[i] = postfixResults.remove();
        
        assertArrayEquals(expectedPostFix, realPostFix);
    }
    
   
    
}
