package org.emerson.longestpalindrome;


import org.junit.Assert;
import org.junit.Test;


public class LongestPalindromeTest {

	@Test
	public void testFindLongestPalindromShouldPass()
	{

		LongestPalindromeFinder finder = new RecursiveLongestPalindromeFinder();

		Assert.assertEquals("", finder.findLongestPalindrome(null));
		Assert.assertEquals("", finder.findLongestPalindrome(""));
		Assert.assertEquals("a", finder.findLongestPalindrome("a"));
		Assert.assertEquals("aa", finder.findLongestPalindrome("aa"));
		Assert.assertEquals("ababa", finder.findLongestPalindrome("ababa"));
		Assert.assertEquals("ababa", finder.findLongestPalindrome("tababaz"));
		Assert.assertEquals("geeksskeeg", finder.findLongestPalindrome("forgeeksskeegfor"));
	}
}

