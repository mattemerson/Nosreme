package org.emerson.lambda;

public class Calculator {
		  
	    interface IntegerMath {
	        int operation(int a, int b);   
	    }
	  
	    public int operateBinary(int a, int b, IntegerMath op) {
	        return op.operation(a, b);
	    }
	
}
