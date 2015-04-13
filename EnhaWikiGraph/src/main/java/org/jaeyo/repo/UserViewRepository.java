package org.jaeyo.repo;

import java.util.List;

import org.jaeyo.model.UserView;
import org.jaeyo.model.WikiDoc;

public interface UserViewRepository {
	public boolean save(UserView userView);
	public List<WikiDoc> loadBySessionId(String sessionId);
}