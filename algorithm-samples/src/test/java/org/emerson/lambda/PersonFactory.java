package org.emerson.lambda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.emerson.lambda.Person.Sex;

public class PersonFactory {

	public static List<Person> getPersons(int lowAge, int highAge)
	{
		List<Person> persons = new ArrayList<Person>();
		
		for (int age=lowAge;age<=highAge;age++)
		{
			persons.add(newPerson(Sex.MALE, age));
			persons.add(newPerson(Sex.FEMALE, age));
		}
		
		return persons;
	}
	
	private static Person newPerson(Sex gender, int age)
	{
		LocalDate birthDay = LocalDate.now();
		String name = gender.name() + "-" + age;
		String emailAddress = name + "@test.com";
		
		Person person = new Person(name, birthDay, gender, emailAddress, age);
		
		return person;
	}
}
