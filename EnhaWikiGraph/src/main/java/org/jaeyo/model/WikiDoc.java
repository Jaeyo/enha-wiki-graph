package org.jaeyo.model;

public class WikiDoc {
	private String title;
	private String contentHtml;
	private String contentHash;

	public WikiDoc() {
	}
	
	public WikiDoc(String title, String contentHtml, String contentHash) {
		this.title = title;
		this.contentHtml = contentHtml;
		this.contentHash = contentHash;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContentHtml() {
		return contentHtml;
	}

	public void setContentHtml(String contentHtml) {
		this.contentHtml = contentHtml;
	}

	public String getContentHash() {
		return contentHash;
	}

	public void setContentHash(String contentHash) {
		this.contentHash = contentHash;
	}
} // class