package org.emerson.lambda;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.emerson.lambda.Person.Sex;
import org.junit.Before;
import org.junit.Test;

/**
 * Aggregate Operations, Parallelism, Reductions
 * https://docs.oracle.com/javase/tutorial/collections/streams/index.html
 * https://docs.oracle.com/javase/tutorial/collections/streams/parallelism.html
 * https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html
 * @author memerson
 *
 */
public class AggregateTest {

	private List<Person> persons;
	
	@Before
	public void setup()
	{
		persons = PersonFactory.getPersons(17, 26);
	}
	
	@Test
	public void testCreateHashOfPersonsByGender()
	{
		// This is pretty cool, it lets you to sort the collection by gender and return a map based on it
		Map<Person.Sex, List<Person>> byGender =
			    persons
			        .stream()
			        .collect(
			            Collectors.groupingBy(Person::getGender));
		
		System.out.println(byGender);
	}
	
	@Test
	public void testPrintAllPersonsShouldPass()
	{
		persons.stream().forEach(person -> person.printPerson());
	}
	
	@Test
	public void testPrintNameForAllPersonsShouldPass()
	{
		persons.stream().map(person -> person.getName()).forEach(name -> System.out.println(name));
	}
	
	@Test
	public void testPrintOutAllMaleNamesShouldPass()
	{
		persons.stream().filter(p->(p.getGender() == Sex.MALE)).forEach(man->System.out.println(man.getName()));
	}
	
	@Test
	public void testPrintOutTheAverageAgeOfAllMenShouldPass()
	{
		/**
		 * Pretty cool: averages a particular value from the incoming entities
		 * Other aggregate operations: average, sum, min, max, and count) 
		 */
		double average = persons
			    .stream()
			    .filter(p -> p.getGender() == Person.Sex.MALE)
			    .mapToInt(Person::getAge) // Converts this to an int stream...could also have called p->p.getAge()
			    .average() // operates on a numeric stream...to perform an aggregate operation on it (OptionalDouble if you have an empty stream)
			    .getAsDouble(); // converts it to a specific data type
		System.out.println(average);
		
	}
	
	
}
