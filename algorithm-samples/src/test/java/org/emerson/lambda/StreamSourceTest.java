package org.emerson.lambda;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.Assert;

/**
 * NOTE: Leaving streams unconsumed can cause memory leaks!
 * See <a href="http://www.baeldung.com/java-8-streams">http://www.baeldung.com/java-8-streams</a>
 * See <a href="http://www.java2s.com/Tutorials/Java/Java_Stream/index.htm">http://www.java2s.com/Tutorials/Java/Java_Stream/index.htm</a>
 * @author memerson
 *
 */
public class StreamSourceTest {

	@Test
	public void testStreamSourceShouldPass()
	{
		
	}
	
	@Test
	public void testCreateEmptyStreamShouldPass()
	{
		// Use an empty stream rather than null
		Stream<String> emptyStream = Stream.empty();
		
		List<String> input = null;
		Stream<String> possiblyEmptyStream = streamOf(input);
	}

	/**
	 * Returns an empty stream rather thna null
	 * @param list
	 * @return
	 */
	private Stream<String> streamOf(List<String> list)
	{
		return list == null || list.isEmpty() ? Stream.empty() : list.stream();
	}
	
	/**
	 * Creates a stream from a collection using natural ordering
	 */
	@Test
	public void testCreateStreamFromCollectionShouldPass()
	{
		Collection<String> collection = Arrays.asList("a","b","c");
		Stream<String> streamOfCollection = collection.stream();
	}
	
	/**
	 * Creates a stream or substream from an array
	 */
	@Test
	public void testCreateStreamAndSubstreamFromArrayShouldPass()
	{
		String[] arr = new String[] {"a", "b", "c" };
		Stream<String> streamOfArrayFill = Arrays.stream(arr);
		Stream<String> streamOfArrayPart = Arrays.stream(arr, 1,3);	
	}
	
	/**
	 * There are different flavors of builder
	 */
	@Test
	public void testCreateStreamFromBuilderShouldPass()
	{
		Stream<String> builtStream =
				Stream.<String>builder().add("a").add("b").add("c").build();
	}
	
	/**
	 * Uses Stream.builder,  Accepts a Supplier<T> for element generation
	 * Stream is infinite, so cap it with a limit
	 */
	@Test
	public void testCreateFromStreamGenerateShouldPass()
	{
		Stream<String> generatedStream = Stream.generate(()-> "element").limit(10);
	}

	/**
	 * Creates a stream from iterator.  I remember these from Matlab
	 */
	@Test
	public void testCreateFromIteratorShouldPass()
	{
		Stream<Integer> iteratedStream = Stream.iterate(40, n -> n + 2).limit(20);
	}
	
	/**
	 * Can create primitives: Int, Double, Long, for ranges inclusive and exclusive
	 */
	@Test
	public void testCreateAStreamOfPrimitives()
	{
		IntStream intStream = IntStream.range(1, 3);
		LongStream longStream = LongStream.rangeClosed(1, 3);
		DoubleStream doubleStream = DoubleStream.iterate(10.0f, n->n+1).limit(3);		
	}
	
	/**
	 * Create a stream of random values
	 */
	@Test
	public void testCreateAStreamOfRandomValues()
	{
		Random random = new Random();
		DoubleStream doubleStream = random.doubles(3);
	}
	
	/**
	 * Turn a string into an IntStream or a StringStream
	 */
	@Test
	public void testCreateAStringStreamFromAString()
	{
		// Have to represent individual characters as an IntStream (there is no CharStream)
		IntStream streamOfChars = "abc".chars();
		
		// Can break a string into sub-strings according to a specific RegEx
		Stream<String> stringStream = Pattern.compile(", ").splitAsStream("a, b, c");
	}
	
	/**
	 * Create from file
	 */
	@Test
	public void testCreateAStringStreamFromFile()
	{
		// I don't have a file, but here's the code
		/*
		Path path = Paths.get("C:\\file.txt");
		Stream<String> streamOfStrings = Files.lines(path);
		Stream<String> streamWithCharset = 
		  Files.lines(path, Charset.forName("UTF-8"));
		*/
	}
	
	/**
	 * Referencing a dead stream.  You can access a stream as long as only intermediate operations are called.  As soon as you call a terminal operation, you will get an IllegalStateException
	 * @throws IllegalStateException
	 */
	@Test
	public void testReferencingADeadStreamShouldFail()
	{
		Stream<String> stream = 
				  Stream.of("a", "b", "c").filter(element -> element.contains("b"));
				
		// Terminal Operation
		Optional<String> anyElement = stream.findAny();
		
		// Stream is dead; this will throw an exception
		Optional<String> firstElement = stream.findFirst();		
	}
	
	@Test
	public void testSingleStripDoSomeWorkOnItAndReturnACount()
	{
		List<String> list = Arrays.asList("abc1", "abc2", "abc3");
		long size = list.stream().skip(1)
		  .map(element -> element.substring(0, 3)).sorted().distinct().count();
		
		Assert.assertEquals(size, 1);
	}
	
	private long counter;
	  
	private void wasCalled() {
	    counter++;
	}
	
	@Test
	public void testIntermediateObjectNotBeingCalledBecauseOfLaziness()
	{
		{			
			// Not called because there was no terminal condition!
			List<String> list = Arrays.asList("abc1", "abc2", "abc3");
			counter = 0;
			Stream<String> stream = list.stream().filter(element -> {
			    wasCalled();
			    return element.contains("2");
			});
			System.out.println("counter=" + counter);
		}
		
		{
			// There was a terminal condition!
			counter = 0;
			List<String> list = Arrays.asList("abc1", "abc2", "abc3");		
			Optional<String> stream = list.stream().filter(element -> {
			    System.out.println("filter() was called");
			    return element.contains("2");
			}).map(element -> {
			    System.out.println("map() was called");
			    return element.toUpperCase();
			}).findFirst();
			
			System.out.println("counter=" + counter);
		}
	}
	
	@Test
	public void testReduceMethodShouldPass()
	{
		// Elements of reduce
		// IDENTITY: Initial value for an accumulator or default if the stream is empty
		// ACCUMULATOR: Tells you how to aggregate the elements
		// COMBINOR; aggregate results of the accumulator; ONLY CALLED IN PARALLEL MODE to reduce the results of accumulators from different threads

		// 6
		OptionalInt reduced =
				  IntStream.range(1, 4).reduce((a, b) -> a + b);
		System.out.println(reduced);
		
		// 16
		int reducedTwoParams =
				  IntStream.range(1, 4).reduce(10, (a, b) -> a + b);
		System.out.println(reducedTwoParams);
		
		// Combiner not called because not in parallel! 16
		int reducedParams = Stream.of(1, 2, 3)
				  .reduce(10, (a, b) -> a + b, (a, b) -> {
				     System.out.println("combiner was called");
				     return a + b;
				  });
		System.out.println(reducedParams);
		
		// Called in Parallel, 36
		int reducedParallel = Arrays.asList(1, 2, 3).parallelStream()
			    .reduce(10, (a, b) -> a + b, (a, b) -> {
			       System.out.println("combiner was called");
			       return a + b;
			    });
		System.out.println(reducedParallel);
	}
	
	public class Product
	{
		private int price;
		private String name;
		
		public Product(int price, String name)
		{
			this.price = price;
			this.name = name;
		}
		
		public int getPrice()
		{
			return this.price;
		}
		
		public String getName()
		{
			return this.name;
		}
	}
	
	@Test
	public void testCollectMethodShouldPass()
	{
		List<Product> productList = Arrays.asList(new Product(23, "potatoes"),
				  new Product(14, "orange"), new Product(13, "lemon"),
				  new Product(23, "bread"), new Product(13, "sugar"));
		
		List<String> collectorCollection = 
				  productList.stream().map(Product::getName).collect(Collectors.toList());
		
		// Joiner takes three parameters (delimiter, prefix, suffix)
		// This is cool
		String listToString = productList.stream().map(Product::getName)
				  .collect(Collectors.joining(", ", "[", "]"));
		System.out.println(listToString);
		
		// Summing int (or averrage, summarizing, etc. provides the mapping
		int summingPrice = productList.stream()
				  .collect(Collectors.summingInt(Product::getPrice));
		System.out.println(summingPrice);
		
		// Collects summary information abouyt the stream
		IntSummaryStatistics statistics = productList.stream()
				  .collect(Collectors.summarizingInt(Product::getPrice));
		System.out.println(statistics);		
		
		// Group by...something...this groups by price
		Map<Integer, List<Product>> collectorMapOfLists = productList.stream()
				  .collect(Collectors.groupingBy(Product::getPrice));
		
		// THis groups by a predicate, in this case, if the price is > 15 
		Map<Boolean, List<Product>> mapPartioned = productList.stream()
				  .collect(Collectors.partitioningBy(element -> element.getPrice() > 15));
		
		// Creates an unmodifiable set out of it
		Set<Product> unmodifiableSet = productList.stream()
				  .collect(Collectors.collectingAndThen(Collectors.toSet(),
				  Collections::unmodifiableSet));
		
		// Creating my own collector
		Collector<Product, ?, LinkedList<Product>> toLinkedList =
				  Collector.of(LinkedList::new, LinkedList::add, 
				    (first, second) -> { 
				       first.addAll(second); 
				       return first; 
				    });
		
		// This pulls a list of products into a linked list
		LinkedList<Product> linkedListOfPersons =productList.stream().collect(toLinkedList);
	}
	
	@Test
	public void testDoThingsInParallelShouldPass()
	{
		List<Product> productList = Arrays.asList(new Product(23, "potatoes"),
				  new Product(14, "orange"), new Product(13, "lemon"),
				  new Product(23, "bread"), new Product(13, "sugar"));
		
		// Use ParallelStream if the source of the stream is a Collection or Array
		{
			Stream<Product> streamOfCollection = productList.parallelStream();
			boolean isParallel = streamOfCollection.isParallel();
			boolean bigPrice = streamOfCollection
			  .map(product -> product.getPrice() * 12)
			  .anyMatch(price -> price > 200);
		}
		
		// Use the parallel call for anything else
		{
			IntStream intStreamParallel = IntStream.range(1, 150).parallel();
			boolean isParallel = intStreamParallel.isParallel();
			
			// Can be converted back to sequential mode by the sequential call
			IntStream intStreamSequential = intStreamParallel.sequential();
			isParallel = intStreamSequential.isParallel();
		}
	}
}
