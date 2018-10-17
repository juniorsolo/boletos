package br.com.juniorcorp.service;

import br.com.juniorcorp.model.User;

public interface UserService {
	 public void save(User user);
	 public User findByUsername(String username);
}
