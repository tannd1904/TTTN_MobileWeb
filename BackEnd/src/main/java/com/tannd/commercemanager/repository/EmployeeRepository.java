package com.tannd.commercemanager.repository;

import com.tannd.commercemanager.model.Account;
import com.tannd.commercemanager.model.Employee;
import com.tannd.commercemanager.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends AbstractRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);

    @Query(value = "select count(k.MANV) " +
            "from nhanvien k " +
            "where month(k.created_at) = month (now()) " +
            "and year (k.created_at) = year (now()) ", nativeQuery = true)
    Long countEmployeeAddedInThisMonth();

    @Query(value = "select count(k.MANV) \n" +
            "from nhanvien k \n" +
            "where month(k.created_at) = :month " +
            "and year (k.created_at) = year (now()) ", nativeQuery = true
    )
    Long countEmployeeAddedInOneMonth(@Param("month") Integer month);
}
