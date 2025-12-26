package com.splitmate.mapper;

import com.splitmate.db.dto.group_member.AddGroupMemberDto;
import com.splitmate.db.dto.group_member.GroupMemberInfoDto;
import com.splitmate.db.entity.GroupEntity;
import com.splitmate.db.entity.GroupMemberEntity;
import com.splitmate.db.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GroupMemberMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "groupEntity", source = "groupEntity")
    @Mapping(target = "user", source = "user")
    GroupMemberEntity dtoToEntity(AddGroupMemberDto dto, GroupEntity groupEntity, UserEntity user);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "groupEntity.id", target = "groupId")
    GroupMemberInfoDto entityToDto(GroupMemberEntity entity);
}

