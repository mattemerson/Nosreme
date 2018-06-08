package org.emerson.zaius.fizzbuzz;

import java.util.ArrayList;
import java.util.List;

import org.emerson.zaius.fizzbuzz.FizzBuzzer;
import org.emerson.zaius.fizzbuzz.SimpleFizzBuzzer;
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
