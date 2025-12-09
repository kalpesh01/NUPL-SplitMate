package com.splitmate.service;

import com.splitmate.dto.request.AddMemberRequest;
import com.splitmate.dto.response.MemberResponse;

import java.util.List;

public interface GroupMemberService {
    MemberResponse addMember(Long groupId, AddMemberRequest request);
    List<MemberResponse> getMembers(Long groupId);
    void removeMember(Long groupId, Long memberId);
}

