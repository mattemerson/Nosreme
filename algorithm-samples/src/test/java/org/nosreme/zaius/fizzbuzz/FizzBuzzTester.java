package org.nosreme.zaius.fizzbuzz;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class FizzBuzzTester {

	@Test
	public void testSimpleFizzBuzzShouldPass()
	{
		List<Integer> inputs = new ArrayList<Integer>();
		
		FizzBuzzer fizzBuzzer = new SimpleFizzBuzzer();
		
		List<String> outputs = fizzBuzzer.process(inputs);
	}
}
