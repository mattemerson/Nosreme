package com.nosreme.sp.quote.protocol.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nosreme.svc.quote.Quote;
import com.nosreme.svc.quote.QuoteServiceClient;

@RestController
public class QuoteController {

	@Autowired
	private QuoteServiceClient quoteService;
	
    @RequestMapping("/quote")
    public Quote quote()
	{
    	Quote quote = quoteService.retrieveQuote();
    	return quote;
    }
}
