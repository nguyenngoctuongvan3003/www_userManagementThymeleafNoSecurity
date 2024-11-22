package iuh.fit.se.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iuh.fit.se.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> { 
	public Role findByName(String name);
}
