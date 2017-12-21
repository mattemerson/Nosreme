package biz.matt.collections.linkedlist;

public final class LinkedListNode
{
	private LinkedListNode myPrevious;
	private LinkedListNode myNext;
	private LinkedListNode myHead;
	private int myValue;
		
	public static LinkedListNode newLinkedList(int value)
	{
		LinkedListNode head = new LinkedListNode(value, null, null, null);
		head.myHead = head;
		
		return head;
	}
	
	private LinkedListNode(int value, LinkedListNode head, LinkedListNode previous, LinkedListNode next)
	{
		myValue = value;
		myHead = head;
		myPrevious = previous;
		myNext = next;
	}
	
	/**
	 * Deletes this node
	 */
	public void delete()
	{
		LinkedListNode currentPrevious = myPrevious;
		LinkedListNode currentNext = myNext;
		
		// Have to connect the previous to the next; if there is one
		if (hasPrevious())
		{
			currentPrevious.myNext = currentNext;
		}
		
		// Have to delete the previous link, if there is a next
		if (hasNext())
		{
			currentNext.myPrevious = currentPrevious;
		}		
		
		// Yeah, I know C-style
		// Orphan the node
		myPrevious = null;
		myNext = null;
		myHead = null;
	}
	
	public LinkedListNode addNext(int value)
	{
		LinkedListNode newNext = new LinkedListNode(value, head(), this, null);
		
		// If this has a next value, we need to insert a new node
		// Otherwise, we need to append to the tail of the LinkedListNode
		if (hasNext())
		{
			LinkedListNode currentNext = next();
			currentNext.myPrevious = newNext;
			newNext.myNext = currentNext;
		}
		
		myNext = newNext;
		
		return next();
	}
	
	public int value()
	{
		return myValue;
	}
	
	public boolean hasNext()
	{
		if (myNext != null)
		{
			return true;
		}
		
		return false;
	}
	
	
	public boolean hasPrevious()
	{
		if (myPrevious != null)
		{
			return true;
		}
		
		return false;
	}
	
	public LinkedListNode head()
	{
		return myHead;
	}
	
	public LinkedListNode next()
	{
		if (!hasNext())
		{
			throw new LinkedListNodeAccessException("There is no next node");
		}
		
		return myNext;
	}
	
	public LinkedListNode previous()
	{
		if (!hasPrevious())
		{
			throw new LinkedListNodeAccessException("There is no previous node");
		}		
		
		return myPrevious;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("(");
		LinkedListNode node = this.head();
		while (true)
		{
			builder.append("(").append(node.value()).append(")").append(",");
			if (node.hasNext())
			{
				node = node.next();
			}
			else
			{
				break;
			}
		}

		builder.append(")");
		
		return builder.toString();
	}
}
