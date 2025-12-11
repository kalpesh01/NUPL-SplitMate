package com.splitmate.dao;

import com.splitmate.entity.ExpenseSplit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseSplitDao extends JpaRepository<ExpenseSplit, Long> {
}

