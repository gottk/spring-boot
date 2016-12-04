package com.au.silverrail.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.au.silverrail.domain.User;

@Component
public class UserRepositoryImpl implements UserRepository {

	private Map<Integer, User> map = new HashMap<>();
	
	public UserRepositoryImpl() {
	
	}
	
	public Map<Integer, User> getMap() {
		return map;
	}
	
	public void setMap(Map<Integer, User> map) {
		this.map = map;
	}
	
	@Override
	public User getUserById(int id)
	{
		if(map.containsKey(id)) {
			return map.get(id);
		}
		else {
			User user = new User(id, "");
			map.put(id, user);
			return user;
		}
	}
	
	@Override
	public void updateUser(User user) {
		// update not necessary in this case
		// users.put(user.getId(), user);
	}
}