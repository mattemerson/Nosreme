package org.emerson.spliterator;


import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;



/**
 * @link http://www.baeldung.com/java-spliterator
 * @author memerson
 *
 */
public class BaeldungSpliteratorTest {


	/**
	 * trySplit: partitions the stream in two or tries to
	 * estimatedSize gives an estimated number of elements
	 * hasCharactistics: gives an int representation of the spliterator characteristics
	 */
	@Test
	public void givenSpliterator_whenAppliedToAListOfArticle_thenSplittedInHalf() {
	    Spliterator<Article> split1 = generateArticleElements().spliterator(); 
	    
	    /**
	     * This call partitions, the two results and splits them in half
	     */
	    Spliterator<Article> split2 = split1.trySplit(); 
	    
	    String result1 = new Task(split1).call();
	    String result2 = new Task(split2).call();
	    
	    Assert.assertTrue(result1.endsWith(generateArticleElements().size() / 2 + ""));
	    Assert.assertTrue(result2.endsWith(generateArticleElements().size() / 2 + ""));	    	    
	}
	
	@Test
	public void testEstimatedSizeShouldPass()
	{
	    Spliterator<Article> split1 = generateArticleElements().spliterator(); 
	    Spliterator<Article> split2 = split1.trySplit();
	    
	    Assert.assertEquals(split1.estimateSize(), 17500);	    
	}
	
	/**
	 * SIZED – if it’s capable of returning an exact number of elements with the estimateSize() method
		SORTED – if it’s iterating through a sorted source
		SUBSIZED – if we split the instance using a trySplit() method and obtain Spliterators that are SIZED as well
		CONCURRENT – if source can be safely modified concurrently
		DISTINCT – if for each pair of encountered elements x, y, !x.equals(y)
		IMMUTABLE – if elements held by source can’t be structurally modified
		NONNULL – if source holds nulls or not
		ORDERED – if iterating over an ordered sequence
	 */
	@Test
	public void testHasCharacteristicsShouldPass()
	{
	    Spliterator<Article> split1 = generateArticleElements().spliterator(); 
	    Spliterator<Article> split2 = split1.trySplit();
	    
	    Assert.assertEquals(split1.characteristics(), 16464);	    
	}	
	
	public void testAutherCountShouldPass()
	{
		Article article = null;
		
		List<Author> authors = article.getListOfAuthors();
		
		// This works well for a single threaded stream
		Assert.assertEquals(9, countAuthors(authors.stream()));
		
		// But it is wrong for a parallel stream
		Assert.assertNotEquals(9, countAuthors(authors.stream().parallel()));
	}
	
	private int countAuthors(Stream<Author> stream)
	{
	    RelatedAuthorCounter wordCounter = stream.reduce(
	    									new RelatedAuthorCounter(0, true), 
	    									RelatedAuthorCounter::accumulate, 
	    									RelatedAuthorCounter::combine);
	    return wordCounter.getCounter();
	}
	
	public static List<Article> generateArticleElements()
	{
	    return Stream.generate(() -> new Article("Java")).limit(35000).collect(Collectors.toList());
	}
	
	

}
