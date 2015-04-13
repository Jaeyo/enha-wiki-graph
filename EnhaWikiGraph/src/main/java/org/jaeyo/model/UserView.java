package org.jaeyo.model;

import java.sql.Date;
import java.util.List;

public class UserView {
	private String sessionId;
	private Date lastUpdated;
	private String viewedWikiDocsTitle;

	public UserView() {
	}

	public UserView(String sessionId, Date lastUpdated, String viewedWikiDocsTitle) {
		this.sessionId = sessionId;
		this.lastUpdated = lastUpdated;
		this.viewedWikiDocsTitle = viewedWikiDocsTitle;
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

	public String getViewedWikiDocsTitle() {
		return viewedWikiDocsTitle;
	}

	public void setViewedWikiDocsTitle(String viewedWikiDocsTitle) {
		this.viewedWikiDocsTitle = viewedWikiDocsTitle;
	}
} // class