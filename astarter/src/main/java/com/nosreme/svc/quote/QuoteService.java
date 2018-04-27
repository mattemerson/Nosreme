package com.nosreme.svc.quote;

public interface QuoteService
{
	/**
	 * Grabs the quote from an external source
	 * @return
	 */
	public Quote retrieveQuote();
}
