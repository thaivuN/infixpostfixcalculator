
package com.thaivun01.evaluation;

import com.thaivun01.collections.CustomQueue;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *The main testing class for making sure the PostFixCalculator works
 * 
 * @author MDThai
 */
@RunWith(Parameterized.class)
public class PostFixCalculatorTest {
    
    @Parameterized.Parameters(name = "{index} plan[{0}]={1}]")
    public static Collection<Object[]> data() {
           return Arrays.asList(new Object[][]{
               {  new String[]{"1", "+", "2"}, "3" },
               {  new String[]{"1", "/", "2"}, "0.5"  },
               {  new String[]{"1", "/", "3"}, "0.3333333333333333" },
               {  new String[]{"1", "/", "3", "*", "3"}, "1"  },
               {  new String[] {"2", "/", "7" , "+", "4"}, "4.285714285714286"},
               {  new String[]{"3", "-", "3", "+", "5"}, "5"  },
               {  new String[]{"4", "+","6", "*", "8"}, "52"  },
               {  new String[]{"42","-","32","/","2","+","9","*","32","*","(","3","-","135",")"}, "-37990"  },
               {  new String[]{"3","/","100","+","(","399","-","403",")"},"-3.97"  },
               {  new String[]{"544","/","4","+","2","/","2","+","32","/","3"}, "147.66666666666666" },
               {  new String[]{"2", "*", "(" , "8" , "+", "9", ")"}, "34"  },
               {  new String[]{"(", "2", "+", "3", ")", "*","4"}, "20"  },
               {  new String[]{"32","*","32","-","32","/","23","-","(","1","/","32","+","32","*","(","32","/","3","/","3",")",")"}, "908.7996678743962"   },
               {  new String[]{"(","2", "+", "3", ")", "*", "(", "4", "+", "9", ")"}, "65"  },
               {  new String[]{"(","4","*","9","+","4", "*", "(", "13", "+", "4", ")", ")", "+", "2"}, "106"  },
               {  new String[]{"(","4","-","(","11","*","2",")",")","/","4"}, "-4.5"  },
               {  new String[]{"(","8","-","(","11","*","2",")","*","(","3","+","15",")",")","/","4"}, "-97"  },
               {  new String[]{"0","/","0"}, "NaN"  },
               {  new String[]{"2","/","325","*","(","32","-","536",")","+","31","+","42","-","20","+","(","32","-","42",")"}, "39.89846153846153"  },
               {  new String[]{"3","+","(","4","+","4","/","(","4","-","42","*","(","4","/","4",")",")",")"}, "6.894736842105264"  },
                   
           });
    }
    
    private PostFixCalculator calculator;
    String[] infix;
    String expectedAnswer;
    private CustomQueue<String> infixQueue;
    
    public PostFixCalculatorTest(String[] infix, String expectedAnswer) {
        this.infix = infix;
        this.expectedAnswer = expectedAnswer;
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
    @Test
    public void testCalculate() {
        CustomQueue<String> result = calculator.calculate(infixQueue);
        
        assertEquals(Double.parseDouble(expectedAnswer),Double.parseDouble(result.peek()), 1e-15);
        
    }
    
}
