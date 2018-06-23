package org.emerson.filereadsamples;

import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class FileUtil2 {

	/*
	https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
		https://leanpub.com/whatsnewinjava8/read
		
		Streaming Files
		The BufferedReader now has the lines() method which returns a Stream; for example3:

		1 try (FileReader fr = new FileReader("file");
		2     BufferedReader br = new BufferedReader(fr)) {
		3     br.lines().forEach(System.out::println);
		4 }
		You can also read a file as a Stream using Files.lines(Path filePath); for example:

		1 try (Stream st = Files.lines(Paths.get("file"))) {
		2     st.forEach(System.out::println);
		3 }
		Note this populates lazily; it does not read the entire file when you call it.

		Files.lines(Path): Any IOException that is thrown while processing the file (after the file is opened) will get wrapped in an UncheckedIOException and thrown.
	*/
	
	/*
	public static List<String> readFileUsingBufferedReader(String filename)
	{
		
	}
	
	public static List<String> readFileUsingFileReader(String filename)
	{
		
	}
	
	public static List<String> readFileUsingScanner(String filename)
	{
		
	}
	
	public static List<String> readFileUsingScannerWithoutLoops(String filename)
	{
		
	}
	
	public static List<String> readFileInAList(String filename)
	{
		
	}
	
	public static String readTextFileAsASingleString(String fielname)
	{
		
	}
	
	public static List<String> readFileUsingStream(String filename)
	{
		
	}
	*/
	Function<String,String> noopLineParser = (s) -> { return s; };
	
}
