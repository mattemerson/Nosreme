package org.emerson.zaius.sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.emerson.lambda.Person;
import org.junit.Assert;
import org.junit.Test;

public class SqlTester
{
	public static String toZString(Person p)
	{
		String val = p.getName()+"," + p.getAge() + "," + p.getState(); 
		return val;
	}
	
	@Test
	public void testSqlTransformationsShouldPass()
	{
		Function<String, Person> mapToItem = (line) -> {

			  String[] p = line.split(",");// a CSV has comma separated lines

			  Person item = new Person();

			  item.setName(p[0]);//<-- this is the first column in the csv file
			  item.setAge(Integer.parseInt(p[1]));
			  item.setState(p[2]);

			  /*
			  if (p[3] != null && p[3].trim().length() > 0) {

			    item.setSomeProeprty(p[3]);

			  }
				*/
			  //more initialization goes here

			  return item;

			};
		
		String filename1 = "src/test/java/org/emerson/zaius/sql/people.csv";
		
		PersonFileDAO personDAO = new PersonFileDAO();
		List<Person> persons = personDAO.readLines(mapToItem, filename1);
		//List<Person> persons = readCSVFileUsingStreams(filename1, mapToItem);
		persons.stream().forEach(p->System.out.println(toZString(p)));
		

		// Get Input
		List<String> actuals = new ArrayList<String>();
		
		actuals.addAll(calculateAverageAge(persons));
		actuals.add("");
		actuals.addAll(calculatePeoplePerState(persons));
		actuals.add("");		
		actuals.addAll(calculateAverageAgePerState(persons));
		actuals.add("");
		actuals.addAll(calculateAverageAgePerStateFilteredByAge(persons));
		actuals.add("");
		
		String filename2 = "src/test/java/org/emerson/zaius/sql/output.txt";
		List<String> expected = readFileLineByLineUsingStreams(filename2);
		//System.out.println(expected);

		
		List<String> testExpected = expected; //expected.subList(0, 15);
		List<String> testActual = actuals; //actuals.subList(0, 15);
		
		System.out.println("actuals:");		
		testActual.stream().forEach(s->System.out.println(s));
		System.out.println("-----");
		System.out.println("expected:");
		testExpected.stream().forEach(s->System.out.println(s));
		System.out.println("---------");
		
		Assert.assertArrayEquals(testExpected.toArray(new String[0]), testActual.toArray(new String[0]));
		System.out.println("Victory!");
	}
	
	/*
	  1. select round(avg(age), 1) from people
	     (overall average age, rounded to 1 decimal place)
*/
	private List<String> calculateAverageAge(List<Person> population)
	{
		double average = population
			    .stream()
			    .mapToInt(Person::getAge) // Converts this to an int stream...could also have called p->p.getAge()
			    .average() // operates on a numeric stream...to perform an aggregate operation on it (OptionalDouble if you have an empty stream)
			    .getAsDouble(); // converts it to a specific data type
		String output = String.format("%.1f", average);
		
		List<String> results = new ArrayList<String>();
		results.add(output);
		
		return results;
	}
	
	/*
	  2. select state, count(*) from people group by state order by count(*) desc
	     (number of people in each state, ordered most to least)
*/
	private List<String> calculatePeoplePerState(List<Person> population)
	{		
		List<String> output = population
									.stream()
									.collect(Collectors.groupingBy(Person::getState, Collectors.counting()))
									.entrySet().stream()
									.sorted( (s1,s2) -> {
															// Desc by Long, Asc by Key
															int r = -s1.getValue().compareTo(s2.getValue());
															if (r==0)
															{
																//return 1;
																return s1.getKey().compareTo(s2.getKey());
															}
															return r;	
														}
									)									
								    .map(map-> map.getKey() + "," + map.getValue())
								    .collect(Collectors.toList());
		return output;
	}
	
	/*
	  3. select state, round(avg(age), 0) from people group by state order by avg(age)
	     (average age per state, ordered smallest to largest average)
*/
	private List<String> calculateAverageAgePerState(List<Person> population)
	{
		/*
		Comparator.comparing(Person::getLastName)
		2         .thenComparing(Person::getFirstName
			*/
		
		List<String> output = population
				.stream()
				.collect(Collectors.groupingBy(Person::getState, Collectors.averagingDouble(Person::getAge)))
				.entrySet().stream()				
				.sorted( (s1,s2) -> {
										// Desc by Long, Asc by Key
										int r = s1.getValue().compareTo(s2.getValue());
										if (r==0)
										{
											//return 1;
											return s1.getKey().compareTo(s2.getKey());
										}
										return r;	
									}
				)				
			    .map(map-> map.getKey() + "," + String.format("%.0f", map.getValue()))
			    .collect(Collectors.toList());
		
		return output;
	}
	
	/*
	  4. select state, round(avg(age), 0) from people where age > 15 AND age < 55 group by state order by state
	     (average age per state for people between 15 and 55 years old, ordered by state name)
	  */
	private List<String> calculateAverageAgePerStateFilteredByAge(List<Person> population)
	{
		List<String> output = population
				.stream()
				.filter(p-> ( (p.getAge() > 15) && (p.getAge() < 55)))
				.collect(Collectors.groupingBy(Person::getState, Collectors.averagingDouble(Person::getAge)))
				.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.map(map-> map.getKey() + "," + String.format("%.0f", map.getValue()))
				.collect(Collectors.toList());						

		return output;
	}	
	
	public static List<String> readFileLineByLineUsingStreams(String filename)
	{
		List<String> lines = null;
		
		//read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(filename)))
		{
			lines = stream.collect(Collectors.toList());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lines;
	}
	
	public static <T> List<T> readCSVFileUsingStreams(String inputFilePath, Function<String,T> mapToItem)
	{
	    List<T> inputList = new ArrayList<T>();

	    try{

	      File inputF = new File(inputFilePath);

	      InputStream inputFS = new FileInputStream(inputF);

	      BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

	      // skip the header of the csv
	      inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());

	      br.close();

	    }
	    catch (IOException e)
	    {
	    	throw new RuntimeException("line could not be read", e);

	    }

	    return inputList ;

	}
	
	/*
	 * Sample for how to parse a CSV line
	 * 
	private Function<String, T> mapToItem = (line) -> {

		  String[] p = line.split(COMMA);// a CSV has comma separated lines

		  T item = new T();

		  item.setItemNumber(p[0]);//<-- this is the first column in the csv file

		  if (p[3] != null && p[3].trim().length() > 0) {

		    item.setSomeProeprty(p[3]);

		  }

		  //more initialization goes here

		  return item;

		}
		*/
}
