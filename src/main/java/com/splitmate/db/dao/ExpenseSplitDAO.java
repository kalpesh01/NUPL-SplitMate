package com.splitmate.db.dao;

import com.splitmate.db.entity.ExpenseSplitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseSplitDAO extends JpaRepository<ExpenseSplitEntity, Long> {
}

