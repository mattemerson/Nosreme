package org.emerson.longestpalindrome;


import org.junit.Assert;
import org.junit.Test;


public class LongestPalindromeTest {

	@Test
	public void testFindLongestPalindromShouldPass()
	{

		LongestPalindromeFinder finder = new RecursiveLongestPalindromeFinder();

		Assert.assertEquals("", finder.findPalindrome(null));
		Assert.assertEquals("", finder.findPalindrome(""));
		Assert.assertEquals("a", finder.findPalindrome("a"));
		Assert.assertEquals("aa", finder.findPalindrome("aa"));
		Assert.assertEquals("ababa", finder.findPalindrome("ababa"));
		Assert.assertEquals("ababa", finder.findPalindrome("tababaz"));
		Assert.assertEquals("geeksskeeg", finder.findPalindrome("forgeeksskeegfor"));
	}
}

