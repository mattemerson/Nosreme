package com.nosreme.svc.quote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * http://www.baeldung.com/rest-template
 * @author memerson
 *
 */
@Service
public class QuoteServiceClient implements QuoteService
{
	private static final Logger log = LoggerFactory.getLogger(QuoteServiceClient.class);
	
	private final RestTemplate restTemplate;
	   
    public QuoteServiceClient(RestTemplateBuilder restTemplateBuilder)
    {
        restTemplate = restTemplateBuilder.build();
    }
	 	   
    @Override
	public Quote retrieveQuote()
	{
		Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
		log.info(quote.toString());		
		
		return quote;
	}

}
