package org.emerson.tree;

/**
 * Possibly this should be replaced with OptionalInt
 * @author memerson
 *
 */
public class IntSolution
{
	private boolean hasSolution = false;
	private Integer value;
	
	public IntSolution(Integer value, boolean hasSolution)
	{
		this.value = value;
		this.hasSolution = hasSolution;
	}
	
	public Integer getValue()
	{
		return value;
	}
	
	public boolean hasSolution()
	{
		return hasSolution;
	}
	
	public static IntSolution newNoSolution()
	{
		return new IntSolution(0,false);
	}
	
	public static IntSolution newSolution(Integer value)
	{
		return new IntSolution(value, true);
	}
	
	public static IntSolution chooseBestSolution(IntSolution first, IntSolution second)
	{
		return IntSolution.max(first, second);
	}
	
	public static IntSolution max(IntSolution ...solutions)
	{
		IntSolution maxValue = IntSolution.newNoSolution();
		for (IntSolution solution : solutions)
		{
			// If this is a viable solution, process it
			if (solution.hasSolution())
			{
				// If maxValue has a possible solution, evaluate them against each other
				if (maxValue.hasSolution())
				{
					// See if this candidate is bigger
					if (solution.getValue() > maxValue.getValue())
					{
						maxValue = solution;
					}
				}
				// Otherwise, just assign our new solution
				else
				{
					maxValue = solution;
				}
			}			
		}
		
		return maxValue;
	}
	
	public static IntSolution add(IntSolution ...solutions)
	{		
		IntSolution runningTotal = null;
		for (IntSolution solution : solutions)
		{
			// Initialize...
			if (runningTotal == null)
			{
				runningTotal = solution;				
			}
			// Otherwise, try to add...
			else
			{
				// Add them up
				if (solution.hasSolution())
				{
					runningTotal = IntSolution.newSolution(runningTotal.getValue() + solution.getValue());
				}
				else
				{
					return IntSolution.newNoSolution();
				}
			}
			
			// Can't combine solution as we have a null so just return that
			if (!runningTotal.hasSolution())
			{
				return IntSolution.newNoSolution();
			}
		}
		return runningTotal;
	}
	
	@Override
	public String toString()
	{
		return "(hasSolution='" + hasSolution() + "', sum='" + getValue() + "')";
	}
}
