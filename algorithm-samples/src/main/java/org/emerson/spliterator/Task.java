package org.emerson.spliterator;

import java.util.Spliterator;

public class Task {
	private Spliterator<Article> spliterator;
	
	public Task(Spliterator<Article> spliterator)
	{
		this.spliterator = spliterator;
	}
	
	public String call() {
	    int current = 0;
	    while (spliterator.tryAdvance(a -> a.setName(a.getName()
	      .concat("- published by Emerson")))) {
	        current++;
	    }
	     
	    return Thread.currentThread().getName() + ":" + current;
	}
}
