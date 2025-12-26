package com.splitmate.business.mapper;

import com.splitmate.db.dto.group_member.CreateGroupMemberDTO;
import com.splitmate.db.dto.group_member.GroupMemberInfoDTO;
import com.splitmate.db.entity.GroupEntity;
import com.splitmate.db.entity.GroupMemberEntity;
import com.splitmate.db.entity.UserEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GroupMemberMapper
{

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "groupEntity", source = "groupEntity")
    @Mapping(target = "user", source = "user")
    GroupMemberEntity mapDTOToEntity(CreateGroupMemberDTO dto, GroupEntity groupEntity, UserEntity user);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "groupEntity.id", target = "groupId")
    GroupMemberInfoDTO mapEntityToDTO(GroupMemberEntity entity);
}

