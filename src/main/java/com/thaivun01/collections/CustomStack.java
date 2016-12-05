package com.thaivun01.collections;

/**
 * Custom stack.
 * 
 * @author Thai-Vu Nguyen
 */
public class CustomStack<T> {


  private int pointer;
  private T[] stack;
  
  /**
   * Default constructor. By default, the stack will start with a size of 10
   */
  public CustomStack(){
      this(10);
  }
    
  /**
   * Constructor for the stack taking in an initial size. 
   * @param size String
   */
  public CustomStack(int size){
      this.pointer = -1;
      this.stack = (T[]) new Object[size];
  }
  
  /**
   * Returns the element at the top of the stack. Does not remove it.
   * @return The element at the top of the stack
   * @throws ArrayIndexOutOfBoundsException is thrown if the method is called when the stack is empty
   */
  public T peek(){
      if (pointer == -1)
          throw new ArrayIndexOutOfBoundsException();
      return stack[pointer];
  }
  
  /**
   * Pops the element at the top of the stack.
   * @return The element at the top of the stack
   * @throws ArrayIndexOutOfBoundsException is thrown if the method is called when the stack is empty
   */
  public T pop(){
      if (pointer == -1)
          throw new ArrayIndexOutOfBoundsException();
      return stack[pointer--];
  }
  
  /**
   * Pushes an element into the top of the stack
   * @param t T
   */
  public void push(T t){
      if (pointer == stack.length - 1){
          stack = getResize(stack.length * 2);
      }
      
      stack [++pointer] = t; 
  }
  
  /**
   * Checks if the stack is empty
   * @return 
   */
  public boolean empty(){
      return this.pointer == -1;
  }
  
  /**
   * Resizes the stack.
   * @param size int
   * @return A resized stack
   */
  private T[] getResize(int size){
      T[] newElements = (T[]) new Object[size];
      for (int i = 0; i <= pointer; i++){
          newElements[i] = stack[i];
      }
      return newElements;
  }
}
