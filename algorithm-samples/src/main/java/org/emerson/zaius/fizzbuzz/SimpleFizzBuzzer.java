package org.emerson.zaius.fizzbuzz;

import java.util.ArrayList;
import java.util.List;

public class SimpleFizzBuzzer implements FizzBuzzer
{

	@Override
	public List<String> process(int low, int high)
	{
		if (low > high)
		{
			throw new IllegalArgumentException("low=" +low + "must <= high=" + high);
		}
		
		List<String> outputs = new ArrayList<String>();
		
		
			for(int ii=low;ii<=high;ii++)
			{
				// Technically not the most elegant because you could do the fizz check and then the buzz check
				// and then drop through into the case if you didn't find anything
				if ((ii % 5)==0 && (ii % 3)==0)
				{
					outputs.add("FizzBuzz");
				}
				else if ((ii % 3)==0)
				{
					outputs.add("Fizz");
				}
				else if ((ii % 5)==0)
				{
					outputs.add("Buzz");
				}
				else
				{
					outputs.add(Integer.toString(ii));
				}
			
		}
		
		return outputs;
	}

}
