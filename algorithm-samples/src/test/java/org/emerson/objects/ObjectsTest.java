package org.emerson.objects;

import org.junit.Test;

/**
 * {@link} https://www.geeksforgeeks.org/java-util-objects-class-java/
 * 
 * @author memerson
 *
 */
public class ObjectsTest {
	/**
	 * The purpose of this test class is to exercise the methods on the objects
	 * class Methods: String toString(Object) String toString (Object, default
	 * String) boolean equals (Object Object) boolean deepEquals(Object, Object) T
	 * requireNonNull(T) T requireNonNull(T, message) int hashcode(Object) int
	 * hash(Object ...values) int compare(T,T,Comparator) isNull(Object) (not shown)
	 * nonNull(Object) (not shown) requireNonNull(T, Supplier<String>
	 * messageSupplier) (not shown)
	 */

	@Test
	public void testToStringShouldPass() {
		Pair<String, String> p1 = new Pair<String, String>("GFG", "geeksforgeeks.org");
		Pair<String, String> p2 = new Pair<String, String>("Code", null);

		System.out.println(p1);
		System.out.println(p2);
	}

	@Test
	public void testEqualsShouldPass() {
		Pair<String, String> p1 = new Pair<String, String>("GFG", "geeksforgeeks.org");

		Pair<String, String> p2 = new Pair<String, String>("GFG", "geeksforgeeks.org");

		Pair<String, String> p3 = new Pair<String, String>("GFG", "www.geeksforgeeks.org");

		System.out.println(p1.equals(p2));
		System.out.println(p2.equals(p3));

	}

	@Test
	public void testRequiredValuesShouldPass() {
		Pair<String, String> p1 = new Pair<String, String>("GFG", "geeksforgeeks.org");

		p1.setKey("Geeks");

		// below statement will throw NPE with customized message
		p1.setValue(null);
	}

	@Test
	public void testHashCodeShouldPass() {
		Pair<String, String> p1 = new Pair<String, String>("GFG", "geeksforgeeks.org");
		Pair<String, String> p2 = new Pair<String, String>("Code", null);
		Pair<String, String> p3 = new Pair<String, String>(null, null);

		System.out.println(p1.hashCodeOption2());
		System.out.println(p2.hashCodeOption2());
		System.out.println(p3.hashCodeOption2());
	}

	@Test
	public void testHashShouldPass() {
		Pair<String, String> p1 = new Pair<String, String>("GFG", "geeksforgeeks.org");
		Pair<String, String> p2 = new Pair<String, String>("Code", null);
		Pair<String, String> p3 = new Pair<String, String>(null, null);

		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());
		System.out.println(p3.hashCode());

	}

}
