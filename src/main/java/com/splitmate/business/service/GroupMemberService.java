package com.splitmate.business.service;

import com.splitmate.db.dto.group_member.AddGroupMemberDTO;
import com.splitmate.db.dto.group_member.GroupMemberInfoDTO;

import java.util.List;

public interface GroupMemberService {
    GroupMemberInfoDTO add(final Long groupId, final AddGroupMemberDTO request);

    List<GroupMemberInfoDTO> get(final Long groupId);

    void remove(final Long groupId, final Long memberId);
}

