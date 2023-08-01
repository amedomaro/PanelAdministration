package admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import admin.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
