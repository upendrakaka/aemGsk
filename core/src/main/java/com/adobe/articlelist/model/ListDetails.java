package com.adobe.articlelist.model;

public class ListDetails {
	private String image;
	private String title;
	private String desc;
	
	public ListDetails(String img , String title , String desc){
		super();
		this.image = img;
		this.title = title;
		this.desc = desc;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
}
