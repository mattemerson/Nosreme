package org.emerson.knapsackproblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

/**
 * @link https://www.geeksforgeeks.org/knapsack-problem/
 * @author memerson
 *
 */
public class KnapsackProblemTest
{
	@Test
	public void testFillKnapsackShouldPass()
	{
		List<Item> items = Arrays.asList(new Item(10,60), new Item(20,100), new Item(30,120));
        final int capacity = 50;
                
        Knapsack knapsack = new Knapsack(capacity);
        knapsack = fillSack(knapsack, items, 0);
        
        System.out.println(knapsack);
	}	
	    
	private Knapsack fillSack(Knapsack knapsack, List<Item> items, int currentItem)
	{
		// If we've run out of items, just return the current solution
		if (currentItem > items.size()-1)
		{	
			return knapsack;
		}
		
		// Let's get the next item
		Item item = items.get(currentItem);
		
		// If it doesn't fit, just return the existing solution
		if (knapsack.getRemainingCapacity() < item.getWeight())
		{
			return knapsack;
		}
		
		// Otherwise, add the item to the knapsack
		Knapsack newKnapsack = new Knapsack(knapsack);
		newKnapsack.addItem(item);
		
		Knapsack finalSack = bestSack(fillSack(knapsack, items, currentItem +1), fillSack(newKnapsack, items, currentItem+1));
		
		return finalSack;
	}
	
	public Knapsack bestSack(Knapsack knapsack, Knapsack otherSack)
	{
		// Could account for weight (greater weight is better maybe?
		if (knapsack.getValue() < otherSack.getValue())
		{
			return otherSack;
		}
		else
		{
			return knapsack;
		}
	}
	
	/*
    // Returns the maximum value that can be put in a knapsack of capacity W
    private int knapSack(int knapsackCapacity, List<Item> items)
    {
       // Base Case
    	if ( (items.size() == 0) || (capacity==0) )
    	
   if (n == 0 || W == 0)
       return 0;
     
   // If weight of the nth item is more than Knapsack capacity W, then
   // this item cannot be included in the optimal solution
   if (wt[n-1] > W)
      return knapSack(W, wt, val, n-1);
     
   // Return the maximum of two cases: 
   // (1) nth item included 
   // (2) not included
   else return max( val[n-1] + knapSack(W-wt[n-1], wt, val, n-1),
                    knapSack(W, wt, val, n-1)
                     );
     }
*/
    
	public class Knapsack
	{
		private int capacity;
		private List<Item> items;
		
		public Knapsack(int capacity)
		{
			this.capacity = capacity;
			items = new ArrayList<Item>();
		}
		
		public Knapsack(int capacity, List<Item> items)
		{
			this.capacity = capacity;
			this.items = Objects.isNull(items) ? new ArrayList<Item>() : new ArrayList<Item>(items);
		}
		
		public Knapsack(Knapsack otherKnapsack)
		{
			this.capacity = otherKnapsack.capacity;
			this.items = new ArrayList<Item>(otherKnapsack.items);
		}
		
		public int getCapacity()
		{
			return this.capacity;
		}
		
		public void addItem(Item item)
		{
			this.items.add(item);
		}
		
		public List<Item> getItems()
		{
			return Collections.unmodifiableList(items);
		}
		
		public int getWeight()
		{
			return items.stream().mapToInt(Item::getWeight).sum();
		}
		
		public int getValue()
		{
			return items.stream().mapToInt(Item::getValue).sum();
		}
		
		public int getRemainingCapacity()
		{
			return this.capacity - getWeight(); 
		}
		
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder();
			builder.append("<knapsack weight='" + getWeight() + "' value='" + getValue() + "'>").append("\n")
				   .append("<items>").append("\n");
			for (Item item : getItems())
			{
				builder.append(item).append("\n");
			}			
			builder.append("</items>").append("\n")
				   .append("</knapsack>");
			
			return builder.toString();
		}
	}
	
	public class Item
	{
		private int weight;
		private int value;
		
		/**
		 * Constructor
		 * @param weight
		 * @param value
		 */
		public Item(int weight, int value)
		{
			this.weight = weight;
			this.value = value;
		}
		
		public int getWeight()
		{
			return this.weight;
		}
		
		public int getValue()
		{
			return this.value;
		}
		
		@Override
		public String toString()
		{
			return "<item weight='" + getWeight() + "' value='" + getValue() + "'/>";
		}
	}
}
