package org.jaeyo.repo;

import org.jaeyo.model.UserView;

public interface UserViewRepository {
	public void save(UserView userView);
	public void loadBySessionId(String sessionId);
}