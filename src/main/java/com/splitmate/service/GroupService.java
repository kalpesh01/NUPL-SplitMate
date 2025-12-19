package com.splitmate.service;

import com.splitmate.dto.group.CreateGroupDto;
import com.splitmate.dto.group.GroupInfoDto;
import com.splitmate.dto.group.UpdateGroupDto;
import com.splitmate.entity.Group;

import java.util.List;

public interface GroupService {
    GroupInfoDto create(final CreateGroupDto request);

    List<GroupInfoDto> getAll();

    GroupInfoDto get(final Long id);

    GroupInfoDto update(final Long id, final UpdateGroupDto request);

    void delete(final Long id);

    Group findById(final Long id);
}

