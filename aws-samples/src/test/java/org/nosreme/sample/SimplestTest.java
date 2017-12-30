package org.nosreme.sample;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SimplestTest {

	    @Test public void testSomeLibraryMethod() {
	        Simplest simplestTest = new Simplest();
	        assertEquals(simplestTest.toString(), "Hello World!");
	    }
}
