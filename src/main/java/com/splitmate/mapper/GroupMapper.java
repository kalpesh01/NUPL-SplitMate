package com.splitmate.mapper;

import com.splitmate.db.dto.group.CreateGroupDto;
import com.splitmate.db.dto.group.GroupInfoDto;
import com.splitmate.db.dto.group.UpdateGroupDto;
import com.splitmate.db.entity.GroupEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring"
)
public interface GroupMapper {

    @Mapping(target = "id", ignore = true)
    GroupEntity dtoToEntity(CreateGroupDto dto);

    @Mapping(target = "id", ignore = true)
    void updateDtoToEntity(UpdateGroupDto dto,
                           @MappingTarget GroupEntity entity);

    GroupInfoDto entityToDto(GroupEntity entity);
}

