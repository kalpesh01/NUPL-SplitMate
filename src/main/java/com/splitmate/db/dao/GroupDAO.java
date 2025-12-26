package com.splitmate.db.dao;

import com.splitmate.db.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupDAO extends JpaRepository<GroupEntity, Long> {
}

