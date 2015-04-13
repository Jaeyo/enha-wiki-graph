package org.jaeyo.model;

public class WikiDocEdge {
	private String srcWikiDocTitle;
	private String destWikiDocTitle;

	public WikiDocEdge() {
	}

	public WikiDocEdge(String srcWikiDocTitle, String destWikiDocTitle) {
		this.srcWikiDocTitle = srcWikiDocTitle;
		this.destWikiDocTitle = destWikiDocTitle;
	}

	public String getSrcWikiDocTitle() {
		return srcWikiDocTitle;
	}

	public void setSrcWikiDocTitle(String srcWikiDocTitle) {
		this.srcWikiDocTitle = srcWikiDocTitle;
	}

	public String getDestWikiDocTitle() {
		return destWikiDocTitle;
	}

	public void setDestWikiDocTitle(String destWikiDocTitle) {
		this.destWikiDocTitle = destWikiDocTitle;
	}
} // class