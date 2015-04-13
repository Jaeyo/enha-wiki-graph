package org.jaeyo.wiki;

import org.jaeyo.model.WikiDoc;

public interface WikiDocLoader {
	public WikiDoc load(String title);
}