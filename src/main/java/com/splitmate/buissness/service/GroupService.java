package com.splitmate.buissness.service;

import com.splitmate.db.dto.group.CreateGroupDto;
import com.splitmate.db.dto.group.GroupInfoDto;
import com.splitmate.db.dto.group.UpdateGroupDto;
import com.splitmate.db.entity.GroupEntity;

import java.util.List;

public interface GroupService {
    GroupInfoDto create(final CreateGroupDto request);

    List<GroupInfoDto> getAll();

    GroupInfoDto get(final Long id);

    GroupInfoDto update(final Long id, final UpdateGroupDto request);

    void delete(final Long id);

    GroupEntity findById(final Long id);
}

