package com.splitmate.db.dao;

import com.splitmate.db.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseDAO extends JpaRepository<ExpenseEntity, Long> {
}

