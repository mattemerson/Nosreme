package org.emerson.lambda;

import org.emerson.lambda.Calculator.IntegerMath;
import org.junit.Test;

public class CalculatorTest {

	/**
	 * THis is an example of me specifying my own functional interface and then using my implementations of it
	 */
	@Test
	public void testCalculatorShouldPass() {
	    
        Calculator myApp = new Calculator();
        IntegerMath addition = (a, b) -> a + b;
        IntegerMath subtraction = (a, b) -> a - b;
        System.out.println("40 + 2 = " +
            myApp.operateBinary(40, 2, addition));
        System.out.println("20 - 10 = " +
            myApp.operateBinary(20, 10, subtraction));    
    }
}
