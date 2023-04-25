package com.kazakov.eventkeeper.mainservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kazakov.eventkeeper.mainservice.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
