package com.tannd.commercemanager.repository;

import com.tannd.commercemanager.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CategoryRepository extends AbstractRepository<Category, Long> {
}
