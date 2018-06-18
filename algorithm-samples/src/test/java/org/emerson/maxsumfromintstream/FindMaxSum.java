package org.emerson.maxsumfromintstream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FindMaxSum {

	@Test
	public void testFindMaxSumFromIntStream()
	{
		List<Integer> streamOfInts = Arrays.asList(-1,1,2,-8,3,4,-8,10);
		
		Solution solution = findSolution(streamOfInts);
		
		System.out.println(solution);
	}
	
	private Solution findSolution(List<Integer> streamOfInts)
	{
		// Prime the solution
		if ( (streamOfInts == null) ||
			     (streamOfInts.isEmpty()) )
		{
			return new Solution();
		}
		
		Integer value = streamOfInts.get(0);
		Solution solution = new Solution();
		solution.addMember(value);
		
		Solution tempSolution = new Solution();
		tempSolution.addMember(value);
		
		if (streamOfInts.size() > 2)
		{
			for (int ii=1;ii<streamOfInts.size();ii++)
			{
				value = streamOfInts.get(ii);
				
				if (value < 0)
				{
					// The number is negative
					
					// If the number drops our total solution negative, then cap our previous solution and start a new one....
					if (tempSolution.getTotal() + value < 0)
					{
						if (tempSolution.getTotal() > solution.getTotal())
						{
							solution = tempSolution;
						}
						
						// start a new one; maybe not necessary, skip adding the negative because it's better to add nothing
						tempSolution = new Solution();
						tempSolution.addMember(value);
					}
					else
					{
						// the solution is still negative, if the current solution is better then total, then use it
						if (tempSolution.getTotal() > solution.getTotal())
						{
							solution = tempSolution;
						}
						
						// Start a new tempSolution based on the old one
						tempSolution = new Solution(tempSolution);
					}
				}
				else // value is >= 0
				{
					// If our previous solution was negative, just throw it away
					// Otherwise, just add it to the solution
					if (tempSolution.getTotal() < 0)
					{
						tempSolution = new Solution();
						tempSolution.addMember(value);
					}
					else
					{
						// value >=0, it will increase the solution
						tempSolution.addMember(value);
					}
				}
			}			
		}
					
		// Replace our solution with the temporary solution
		if (tempSolution.getTotal() > solution.getTotal())
		{
			solution = tempSolution;
		}
		
		return solution;
	}
	
	
	public class Solution
	{
		private int total;
		private List<Integer> members;
		
		/**
		 * Copy Constructor
		 * @param solution
		 */
		public Solution(Solution solution)
		{
			total = solution.total;
			members = new ArrayList<Integer>(solution.members);
		}
		
		/**
		 * Zero constructor
		 */
		public Solution()
		{
			total = 0;
			members = new ArrayList<Integer>();
		}

		public int getTotal()
		{
			return this.total;
		}
		
		public int addMember(Integer value)
		{
			members.add(value);
			total+=value;
			
			return getTotal();
		}
		
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder();
			builder.append("(total='").append(getTotal()).append("', members='").append(members).append("')");
			return builder.toString();
		}
	}
}
