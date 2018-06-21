package org.emerson.lambda;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class SortStreamTest {

	@Test
	public void testSortStreamTestShouldPass()
	{
		List<String> names = Arrays.asList("amy","bob","cat","egg");
		
		/**
		 * This example merges stream of List<Person> together using flatMap
		 */
		{
			List<Person> people = Stream.iterate(1,n->n+1).limit(5).map( 
								(age) -> { return names.stream().map( (name) -> { return new Person(age, name + "_first" + age, name + "_last" + age); } ).collect(Collectors.toList()); } )
								.flatMap(Collection::stream)
								.collect(Collectors.toList());
			
			//people.stream().forEach(p -> System.out.println(p));
			
			// This sorts people by age
			//people.stream().sorted(Comparator.comparing(Person::getAge)).forEach(p->System.out.println(p));
			
			// This sorts people by age, then sorts htem by their first name descending!  Awesome sauce!
			people.stream().sorted(Comparator.comparing(Person::getAge).thenComparing(Comparator.comparing(Person::getFirstName).reversed())).forEach(p->System.out.println(p));
		}
		
		{
			List<Person> people = names.stream().map( (name) -> { int age=1; return new Person(age, name + "_first" + age, name + "_last" + age); } ).collect(Collectors.toList());
			people.stream().forEach(p -> System.out.println(p));
		}
		

		
		//List<String> moreNames = names.stream().map(name -> { return newPerson(name, 1)); } ).collect(Collectors.toList());
		/*
		List<Person> people = Stream.iterate(1, n->n+1)
									  .limit(5)
									  .flatMap(
											  (n) ->
											  		{
											  			return names.stream().map( (s) -> { return newPerson(n, s); }
											  		} 							  
								      .collect(Collectors.toList());
		*/								  
	}
	
	public static Person newPerson(int age, String name)
	{
		return new Person(age, name + "_first" + age, name + "_last" + age);
	}	
	
	public static class Person 
	{
		private int age;
		private String firstName;
		private String lastName;
		

		
		public Person(int age, String firstName, String lastName)
		{
			this.age=age;
			this.firstName=firstName;
			this.lastName = lastName;
		}
		
		public int getAge()
		{
			return this.age;
		}
		
		public String getFirstName()
		{
			return this.firstName;
		}
		
		public String getLastName()
		{
			return this.lastName;
		}
		
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder();
			builder.append("(").append(age).append(",").append(firstName).append(",").append(lastName).append(")");
			return builder.toString();
		}
	}
}
