package br.com.juniorcorp.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.juniorcorp.model.User;

public interface UserRepository  extends CrudRepository<User, Long>{
	User findByUserName(String userName);
}
