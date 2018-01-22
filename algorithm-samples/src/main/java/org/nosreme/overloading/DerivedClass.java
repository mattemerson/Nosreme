package org.nosreme.overloading;

public class DerivedClass extends BaseClass {

	public DerivedClass() {
		System.out.println("DerivedClass: constructor");
	}

	static {
		System.out.println("DerivedClass: static initializer");
	}
	
	{
		System.out.println("DerivedClass:  non-static initializer.");
	}

	
}
