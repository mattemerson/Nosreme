package org.emerson.zaius.fizzbuzz;

import java.util.ArrayList;
import java.util.List;

public class SimpleFizzBuzzer implements FizzBuzzer
{

	@Override
	public List<String> process(List<Integer> inputs)
	{
		List<String> outputs = new ArrayList<String>();
		
		if ((inputs != null) && (!inputs.isEmpty()))
		{
			for(Integer input : inputs)
			{
				// Technically not the most elegant because you could do the fizz check and then the buzz check
				// and then drop through into the case if you didn't find anything
				if ((input % 5)==0 && (input % 3)==0)
				{
					outputs.add("FizzBuzz");
				}
				else if ((input % 5)==0)
				{
					outputs.add("Fizz");
				}
				else if ((input % 3)==0)
				{
					outputs.add("Buzz");
				}
				else
				{
					outputs.add(Integer.toString(input));
				}
			}
		}
		
		return outputs;
	}

}
