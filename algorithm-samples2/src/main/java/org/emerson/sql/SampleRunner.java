package org.emerson.sql;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.emerson.lambda.Person;
import org.emerson.zaius.sql.PersonFileDAO;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner
{
    private static final Logger LOG = LoggerFactory.getLogger(SampleRunner.class);
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    /**
     * Runs the individual Samples
     */
    public void runSamples()
    {
		LOG.info("Creating tables");
	
		LOG.info("START:setupDatabase");
		setUpDatabase();
		LOG.info("FINISH:setupDatabase");
		
		LOG.info("START:populateDatabase");
		populateDatabase();
		LOG.info("FINISH:populateDatabase");

		LOG.info("START:calculateActualResults");
		List<String> actuals = calculateActualResults();
		LOG.info("FINISH:calculateActualResults");

		LOG.info("START:LoadExpectedResults");
		String filename2 = "resources/test/org/emerson/zaius/sql/output.txt";
		List<String> expected = readFileLineByLineUsingStreams(filename2);
		LOG.info("FINISH:LoadExpectedResults");

		LOG.info("START:compareResults");				
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
		LOG.info("FINISH:compareResults");
    }

    /**
     * Runs each of the test results
     * @return
     */
    private List<String> calculateActualResults()
    {
		// Get Input
		List<String> actuals = new ArrayList<String>();
		
		actuals.addAll(calculateAverageAge());
		actuals.add("");
		actuals.addAll(calculatePeoplePerState());
		actuals.add("");		
		actuals.addAll(calculateAverageAgePerState());
		actuals.add("");
		actuals.addAll(calculateAverageAgePerStateFilteredByAge());
		actuals.add("");
		
		return actuals;
    }
    
    /**
     * 	  1. select round(avg(age), 1) from people
	     (overall average age, rounded to 1 decimal place)
     * @return
     */
    private List<String> calculateAverageAge()
    {
    	List<String> results = jdbcTemplate.query("select round(avg(age), 1) as avg_age from people",
	            							(rs, rowNum) -> {return rs.getString("avg_age");});
	            
    	return results;
    }
    
    /**
     * 	  2. select state, count(*) from people group by state order by count(*) desc
	     (number of people in each state, ordered most to least)
     * @return
     */
    private List<String> calculatePeoplePerState()
    {
    	List<String> results = jdbcTemplate.query("select state, count(*) as total from people group by state order by count(*) desc, state asc",
				(rs, rowNum) -> {return rs.getString("state") + ","+ rs.getString("total");});
    	
    	return results;
    }
    
    /*
	  3. select state, round(avg(age), 0) from people group by state order by avg(age)
	     (average age per state, ordered smallest to largest average)
	*/   
    private List<String> calculateAverageAgePerState()
    {
    	List<String> results = jdbcTemplate.query("select state, round(avg(age), 0) avg_age_by_state from people group by state order by avg(age)",
				(rs, rowNum) -> {return rs.getString("state") + ","+ rs.getInt("avg_age_by_state");});
    	
    	return results;
    }
    
    /**
     * 	  4. select state, round(avg(age), 0) from people where age > 15 AND age < 55 group by state order by state
	     (average age per state for people between 15 and 55 years old, ordered by state name)
     * @return
     */
    private List<String> calculateAverageAgePerStateFilteredByAge()
    {
    	List<String> results = jdbcTemplate.query("select state, round(avg(age), 0) as avg_age_by_state from people where age > 15 AND age < 55 group by state order by state",
				(rs, rowNum) -> {return rs.getString("state") + ","+ rs.getInt("avg_age_by_state");});
    	
    	return results;
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
    
    private void setUpDatabase()
    {
	    jdbcTemplate.execute("DROP TABLE people IF EXISTS");
	    jdbcTemplate.execute("CREATE TABLE people(" +
	            "name VARCHAR(10), age FLOAT, state VARCHAR(2))");    	
    }
    
    private void populateDatabase()
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
			
		String filename1 = "resources/test/org/emerson/zaius/sql/people.csv";
		
		PersonFileDAO personDAO = new PersonFileDAO();
		List<Person> persons = personDAO.readLines(mapToItem, filename1);
		//List<Person> persons = Arrays.asList(new Person("bob", 20, "NH"));
		//List<Person> persons = readCSVFileUsingStreams(filename1, mapToItem);
		persons.stream().forEach(p->System.out.println(p.toZString()));
		
		Function<Person,Object[]> mapper = (Person p) -> { return Arrays.asList(p.getName(), p.getAge(), p.getState()).toArray(new Object[0]);};		
		List<Object[]> personsAsObjects = persons.stream().map(mapper).collect(Collectors.toList());
	
	    // Use a Java 8 stream to print out each tuple of the list
	    personsAsObjects.forEach(person -> LOG.info(String.format("Inserting customer record for %s %d %s", person[0], person[1], person[2])));
	
	    // Uses JdbcTemplate's batchUpdate operation to bulk load data
	    jdbcTemplate.batchUpdate("INSERT INTO people(name, age,state) VALUES (?,?,?)", personsAsObjects);
    }
}
