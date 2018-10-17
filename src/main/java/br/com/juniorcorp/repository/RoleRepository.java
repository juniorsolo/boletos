package br.com.juniorcorp.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.juniorcorp.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

}
