package com.nosreme.sp.quote;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.nosreme.sp.quote.protocol.rest,com.nosreme.svc.quote")
public class QuoteConfiguration {

}
