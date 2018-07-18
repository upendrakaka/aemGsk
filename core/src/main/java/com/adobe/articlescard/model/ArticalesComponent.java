package com.adobe.articlescard.model;





import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Value;

import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;



public class ArticalesComponent {
	
	private List<CardDetails> cardList;
	
	public List<CardDetails> getCardList() {
		return cardList;
	}


	public void setCardList(List<CardDetails> cardList) {
		this.cardList = cardList;
	}


	public void getArticles(Node currentNode) throws RepositoryException, JSONException{
		this.cardList = new ArrayList<CardDetails>();
		String articlesTypeOne = "articlecardtypeone";
		if(currentNode.hasProperty(articlesTypeOne)){
		Property prop = currentNode.getProperty(articlesTypeOne);
		Value[] value = this.getValues(prop);
		for(int i=0;i<value.length;i++){
			String content  = value[i].getString();
			content = content.replaceAll("\n", "");
			content = content.replaceAll("\r", "");
			
			JSONObject jsonobj = new JSONObject(content);
			String imge = jsonobj.getString("articletileImage");
			String desc = jsonobj.getString("articletileDesc");
			String title = jsonobj.getString("articletileTitle");
			
			CardDetails carddet = new CardDetails(imge , title , desc);
			
			this.cardList.add(carddet);
			
			
		}
		
		}
	}
	
	
	public Value[] getValues(Property prop) throws RepositoryException{
		
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
