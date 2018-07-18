package com.adobe.gskservices;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.LoggerFactory;


@Component


@Service
 
public class DistanceImpl implements Distance {
	
	protected final Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
 
    @Override
    public String getDistance() {
        // TODO Auto-generated method stub
         
        try
        {
        	HttpClient client = new HttpClient();
              
        	HttpMethod getRequest = new GetMethod("http://10.100.100.41:8082/api/v1/product/productlist");
        	getRequest.addRequestHeader("Authorization", "Bearer 3c28ad63e86140c59ff1845f4582f248");
            getRequest.addRequestHeader("accept", "application/json");;
            getRequest.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,    new DefaultHttpMethodRetryHandler(3, false));
            
            
            int statusCode = client.executeMethod(getRequest);
          
 
            if (statusCode != HttpStatus.SC_OK) {
                log.info("HTTP Method failed: " + getRequest.getStatusLine());
              }
            byte[] responseBody = getRequest.getResponseBody();
             
         // httpClient.getConnectionManager().shutdown();
            return new String(responseBody);
        }
         
        catch (Exception e)
        {
            e.printStackTrace() ; 
        }
         
        return null;
    }
}