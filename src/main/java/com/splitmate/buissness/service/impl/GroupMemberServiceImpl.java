package com.splitmate.buissness.service.impl;

import com.splitmate.db.dao.GroupMemberDao;
import com.splitmate.db.dto.group_member.AddGroupMemberDto;
import com.splitmate.db.dto.group_member.GroupMemberInfoDto;
import com.splitmate.db.entity.GroupEntity;
import com.splitmate.db.entity.GroupMemberEntity;
import com.splitmate.db.entity.UserEntity;
import com.splitmate.mapper.GroupMemberMapper;
import com.splitmate.buissness.service.GroupMemberService;
import com.splitmate.buissness.service.GroupService;
import com.splitmate.buissness.service.UserService;
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

        GroupEntity groupEntity = groupService.findById(groupId);
        UserEntity user = userService.findById(addGroupMemberDto.getUserId());
        GroupMemberEntity groupMemberEntity = groupMemberMapper.dtoToEntity(addGroupMemberDto, groupEntity, user);
        groupMemberDao.save(groupMemberEntity);

        return groupMemberMapper.entityToDto(groupMemberEntity);
    }

    @Override
    public List<GroupMemberInfoDto> get(final Long groupId) {

        List<GroupMemberEntity> members = groupMemberDao.findByGroupEntity_Id(groupId);

        return members.stream()
                .map(groupMemberMapper::entityToDto)
                .toList();
    }

    @Override
    public void remove(final Long groupId, final Long memberId) {
        groupMemberDao.deleteById(memberId);
    }
}

