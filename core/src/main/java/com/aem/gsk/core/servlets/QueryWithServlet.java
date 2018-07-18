package com.aem.gsk.core.servlets;

import java.io.IOException;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.jcr.api.SlingRepository;


@SlingServlet(paths="/bin/myQuery", methods = "GET", metatype=true)
@Property(name = "sling.auth.requirements", value = "-/bin/myQuery")
public class QueryWithServlet extends SlingAllMethodsServlet {
       
	
	 @Reference
	    private SlingRepository repository;
	      
	    public void bindRepository(SlingRepository repository) {
	           this.repository = repository; 
	           }
	    @Override
	    protected void doGet(final SlingHttpServletRequest req,
	            final SlingHttpServletResponse resp) throws ServletException, IOException {
	    	int total;
	    	Session session = req.getResource().getResourceResolver().adaptTo(Session.class);
	    	 resp.setContentType("application/json");
	    	 resp.setCharacterEncoding("UTF-8");
	    	 QueryManager queryManager;
			try {
				queryManager = session.getWorkspace().getQueryManager();
				String query = "SELECT node.artclTwoColsInfoDesc, 1 as position,node.[jcr:created] FROM [nt:unstructured] AS node where ISDESCENDANTNODE (node, '/content/GSK/en') AND node.[sling:resourceType] = 'GSK2App/components/content/article-two-columns'";
		    	Query queryr = queryManager.createQuery(query, "JCR-SQL2");
		    	QueryResult result=queryr.execute();
		    	NodeIterator nodeit = result.getNodes();
		    	total=(int)nodeit.getSize();
		    	while(nodeit.hasNext()){
		    		Node node = nodeit.nextNode();
		    		node.setProperty("servlet", "queryyy");
		    	}
		    	session.save();
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    }
}
