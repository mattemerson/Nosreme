package org.emerson.spliterator;

/**
 * This class will count authors while traversing a stream of authors. Then the
 * class will perform a reduction on the stream.
 * 
 * @author memerson
 *
 */
public class RelatedAuthorCounter {
	private int counter;
	private boolean isRelated;

	public RelatedAuthorCounter(int currentCount, boolean related)
	{
		this.counter = currentCount;
		this.isRelated = related;
	}
	// standard constructors/getters

	public int getCounter()
	{
		return this.counter;
	}
	
	public RelatedAuthorCounter accumulate(Author author)
	{
		if (author.getRelatedArticleId() == 0)
		{
			return isRelated ? this : new RelatedAuthorCounter(counter, true);
		}
		else
		{
			return isRelated ? new RelatedAuthorCounter(counter + 1, false) : this;
		}
	}

	public RelatedAuthorCounter combine(RelatedAuthorCounter RelatedAuthorCounter)
	{
		return new RelatedAuthorCounter(counter + RelatedAuthorCounter.counter, RelatedAuthorCounter.isRelated);
	}
}
