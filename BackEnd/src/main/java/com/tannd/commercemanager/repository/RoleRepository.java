package com.tannd.commercemanager.repository;

import com.tannd.commercemanager.dto.RoleDTO;
import com.tannd.commercemanager.model.ERole;
import com.tannd.commercemanager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole role);
}
