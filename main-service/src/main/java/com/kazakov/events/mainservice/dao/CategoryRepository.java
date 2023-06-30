package com.kazakov.events.mainservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kazakov.events.mainservice.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
