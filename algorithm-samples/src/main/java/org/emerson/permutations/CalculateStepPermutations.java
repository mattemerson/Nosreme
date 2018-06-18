package org.emerson.permutations;

/**
 * Given a stair case of whatever size, calculate the number of different combination of steps it would take to go down it if you can take 1,2,3 steps
 * @author MEmerson
 *
 */
public class CalculateStepPermutations
{

	public static void main(String[] args)
	{
		CalculateStepPermutations stepPermutter = new CalculateStepPermutations();
		
		int numberOfSteps = 30;
		int numberOfStepPermutations = stepPermutter.calculateTotalStepPermutations(numberOfSteps);
		
		System.out.println("number of steps=" + numberOfSteps);
		System.out.println("number of step permutations=" + numberOfStepPermutations);
	}
	
	private int calculateTotalStepPermutations(int numberOfStairs)
	{
		int totalSteps = step(0, numberOfStairs);
		return totalSteps;		
	}
	
	private int step(int stepSize, int numberOfStairsRemaining)
	{
		int remainder = numberOfStairsRemaining - stepSize;
		if (remainder == 0)
		{
			// On the bottom step, good solution!
			return 1;
		}
		else if (remainder < 0)
		{
			// stepped passed the end...bad!
			return 0;
		}
		else 
		{
			// keep stepping
			return step(1, remainder) + step(2, remainder) + step(3, remainder);
		}
			
	}
}
