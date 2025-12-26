package com.splitmate.business.service.impl;

import com.splitmate.business.mapper.GroupMemberMapper;
import com.splitmate.business.service.GroupMemberService;
import com.splitmate.business.service.GroupService;
import com.splitmate.business.service.UserService;
import com.splitmate.db.dao.GroupMemberDAO;
import com.splitmate.db.dto.group_member.CreateGroupMemberDTO;
import com.splitmate.db.dto.group_member.GroupMemberInfoDTO;
import com.splitmate.db.entity.GroupEntity;
import com.splitmate.db.entity.GroupMemberEntity;
import com.splitmate.db.entity.UserEntity;

import org.springframework.stereotype.Service;

import java.util.List;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GroupMemberServiceImpl implements GroupMemberService
{

    private final GroupService groupService;
    private final UserService userService;
    private final GroupMemberDAO groupMemberDao;
    private final GroupMemberMapper groupMemberMapper;

    @Override
    public GroupMemberInfoDTO add(final Long groupId, final CreateGroupMemberDTO createGroupMemberDto)
    {

        GroupEntity groupEntity = groupService.findById(groupId);
        UserEntity user = userService.findById(createGroupMemberDto.getUserId());
        GroupMemberEntity groupMemberEntity = groupMemberMapper.mapDTOToEntity(createGroupMemberDto, groupEntity, user);
        groupMemberDao.save(groupMemberEntity);

        return groupMemberMapper.mapEntityToDTO(groupMemberEntity);
    }

    @Override
    public List<GroupMemberInfoDTO> get(final Long groupId)
    {

        List<GroupMemberEntity> members = groupMemberDao.findByGroupEntity_Id(groupId);

        return members.stream().map(groupMemberMapper::mapEntityToDTO).toList();
    }

    @Override
    public void remove(final Long groupId, final Long memberId)
    {
        groupMemberDao.deleteById(memberId);
    }
}

