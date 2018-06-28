package org.emerson.schedule;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * {@link http://www.deadcoderising.com/java8-writing-asynchronous-code-with-completablefuture/}
 * @author memerson
 *
 */
public class CompletableFutureTest {

	//http://www.baeldung.com/java-completablefuture
	//https://dzone.com/articles/20-examples-of-using-javas-completablefuture
	
	private Supplier<String> getAReceiver = () -> { System.out.println("Getting a receiver");
												     return "receiver1";
												    };
	private Function<String,String> sendMessage = (String receiver) -> {
														System.out.println("Sent Msg=\"" + "Ackbar" + "\" to Receiver=" + receiver);
														return "success";														
													};
	private Consumer<String> statusReport = (status) -> { System.out.println("Message status=" + status); };


	/*
	private Consumer<String> someAction = (s) -> { System.out.println("Task:" + s +" completed."); };
	private Supplier<String> gotSomeStuff = () -> { System.out.println("getting some stuff"); 
											return "some stuff"; };
	*/
	
	@Test
	public void testSimplestCompletableFutureShouldPass()
	{
		// Uses ForkJoinPoin.commonPool as the Executor
		// This just processes something asynchronously.  Its not very profound.
		// These all return completable futures that can be chained together
		CompletableFuture<?> valid = CompletableFuture.supplyAsync(getAReceiver);	
	}
	
	@Test
	public void testSimplestCallbackShouldPass()
	{
		// Uses ForkJoinPool.commonPool as the Executor
		// This does a little more; takes the upstream work and does something with it.
		CompletableFuture.supplyAsync(getAReceiver)  
        				 .thenAccept(statusReport);
	}
	
	@Test
	public void testChainingMultipleCallbacks()
	{
		// If you want to chain things together, thenApply let's you pass in a Function interface to pass information through the chain
		CompletableFuture.supplyAsync(getAReceiver)
						 .thenApply(sendMessage)
		 				 .thenAccept(statusReport);
	}
	
	/**
	 * This one totally doesn't work!!!!
	 */
	@Test
	public void testCombineFuturesShouldPass()
	{
		// All of the previous tasks return completable futures. You can combine
		// completable futures (say because different parts of the application will
		// build a complicated workflow...or perhaps you have an emergent workflow based
		// on upstream processing)
		
		//CompletableFuture.supplyAsync(getAReceiver)
		//				 .thenCompose(receiver -> newAsynchMessage(receiver));
	}
	
	/*
	https://www.callicoder.com/java-8-completablefuture-tutorial/
	private CompletableFuture<String> newAsynchMessage(String receiver)
	{	
		return CompletableFuture.supplyAsync(statusReport);
		
		/*
		Function<String,CompletableFuture<String>> asynchMessage = (String input) ->
		{
			return CompletableFuture.
			return CompletableFuture.supplyAsync( () -> { return input; } ).thenApply(sendMessage).thenAccept(statusReport);			
		};
		*/
		//return asynchMessage;
	//}

}
