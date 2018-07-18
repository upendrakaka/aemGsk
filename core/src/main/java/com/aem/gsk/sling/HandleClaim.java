package com.aem.gsk.sling;


import java.io.IOException;

import java.rmi.ServerException;

  

import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import org.apache.sling.jcr.api.SlingRepository;





  
@SlingServlet(paths="/bin/mySearchServlet", methods = "GET", metatype=true)
@Property(name = "sling.auth.requirements", value = "-/bin/mySearchServlet")
public class HandleClaim extends org.apache.sling.api.servlets.SlingAllMethodsServlet {
     private static final long serialVersionUID = 2598426539166789515L;
       
     @Reference
     private SlingRepository repository;
       
     public void bindRepository(SlingRepository repository) {
            this.repository = repository; 
            }
            
       
     @Override
     protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServerException, IOException {
        
      try
      {
         //Define a String value to return
          String jsonData = "This is the Return Value from the doGET method in /bin/mySearchServlet";
           response.getWriter().write(jsonData);
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
    }
      
}