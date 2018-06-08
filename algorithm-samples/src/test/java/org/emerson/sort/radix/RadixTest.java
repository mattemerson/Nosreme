package org.emerson.sort.radix;

import org.emerson.sort.radix.RadixSort;
import org.junit.Assert;
import org.junit.Test;

public class RadixTest {

	@Test
	public void testRadixSortShouldPass()
	{
		int[] input = { 1000, 52, 12, 300, 500, 17 }; 
		int[] expected = { 12, 17, 52, 300, 500, 1000 };
		
		RadixSort radixSort = new RadixSort();
		int[] actual = radixSort.sort(input);
		
		Assert.assertArrayEquals(expected, actual);
	}
}
