package com.adobe.articlescard.controller;

import java.util.List;

import javax.jcr.Node;

import com.adobe.articlescard.model.ArticalesComponent;
import com.adobe.articlescard.model.CardDetails;
import com.adobe.cq.sightly.WCMUsePojo;

public class ArticalesCardController extends WCMUsePojo {
	
	public ArticalesComponent articalesComponent;

	@Override
	public void activate() throws Exception {
		try{
			Node nodeController = getResource().adaptTo(Node.class);
			articalesComponent = new ArticalesComponent();
			articalesComponent.getArticles(nodeController);
		}catch(Exception e){
			e.printStackTrace();
		} 
		
	}

	public List<CardDetails> getArticalesComponent() {
		return this.articalesComponent.getCardList();
	}
	
	

}
