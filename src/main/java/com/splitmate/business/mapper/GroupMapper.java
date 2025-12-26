package com.splitmate.business.mapper;

import com.splitmate.db.dto.group.CreateGroupDTO;
import com.splitmate.db.dto.group.GroupInfoDTO;
import com.splitmate.db.dto.group.UpdateGroupDTO;
import com.splitmate.db.entity.GroupEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GroupMapper
{

    @Mapping(target = "id", ignore = true)
    GroupEntity mapDTOToEntity(CreateGroupDTO dto);

    @Mapping(target = "id", ignore = true)
    void mapUpdateDTOToEntity(UpdateGroupDTO dto, @MappingTarget GroupEntity entity);

    GroupInfoDTO mapEntityToDTO(GroupEntity entity);
}

