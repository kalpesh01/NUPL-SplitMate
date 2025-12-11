package com.splitmate.service;

import com.splitmate.dto.group.CreateGroupDto;
import com.splitmate.dto.group.GroupInfoDto;
import com.splitmate.dto.group.UpdateGroupDto;
import com.splitmate.entity.Group;

import java.util.List;

public interface GroupService {
    GroupInfoDto create(CreateGroupDto request);

    List<GroupInfoDto> getAll();

    GroupInfoDto get(Long id);

    GroupInfoDto update(Long id, UpdateGroupDto request);

    void delete(Long id);

    Group findById(Long id);
}

