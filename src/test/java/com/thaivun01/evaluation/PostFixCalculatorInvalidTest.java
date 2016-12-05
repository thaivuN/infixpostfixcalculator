package com.thaivun01.evaluation;

import com.thaivun01.collections.CustomQueue;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
/**
 *The main testing class for making sure the PostFixCalculator catches the invalid infix input
 * @author Thai-Vu Nguyen
 */
@RunWith(Parameterized.class)
public class PostFixCalculatorInvalidTest {
    @Parameterized.Parameters(name = "{index} plan[{0}]={1}]")
    public static Collection<Object[]> data() {
           return Arrays.asList(new Object[][]{
               {    new String[]{"12","+","3","/","+","2"}   },
               {    new String[]{"2","*",")","3","/","2"}   },
               {    new String[]{")", "3", "+", "4"}   },
               {    new String[]{"3","+","3","12","*","2"}   },
               {    new String[]{"23", "+", "(", ")", "*", "42"}    },
               {    new String[]{"2", "+", "32", "*", "(", "23", "+", "(", "1", "/", "4", ")"}    }
           });
    }
    
   private PostFixCalculator calculator;
   private String[] infix;
    private CustomQueue<String> infixQueue;
    
    public PostFixCalculatorInvalidTest(String[] infix){
        this.infix = infix;
        this.calculator = new PostFixCalculator();
    }
    
    /**
     * Set up method.
     */
    @Before
    public void setUp() {
       this.infixQueue = new CustomQueue();
        for (String input : infix) {
            infixQueue.add(input);
        }
    }
    
    /**
     * Test of calculate method, of class PostFixCalculator.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testCalculate() {
        CustomQueue<String> result = calculator.calculate(infixQueue);
                
    }
    
}
