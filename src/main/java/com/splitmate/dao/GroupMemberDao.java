package com.splitmate.dao;

import com.splitmate.entity.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupMemberDao extends JpaRepository<GroupMember, Long> {
    List<GroupMember> findByGroupId(Long groupId);
}

