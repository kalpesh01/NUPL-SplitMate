package com.splitmate.mapper;

import com.splitmate.dto.group.CreateGroupDto;
import com.splitmate.dto.group.GroupInfoDto;
import com.splitmate.dto.group.UpdateGroupDto;
import com.splitmate.entity.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring"
)
public interface GroupMapper {

    @Mapping(target = "id", ignore = true)
    Group dtoToEntity(CreateGroupDto dto);

    @Mapping(target = "id", ignore = true)
    void updateDtoToEntity(UpdateGroupDto dto,
                           @MappingTarget Group entity);

    GroupInfoDto entityToDto(Group entity);
}

