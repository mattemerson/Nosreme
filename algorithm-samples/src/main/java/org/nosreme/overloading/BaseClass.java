package org.nosreme.overloading;

public class BaseClass {

	public BaseClass() {
		System.out.println("BaseClass: constructor");
	}

	static {
		System.out.println("BaseClass: static initializer");
	}
	
	{
		System.out.println("BaseClass:  non-static initializer.");
	}
}
