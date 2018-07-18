package com.aem.withquery;

import java.util.logging.Logger;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.felix.scr.annotations.Reference;
import org.apache.sling.api.resource.ResourceResolverFactory;

public class QueryWithNodeImpl implements Querywithnode {

	
	
	
	@Reference    ResourceResolverFactory resourceResolverFactory;
	
	@Override
	public String addQuery() {

		
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod("http://localhost:4502/bin/myQuery");
        //method.addRequestHeader("Authorization", "Bearer 3c28ad63e86140c59ff1845f4582f248");
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,    new DefaultHttpMethodRetryHandler(3, false));
 

        try {
          int statusCode = client.executeMethod(method);
          
          byte[] responseBody = method.getResponseBody();
          return new String(responseBody);
 
        } catch (Exception e) {
           // log.info("ERROR: " +e.getMessage());
        }
         
         
       return "There was an error invoking the servlet" ; 
    
	}

}
