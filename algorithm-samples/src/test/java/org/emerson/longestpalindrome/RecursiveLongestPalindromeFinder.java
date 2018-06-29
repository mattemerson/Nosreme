package org.emerson.longestpalindrome;

public class RecursiveLongestPalindromeFinder implements LongestPalindromeFinder
{
	
	/**
	 * Recursively finds the longest palindrome
	 * @param array
	 * @return
	 */
	@Override
	public String findPalindrome(String input)
	{
		// Trivial example, there is no data
		if ( (input == null) ||
		     (input.isEmpty()))
		{
			return "";
		}
		// There is only 1 piece of data
		else if (input.length() == 1)
		{
			return input;
		}
		else
		{
			// Convert into an int array
			int[] array = input.chars().toArray();
			int arrayLength = array.length;
			
			// Palindrome could be even or odd in length
			BestSolution solution = new BestSolution(0,0);
						
			for (int position=0;position<arrayLength-1;position++)
			{
				solution = findLongestEven(solution, array,position,0);
			}
							
			// Only bother processing if the length is greater than 2 (so there is an odd number)
			if (array.length > 2)
			{
				for (int position=1;position<arrayLength-1;position++)
				{
					solution = findLongestOdd(solution, array,position,1);
				}
			}
			
			return input.substring(solution.getStart(), solution.getEnd()+1);
		}
	}	
	
	/**
	 * Recursively finds the longest even length palindrom
	 * 
	 * @param array
	 * @param position
	 * @param width
	 * @return
	 */
	private BestSolution findLongestEven(BestSolution lastSolution, int[] array, int position, int width)
	{
		int startPosition = position - width;
		int endPosition = position + 1 + width;
		if ( (startPosition < 0) ||
		     (endPosition > (array.length - 1)) ||
		     (array[startPosition] != array[endPosition]) )
		{
			return lastSolution;
		}
		else {			
			lastSolution = lastSolution.returnBest(new BestSolution(startPosition, endPosition));
			return findLongestEven(lastSolution, array, position, width+1);
		}
	}
	
	/**
	 * Recursively finds the longest odd-length palindrome
	 * @param array
	 * @param position
	 * @param width
	 * @return
	 */
	private BestSolution findLongestOdd(BestSolution lastSolution, int [] array, int position, int width)
	{
		int startPosition = position-width;
		int endPosition = position+width;
		if ( (startPosition < 0) ||
		     (endPosition > (array.length - 1)) ||
		     (array[startPosition] != array[endPosition]) )
		{
			return lastSolution;
		}
		     
		else {
			lastSolution = lastSolution.returnBest(new BestSolution(startPosition, endPosition));
			return findLongestOdd(lastSolution, array, position, width+1);
		}
	}
	
	private class BestSolution
	{
		public int start;
		public int end;
		
		public BestSolution(int start, int end)
		{
			this.start=start;
			this.end=end;
		}
		
		public int getStart()
		{
			return this.start;
		}
		
		public int getEnd()
		{
			return this.end;
		}
		
		public int score()
		{
			return this.end - this.start;
		}
		
		public BestSolution returnBest(BestSolution solution)
		{
			if (solution.score() > this.score())
			{
				return solution;
			}
			return this;
		}
	}
}
