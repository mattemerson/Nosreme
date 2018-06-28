package org.emerson.sort.radix;

import java.util.Arrays;

/**
 * Radix Sort
 * Stolen from: https://www.geeksforgeeks.org/radix-sort/
 * @author memerson
 *
 */
public class RadixSort {

	public int[] sort(int[] input)
	{
		if ((input == null) || (input.length == 1))
		{
			return input;
		}
		
		// Find the maximum number to know the number of digits; this drives the d calculation
		int maxNumber = getMax(input, input.length);
		
		// Do counting sortt for every digit.
		// NOTE: instead of passing a digit number, exp is passed where exp is 10^i where i is the current digit number
		for (int exp = 1; maxNumber/exp > 0; exp *=10)
		{
			countSort(input, input.length, exp);
		}
		
		return input;
	}
	
	/**
	 * Does a counting sort of the input[] according to the digit represented by the exp
	 * @param input
	 * @param n
	 * @param exp
	 */
	private void countSort(int input[], int n, int exp)
	{
		int output[] = new int[n]; // output array
		int count[] = new int[10]; // assuming decimal
		
		System.out.println("\nexp=" + exp);
		System.out.println("input=" + Arrays.toString(input));
		// Initialize our count to 0
		Arrays.fill(count, 0);
		System.out.println("count=" + Arrays.toString(count));
		
		
		// Store number of occurrences in count[]
		for (int ii=0; ii<n;ii++)
		{
			int index = calculateIndexByDigitAndPosition(input[ii], exp);
			count[index]++;
		}
		System.out.println("count=" + Arrays.toString(count));
		
		// Change count[i] so that count[i] now contains the actual position of this digit in the output
		for (int ii=1;ii<10;ii++)
		{
			count[ii] += count[ii-1];
		}
		System.out.println("count=" + Arrays.toString(count));
		
		// Build the output array
		for (int ii=n-1; ii>=0;ii--)
		{
			int index = calculateIndexByDigitAndPosition(input[ii], exp);
			output[ count[index] - 1] = input[ii];
			count[index]--;
		}
		System.out.println("output=" + Arrays.toString(output));
		System.out.println("count=" + Arrays.toString(count));
		
		// Copy the output array to input[] so that input[] now contains sorted number according to the current digit
		for (int ii=0;ii<n;ii++)
		{
			input[ii] = output[ii];
		}
		System.out.println("input=" + Arrays.toString(input));
	}
	
	private int calculateIndexByDigitAndPosition(int value, int positionAsExp)
	{
		// Assumed 10 digits; okay for decimal
		return (value/positionAsExp)%10;
	}
	
	/**
	 * Utility function to the max value in the input arr[]
	 * @param arr
	 * @param n
	 * @return
	 */
	private int getMax(int arr[], int n)
	{		
		int max = arr[0];
		for (int ii=1;ii<n;ii++)
		{
			if (arr[ii] > max)
				max = arr[ii];
		}
		
		return max;
	}
}
