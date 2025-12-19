package com.splitmate.buissness.service;

import com.splitmate.db.dto.group_member.AddGroupMemberDto;
import com.splitmate.db.dto.group_member.GroupMemberInfoDto;

import java.util.List;

public interface GroupMemberService {
    GroupMemberInfoDto add(final Long groupId, final AddGroupMemberDto request);

    List<GroupMemberInfoDto> get(final Long groupId);

    void remove(final Long groupId, final Long memberId);
}

