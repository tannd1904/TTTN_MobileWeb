package com.tannd.commercemanager.repository;

import com.tannd.commercemanager.model.Account;
import com.tannd.commercemanager.model.Employee;
import com.tannd.commercemanager.model.User;

import java.util.Optional;

public interface EmployeeRepository extends AbstractRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
}
