package com.splitmate.db.dao;

import com.splitmate.db.entity.GroupMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupMemberDAO extends JpaRepository<GroupMemberEntity, Long> {
    List<GroupMemberEntity> findByGroupEntity_Id(Long groupId);
}

