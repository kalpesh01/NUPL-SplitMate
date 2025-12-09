package com.splitmate.service.impl;

import com.splitmate.dao.GroupMemberRepository;
import com.splitmate.dao.GroupRepository;
import com.splitmate.dao.UserRepository;
import com.splitmate.dto.request.AddMemberRequest;
import com.splitmate.dto.response.MemberResponse;
import com.splitmate.entity.Group;
import com.splitmate.entity.GroupMember;
import com.splitmate.entity.User;
import com.splitmate.service.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupMemberServiceImpl implements GroupMemberService {

    @Autowired
    private  GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupMemberRepository memberRepository;

    @Override
    public MemberResponse addMember(Long groupId, AddMemberRequest request) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        GroupMember member = new GroupMember();
        member.setGroup(group);
        member.setUser(user);

        memberRepository.save(member);
        return toResponse(member);
    }

    @Override
    public List<MemberResponse> getMembers(Long groupId) {

        List<GroupMember> members = memberRepository.findByGroupId(groupId);

        return members.stream()
                .map(this::toResponse)
                .toList();
    }


    @Override
    public void removeMember(Long groupId, Long memberId) {
        memberRepository.deleteById(memberId);
    }

    private MemberResponse toResponse(GroupMember gm) {
        MemberResponse res = new MemberResponse();
        res.setId(gm.getId());
        res.setGroupId(gm.getGroup().getId());
        res.setUserId(gm.getUser().getId());
        return res;
    }
}

