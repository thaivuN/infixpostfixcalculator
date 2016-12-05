/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaivun01.collections;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author Thai-Vu Nguyen
 */
@RunWith(Parameterized.class)
public class CustomQueueTest {

    @Parameters(name = "{index} plan[{0}]={1}]")
    public static Collection<Object[]> data() {
           return Arrays.asList(new Object[][]{
               {    new String[]{"20", "+", "22"}, new String[]{"1"}   },
               {    new String[]{"22", "11", "3", "5"}, new String[]{"4", "4"}  },
               {    new String[]{}, new String[]{}  },

           });
    }
    
    private CustomQueue<String> queue;
    private String[] prefilled;
    private String[] toFill;

    public CustomQueueTest(String[] prefilled, String[] toFill) {
        
        this.prefilled = prefilled;
        this.toFill = toFill;
    }

    @Before
    public void setUp() {
        this.queue = new CustomQueue();
        for (int i = 0; i < prefilled.length;i++)
            queue.add(prefilled[i]);
    }

    /**
     * Test of add method, of class CustomQueue.
     */
    @Test
    public void testAdd() {
        int currentSize = queue.size();
        int toAddSize = toFill.length;
        int expectedSize = currentSize + toAddSize;
        
        for (int i = 0; i < toFill.length; i++)
            queue.add(toFill[i]);
        
        int realSize = queue.size();
        
        assertEquals(expectedSize, realSize);
    }

    /**
     * Test of remove method, of class CustomQueue.
     */
    @Test
    public void testRemove() {
        if (queue.isEmpty()){
            try{
                String testRemove = queue.remove();
                
                fail();
            }catch (ArrayIndexOutOfBoundsException eae){
                
            }
            
        }
        else{
            String peek = queue.peek();
            String removed = queue.remove();
            
            assertEquals(peek, removed);
        }
    }

    /**
     * Test of isEmpty method, of class CustomQueue.
     */
    @Test
    public void testIsEmpty() {
        boolean expectedEmptiness;
        if (prefilled.length == 0){
            expectedEmptiness = true;
        }
        else{
            expectedEmptiness = false;
        }
        
        assertEquals(queue.isEmpty(), expectedEmptiness);
    }

    /**
     * Test of size method, of class CustomQueue.
     */
    @Test
    public void testSize() {
        int expected =  prefilled.length;
        int real = queue.size();
        
        assertEquals(expected, real);
    }

    /**
     * Test of peek method, of class CustomQueue.
     */
    @Test
    public void testPeek() {
        if (prefilled.length > 0){
            String expectedPeek = prefilled[0];
            String realPeek = queue.peek();
            
            assertEquals(expectedPeek, realPeek);
        }
    }

}
