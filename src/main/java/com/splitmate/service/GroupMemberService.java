package com.splitmate.service;

import com.splitmate.dto.group_member.AddGroupMemberDto;
import com.splitmate.dto.group_member.GroupMemberInfoDto;

import java.util.List;

public interface GroupMemberService {
    GroupMemberInfoDto add(Long groupId, AddGroupMemberDto request);

    List<GroupMemberInfoDto> get(Long groupId);

    void remove(Long groupId, Long memberId);
}

