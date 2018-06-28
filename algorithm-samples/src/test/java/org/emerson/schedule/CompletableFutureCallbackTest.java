package org.emerson.schedule;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * The CompletableFuture.get() method is blocking. It waits until the Future is
 * completed and returns the result after its completion.
 * 
 * But, that’s not what we want right? For building asynchronous systems we
 * should be able to attach a callback to the CompletableFuture which should
 * automatically get called when the Future completes.
 * 
 * That way, we won’t need to wait for the result, and we can write the logic
 * that needs to be executed after the completion of the Future inside our
 * callback function.
 * 
 * You can attach a callback to the CompletableFuture using thenApply(),
 * thenAccept() and thenRun() methods -
 * 
 * @Link https://www.callicoder.com/java-8-completablefuture-tutorial/
 * @author memerson
 *
 */
public class CompletableFutureCallbackTest {

	/**
	 * ThenApply: Simple follow-on work
	 * 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test
	public void testAsynchProcessWithThenApplyCallbackShouldPass() throws InterruptedException, ExecutionException {
		// Create a CompletableFuture
		CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return "Dolly";
		});

		// Attach a callback to the Future using thenApply()
		// Just uses a simple Function functional interface. This one is
		// Function<String,String>
		CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(name -> {
			return "Hello " + name;
		});

		// Block and get the result of the future.
		String result = greetingFuture.get();

		Assert.assertEquals("Hello Dolly", result);
	}

	/**
	 * ThenApply: A chained piece of follow-on work
	 * 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test
	public void testAsynchProcessWithSequencyOfThenApplyCallbacksShouldPass()
			throws InterruptedException, ExecutionException {
		CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return "Dolly";
		}).thenApply(name -> {
			return "Hello " + name;
		}).thenApply(greeting -> {
			return greeting + "!";
		});

		// Block and get the result of the future.
		String result = welcomeText.get();

		Assert.assertEquals("Hello Dolly!", result);
	}

	/**
	 * If you don’t want to return anything from your callback function and just
	 * want to run some piece of code after the completion of the Future, then you
	 * can use thenAccept() and thenRun() methods. These methods are consumers and
	 * are often used as the last callback in the callback chain.
	 */

	/**
	 * CompletableFuture.thenAccept() takes a Consumer<T> and returns
	 * CompletableFuture<Void>. It has access to the result of the CompletableFuture
	 * on which it is attached.
	 */
	@Test
	public void testAsynchProcessWithThenAcceptMethodForPostProcessingShouldPass() {
		CompletableFuture.supplyAsync(() -> {
			return new ProductService().getProduct(42);
		}).thenAccept(product -> {
			System.out.println("Got product detail from remote service " + product.getName());
		});
	}

	public class Product {
		private int id;
		private String name;

		public Product(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return this.id;
		}

		public String getName() {
			return this.name;
		}
	}

	public class ProductService {
		public Product getProduct(int id) {
			return new Product(id, "foo-" + id);
		}
	}

	/*
	 * While thenAccept() has access to the result of the CompletableFuture on which
	 * it is attached, thenRun() doesn’t even have access to the Future’s result. It
	 * takes a Runnable and returns CompletableFuture<Void> -
	 */
	@Test
	public void testAsynchProcessWithThenRunMethodForPostProcessingShouldPass() {
		// thenRun() example
		CompletableFuture.supplyAsync(() -> {
			return "result of some computation";
		}).thenRun(() -> {
			System.out.println("Doing some other computation.  I have no access to the previous result");
		});
	}

	/**
	 * 
	 * In the above case, the task inside thenApply() is executed in the same thread
	 * where the supplyAsync() task is executed, or in the main thread if the
	 * supplyAsync() task completes immediately (try removing sleep() call to
	 * verify).
	 * 
	 * To have more control over the thread that executes the callback task, you can
	 * use async callbacks. If you use thenApplyAsync() callback, then it will be
	 * executed in a different thread obtained from ForkJoinPool.commonPool() or
	 * from your own thread pool if you care to specify one.
	 */
	@Test
	public void testAsynchProcessWithThenApplyExecutedInSameThread()
	{
		CompletableFuture.supplyAsync(() -> {
			
			System.out.println(Thread.currentThread().getName() + ": Pre-sleep");
		    try {
		       TimeUnit.MILLISECONDS.sleep(1);	
		    } catch (InterruptedException e) {	
		      throw new IllegalStateException(e);
		    }
			System.out.println(Thread.currentThread().getName() + ": Post-sleep");		    
		    
		    return "Some Result";
		}).thenApply(result -> {
		    /* 
		      Executed in the same thread where the supplyAsync() task is executed
		      or in the main thread If the supplyAsync() task completes immediately (Remove sleep() call to verify)
		    */
			System.out.println(Thread.currentThread().getName() + ": Apply-sleep");
			
		    return "Processed Result";
		});
	}
	
	@Test
	public void testAsynchProcessWithThenApplyExecutedInDifferentThreads()
	{
		CompletableFuture.supplyAsync(() -> {
			
			System.out.println(Thread.currentThread().getName() + ": Pre-sleep");
			System.out.println(Thread.currentThread().getName() + ": Post-sleep");		    
		    
		    return "Some Result";
		}).thenApply(result -> {
		    /* 
		      Executed in the same thread where the supplyAsync() task is executed
		      or in the main thread If the supplyAsync() task completes immediately (Remove sleep() call to verify)
		    */
			System.out.println(Thread.currentThread().getName() + ": Apply-sleep");
			
		    return "Processed Result";
		});		
	}
	
	@Test
	public void testAsynchProcessWithThenApplyUsingAnotherThreadFromTheCommonPool()
	{
		CompletableFuture.supplyAsync(() -> {
			System.out.println("ONE: " + Thread.currentThread().getName());
		    return "Some Result";
		}).thenApplyAsync(result -> {
			System.out.println("TWO: " + Thread.currentThread().getName());
		    // Executed in a different thread from ForkJoinPool.commonPool()
		    return "Processed Result";
		});
	}
	
	@Test
	public void testAsynchProcessWithThenApplyExecutedInMyOwnPoolOfThreads()
	{
		Executor executor = Executors.newFixedThreadPool(2);
		CompletableFuture.supplyAsync(() -> {
			System.out.println("ONE: " + Thread.currentThread().getName());
		    return "Some result";
		}).thenApplyAsync(result -> {
		    // Executed in a thread obtained from the executor
			System.out.println("TWO: " + Thread.currentThread().getName());
		    return "Processed Result";
		}, executor);
	}
}
