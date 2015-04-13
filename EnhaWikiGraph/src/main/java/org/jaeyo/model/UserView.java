package org.jaeyo.model;

import java.util.Date;

public class UserView {
	private String sessionId;
	private Date lastUpdated;
	private String wikiDocTitle;

	public UserView() {
	}

	public UserView(String sessionId, Date lastUpdated, String wikiDocTitle) {
		this.sessionId = sessionId;
		this.lastUpdated = lastUpdated;
		this.wikiDocTitle = wikiDocTitle;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getWikiDocTitle() {
		return wikiDocTitle;
	}

	public void setWikiDocTitle(String wikiDocTitle) {
		this.wikiDocTitle = wikiDocTitle;
	}
} // class