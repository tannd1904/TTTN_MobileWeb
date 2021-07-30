package com.tannd.commercemanager.repository;

import com.tannd.commercemanager.model.User;

import java.util.Optional;

public interface UserRepository extends AbstractRepository<User, Long>{
    Optional<User> findByEmail(String email);
}
