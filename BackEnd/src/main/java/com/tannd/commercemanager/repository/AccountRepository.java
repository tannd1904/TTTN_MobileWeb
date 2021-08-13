package com.tannd.commercemanager.repository;

import com.tannd.commercemanager.model.Account;
import com.tannd.commercemanager.model.Category;

import java.util.Optional;

public interface AccountRepository extends AbstractRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
}
