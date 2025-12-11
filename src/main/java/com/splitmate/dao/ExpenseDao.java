package com.splitmate.dao;

import com.splitmate.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseDao extends JpaRepository<Expense, Long> {
}

