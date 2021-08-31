package com.tannd.commercemanager.repository;

import com.tannd.commercemanager.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends AbstractRepository<User, Long>{
    Optional<User> findByEmail(String email);

    @Query(value = "select * " +
            "from khachhang k " +
            "where month(now()) - month(k.created_at) < :n_month " , nativeQuery = true)
    List<User> findUserRegisterInMonthCurrent(@Param("n_month") Integer n_month);
}
