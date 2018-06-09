package org.emerson.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtil
{
	//https://www.mkyong.com/java8/java-8-stream-read-a-file-line-by-line/
	//String fileName = "c://lines.txt";
	//			stream.forEach(System.out::println);
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
