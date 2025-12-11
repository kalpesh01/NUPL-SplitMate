package com.splitmate.service.impl;

import com.splitmate.dao.GroupMemberDao;
import com.splitmate.dto.group_member.AddGroupMemberDto;
import com.splitmate.dto.group_member.GroupMemberInfoDto;
import com.splitmate.entity.Group;
import com.splitmate.entity.GroupMember;
import com.splitmate.entity.User;
import com.splitmate.service.GroupMemberService;
import com.splitmate.service.GroupService;
import com.splitmate.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupMemberServiceImpl implements GroupMemberService {

    private final GroupService groupService;
    private final UserService userService;
    private final GroupMemberDao memberRepository;

    public GroupMemberServiceImpl(GroupService groupService,
                                  UserService userService,
                                  GroupMemberDao memberRepository) {
        this.groupService = groupService;
        this.userService = userService;
        this.memberRepository = memberRepository;
    }

    @Override
    public GroupMemberInfoDto add(Long groupId, AddGroupMemberDto request) {

        Group group = groupService.findById(groupId);
        User user = userService.findById(request.getUserId());

        GroupMember member = new GroupMember();
        member.setGroup(group);
        member.setUser(user);

        memberRepository.save(member);
        return toResponse(member);
    }

    @Override
    public List<GroupMemberInfoDto> get(Long groupId) {

        List<GroupMember> members = memberRepository.findByGroupId(groupId);

        return members.stream()
                .map(this::toResponse)
                .toList();
    }


    @Override
    public void remove(Long groupId, Long memberId) {
        memberRepository.deleteById(memberId);
    }

    private GroupMemberInfoDto toResponse(GroupMember gm) {
        GroupMemberInfoDto res = new GroupMemberInfoDto();
        res.setId(gm.getId());
        res.setGroupId(gm.getGroup().getId());
        res.setUserId(gm.getUser().getId());
        return res;
    }
}

