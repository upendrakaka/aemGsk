package com.adobe.articlelist.model;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Value;

import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

public class ArticleListComponent {
private List<ListDetails> listDetails;


	public List<ListDetails> getListDetails() {
	return listDetails;
      }


   public void setListDetails(List<ListDetails> listDetails) {
	this.listDetails = listDetails;
       }


	public void getArticleslist(Node currentNode) throws RepositoryException, JSONException{
		this.listDetails = new ArrayList<ListDetails>();
		String articlesTypeOne = "myProductList";
		if(currentNode.hasProperty(articlesTypeOne)){
		Property prop = currentNode.getProperty(articlesTypeOne);
		Value[] value = this.getValuesd(prop);
		for(int i=0;i<value.length;i++){
			String content  = value[i].getString();
			content = content.replaceAll("\n", "");
			content = content.replaceAll("\r", "");
			
			JSONObject jsonobj = new JSONObject(content);
			String image = jsonobj.getString("productListImage");
			String desc = jsonobj.getString("productListDesc");
			String title = jsonobj.getString("productListHeading");
			
			ListDetails listdet = new ListDetails(image , title , desc);
			
			this.listDetails.add(listdet);
}
		
		}
	}
	
	
	public Value[] getValuesd(Property prop) throws RepositoryException{
		
		Value[] value = null;
	    if(prop.isMultiple()){
	    	value = prop.getValues();
	    }else {
	    	value = new Value[1];
	    	value[0] = prop.getValue();
	    }
		return value;
		
	}

}
