package org.emerson.lambda;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

/**
 * https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html
 * @author memerson
 *
 */
public class ReduceAndCollectTest {

	private List<Person> persons;
	
	@Before
	public void setup()
	{
		persons = PersonFactory.getPersons(17, 26);
	}
	
	@Test
	public void testGetNamesOfAllMalePersonsInAList()
	{
		
		List<String> namesOfMaleMembersCollect = persons
			    .stream()
			    .filter(p -> p.getGender() == Person.Sex.MALE)
			    .map(p -> p.getName())
			    .collect(Collectors.toList());
		System.out.println(namesOfMaleMembersCollect);
	}
	
	@Test
	public void testGetPersonsByGender()
	{
		Map<Person.Sex, List<Person>> byGender =
			    persons
			        .stream()
			        .collect(
			            Collectors.groupingBy(Person::getGender));
		
	}
	
	@Test
	public void testGetAverageAgeByGender()
	{
		Map<Person.Sex, Double> averageAgeByGender = persons
			    .stream()
			    .collect(
			        Collectors.groupingBy(
			            Person::getGender,   // group by gender                   
			            Collectors.averagingInt(Person::getAge))); // average the age
	}
	
	@Test
	public void testGetTotalAgeOfEachPersonByGender()
	{
		Map<Person.Sex, Integer> totalAgeByGender =
			    persons
			        .stream()
			        .collect(
			            Collectors.groupingBy(
			                Person::getGender,       // the key               
			                Collectors.reducing(
			                    0,						// runs a reduction on the before filtered piece
			                    Person::getAge,
			                    Integer::sum)));  // sums it up
	}
	
	@Test
	public void testGetNamesByGender()
	{
		Map<Person.Sex, List<String>> namesByGender =
			    persons
			        .stream()
			        .collect(
			            Collectors.groupingBy(
			                Person::getGender,    // Generates the Key                  
			                Collectors.mapping(
			                    Person::getName,  // Generates the list from the key grouping (mapping API)
			                    Collectors.toList())));
	}
	
	@Test
	public void testCalculateTotalAgeThreeDifferentWays()
	{
		// Not guaranteed a return value
		Integer totalAge = persons
			    .stream()
			    .mapToInt(Person::getAge)
			    .sum();
		System.out.println(totalAge);
		
		//Compare this with the following pipeline, which uses the Stream.reduce operation to calculate the same value:
		// This will always give zero...however, it may not be the best way to go if you are collecting elements into a collection (for instance)
			Integer totalAgeReduce = persons
			   .stream()
			   .map(Person::getAge)
			   .reduce(
			       0,
			       (a, b) -> a + b);
			System.out.println(totalAgeReduce);
	}
}
