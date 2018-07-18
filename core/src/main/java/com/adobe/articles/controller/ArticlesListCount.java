package com.adobe.articles.controller;

import java.util.List;

import javax.jcr.Node;

import com.adobe.articlelist.model.ArticleListComponent;
import com.adobe.articlelist.model.ListDetails;
import com.adobe.cq.sightly.WCMUsePojo;

public class ArticlesListCount extends WCMUsePojo {

	public ArticleListComponent articleList;
	
	@Override
	public void activate() throws Exception {
		Node nodeController = getResource().adaptTo(Node.class);
		articleList = new ArticleListComponent();
		articleList.getArticleslist(nodeController);
}

	public List<ListDetails> getArticleList() {
		return this.articleList.getListDetails();
	}

	
	
	
	

}
