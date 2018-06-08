package org.emerson.lambda;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.Before;
import org.junit.Test;

/**
 * Ideal Use Case for Lambda Expressions
 * https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
 * 
 * You fundamentally want to pass functionality.
 * Named class and even an anonymous class seems too heavy
 * You only want to have a single method implemented (see standard functional interfaces)
 *
 * 
 * 1. You have a collection of things
 * 2. You want to create some sort of filter on those things
 * 3. You want to perform or take some action on those things
 * 
 * In the use case below:
 * 1. We want to filter a list of people by some criteria and then perform some action in this case print them
 * @author memerson
 *
 */
public class PersonFilterTest
{
	private List<Person> persons;
	
	@Before
	public void setup()
	{
		persons = PersonFactory.getPersons(17, 26);
	}
	
	@Test
	public void testApproach1ShouldPass()
	{
		// This will act on people (as loaded above)
		/*
		 * Approach 1: Create static methods that do it
		 * Downside: hard-coded, not flexible, not resistant to changes in the underlying data types
		 */
		printPersonsOlderThan(persons, 24);
		
	}
	
	@Test
	public void testApproach2ShouldPass()
	{
		printPersonsWithinAgeRange(persons, 18, 20);		
	}	
	
	@Test
	public void testApproach3ShouldPass()
	{
		printPersons(persons, new CheckPersonEligibleForSelectiveService());
	}

	@Test
	public void testApproach4ShouldPass()
	{
		/**
		 * Approach 4: In this approach, you don't bother creating the named class, you just build an anonymous class 
		 * on the fly.  
		 * Downsides: you still need an interface, which you have to name....basically you're writing an embedded class.
		 * Plus, the interface only has one method so lambdas can help us!
		 */
		printPersons(
			    persons,
			    new CheckPerson() {
			        public boolean test(Person p) {
			            return p.getGender() == Person.Sex.MALE
			                && p.getAge() >= 18
			                && p.getAge() <= 25;
			        }
			    }
			);
	}
	
	@Test
	public void testApproach5ShouldPass()
	{
		// Approach 5: Specify Search Criteria with a Lambda expression...
		// Since the CheckPerson only has one method, it is a functional interface, and lambda's can infer an anonynmous class implementation (including types!) from it
		// Downsides: We get a bunch of functional interfaces out of the box so why are we creating one?
		printPersons(
			    persons,
			    (Person p) -> p.getGender() == Person.Sex.MALE
			        && p.getAge() >= 18
			        && p.getAge() <= 25
			);
	}
	
	@Test
	public void testApproach6ShouldPass()
	{
		// Approach 6: Use an off the shelf predicate rather than writing our own
		// Downsides: none really, but why do we still even have the static method?
		printPersonsWithPredicate(
			    persons,
			    p -> p.getGender() == Person.Sex.MALE
			        && p.getAge() >= 18
			        && p.getAge() <= 25
			);
	}
	
	@Test
	public void testApproach7ShouldPass()
	{
		/**
		 * Approach 7: Have the action as well as the test be dynamic...note: we don't have to have the type on p because it can be inferred from our generic call
		 * Downsides: Again, why do we have a processPersons method?
		 */
		processPersons(
			     persons,
			     p -> p.getGender() == Person.Sex.MALE
			         && p.getAge() >= 18
			         && p.getAge() <= 25,
			     p -> p.printPerson()
			);
	}

	@Test
	public void testApproach7AShouldPass()
	{
		/**
		 * Approach 7A: Use Lambdas everywhere:
		 * This is cool because we can filter people, transform the data in the resulting people and then do something with it.
		 * All on the fly without writing a lot of code.
		 * In this case, we're filtering people who are eligible for selective service and then printing out their email address 
		 * Downsides: Again, why do we have a processPersons method?
		 */
		processPersonsWithFunction(
			    persons,
			    p -> p.getGender() == Person.Sex.MALE
			        && p.getAge() >= 18
			        && p.getAge() <= 25,
			    p -> p.getEmailAddress(),
			    email -> System.out.println(email)
			);
	}
	
	@Test
	public void testApproach8ShouldPass()
	{
		/**
		 * Approach 8: Ultimate expression of the static method.  Notice: there are no declared specific types in the processElements method
		 * Downsides: Why do we have a static method again?
		 */
		processElements(
			    persons,
			    p -> p.getGender() == Person.Sex.MALE
			        && p.getAge() >= 18
			        && p.getAge() <= 25,
			    p -> p.getEmailAddress(),
			    email -> System.out.println(email)
			);
	}
	
	@Test
	public void testApproach9ShouldPass()
	{
		/**
		 * Approach 9: This takes advantage of the streams API and aggregate actions to do away with the static method.
		 * so in this case:
		 * - Stream(): stream-ifies the collection
		 *  - filter() : filters objects that match the predicate
		 *  - map(Function mapper) : stream ; maps the incoming objects and creates a stream from the result
		 *  -forEach(consumer); takes an action on each object in the stream using the consumer API
		 */
		persons
	    .stream()
	    .filter(
	        p -> p.getGender() == Person.Sex.MALE
	            && p.getAge() >= 18
	            && p.getAge() <= 25)
	    .map(p -> p.getEmailAddress())
	    .forEach(email -> System.out.println(email));
	}
	
	
	/**
	 * The ultimate expression of what you can do with the static method
	 * 1. Takes an iterable list, tests each member, performs a transform on the member and then does something with it
	 * @param source
	 * @param tester
	 * @param mapper
	 * @param block
	 */
	public static <X, Y> void processElements(
		    Iterable<X> source,
		    Predicate<X> tester,
		    Function <X, Y> mapper,
		    Consumer<Y> block) {
		    for (X p : source) {
		        if (tester.test(p)) {
		            Y data = mapper.apply(p);
		            block.accept(data);
		        }
		    }
		}
	
	/**
	 * This approach allows us to filter the persons, transform their data and then do something with the transformed data
	 * @param roster
	 * @param tester
	 * @param mapper
	 * @param block
	 */
	public static void processPersonsWithFunction(
		    List<Person> roster,
		    Predicate<Person> tester,
		    Function<Person, String> mapper,
		    Consumer<String> block) {
		    for (Person p : roster) {
		        if (tester.test(p)) {
		            String data = mapper.apply(p);
		            block.accept(data);
		        }
		    }
		}
	
	
	/**
	 * Have the action to take on a person be something that's passed in as well
	 * @param roster
	 * @param tester
	 * @param block
	 */
	public static void processPersons(
		    List<Person> roster,
		    Predicate<Person> tester,
		    Consumer<Person> block) {
		        for (Person p : roster) {
		            if (tester.test(p)) {
		                block.accept(p);
		            }
		        }
		}
	
	/**
	 * So here we are using an off the shelf predicate instead of writing our own
	 * @param roster
	 * @param tester
	 */
	public static void printPersonsWithPredicate(
		    List<Person> roster, Predicate<Person> tester) {
		    for (Person p : roster) {
		        if (tester.test(p)) {
		            p.printPerson();
		        }
		    }
		}
	
	/**
	 * Approach 3: Write a more generalized checker
	 * Downsides: Solves a lot of problems of the hard-coding (although types are still there), but you have to create
	 * an interface and a named class.
	 * @param roster
	 * @param tester
	 */
	public static void printPersons(
		    List<Person> roster, CheckPerson tester) {
		    for (Person p : roster) {
		        if (tester.test(p)) {
		            p.printPerson();
		        }
		    }
		}
	
	/**
	 * Approach 2: Write more generalized methods
	 * Downsides, same as approach 1.  You can't possibly write this generic enough to get rid of its inflexibility
	 * @param roster
	 * @param low
	 * @param high
	 */
	public static void printPersonsWithinAgeRange(
		    List<Person> roster, int low, int high) {
		    for (Person p : roster) {
		        if (low <= p.getAge() && p.getAge() < high) {
		            p.printPerson();
		        }
		    }
		}
	
	/**
	 * Downside: Hard-coded (what about younger than?), not flexible, not resistant to changes in the underlying data types)
	 * @param roster
	 * @param age
	 */
	public static void printPersonsOlderThan(List<Person> roster, int age) {
	    for (Person p : roster) {
	        if (p.getAge() >= age) {
	            p.printPerson();
	        }
	    }
	}
}
