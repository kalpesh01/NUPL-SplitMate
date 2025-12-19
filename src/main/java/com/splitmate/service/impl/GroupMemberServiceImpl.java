package com.splitmate.service.impl;

import com.splitmate.dao.GroupMemberDao;
import com.splitmate.dto.group_member.AddGroupMemberDto;
import com.splitmate.dto.group_member.GroupMemberInfoDto;
import com.splitmate.entity.Group;
import com.splitmate.entity.GroupMember;
import com.splitmate.entity.User;
import com.splitmate.mapper.GroupMemberMapper;
import com.splitmate.service.GroupMemberService;
import com.splitmate.service.GroupService;
import com.splitmate.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupMemberServiceImpl implements GroupMemberService {

    private final GroupService groupService;
    private final UserService userService;
    private final GroupMemberDao groupMemberDao;
    private final GroupMemberMapper groupMemberMapper;

    @Override
    public GroupMemberInfoDto add(final Long groupId, final AddGroupMemberDto addGroupMemberDto) {

        Group group = groupService.findById(groupId);
        User user = userService.findById(addGroupMemberDto.getUserId());
        GroupMember groupMember = groupMemberMapper.dtoToEntity(addGroupMemberDto, group, user);
        groupMemberDao.save(groupMember);

        return groupMemberMapper.entityToDto(groupMember);
    }

    @Override
    public List<GroupMemberInfoDto> get(final Long groupId) {

        List<GroupMember> members = groupMemberDao.findByGroupId(groupId);

        return members.stream()
                .map(groupMemberMapper::entityToDto)
                .toList();
    }

    @Override
    public void remove(final Long groupId, final Long memberId) {
        groupMemberDao.deleteById(memberId);
    }
}

