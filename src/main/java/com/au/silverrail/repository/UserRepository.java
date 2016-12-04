package com.au.silverrail.repository;

import com.au.silverrail.domain.User;

public interface UserRepository {
	User getUserById(int id);	
	void updateUser(User user);
}