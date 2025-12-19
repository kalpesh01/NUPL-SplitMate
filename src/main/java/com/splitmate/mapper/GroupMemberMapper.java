package com.splitmate.mapper;

import com.splitmate.dto.group_member.AddGroupMemberDto;
import com.splitmate.dto.group_member.GroupMemberInfoDto;
import com.splitmate.entity.Group;
import com.splitmate.entity.GroupMember;
import com.splitmate.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GroupMemberMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "group", source = "group")
    @Mapping(target = "user", source = "user")
    GroupMember dtoToEntity(AddGroupMemberDto dto, Group group, User user);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "group.id", target = "groupId")
    GroupMemberInfoDto entityToDto(GroupMember entity);
}

