package org.emerson.misc;

import org.junit.Test;

import junit.framework.Assert;

public class TestMisc {

	@Test
	public void testTruncation()
	{
		long intMaxPlus1 = (long)Integer.MAX_VALUE +  (long)1;
		System.out.println(intMaxPlus1);
		
		
		int castIntMaxPlus1 = (int)intMaxPlus1;
		System.out.println(castIntMaxPlus1);
		
		Assert.assertEquals(Integer.MAX_VALUE, (int)intMaxPlus1);
	}
}
