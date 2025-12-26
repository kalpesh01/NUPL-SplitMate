package com.splitmate.business.service;

import com.splitmate.db.dto.group.CreateGroupDTO;
import com.splitmate.db.dto.group.GroupInfoDTO;
import com.splitmate.db.dto.group.UpdateGroupDTO;
import com.splitmate.db.entity.GroupEntity;

import java.util.List;

public interface GroupService {
    GroupInfoDTO create(final CreateGroupDTO request);

    List<GroupInfoDTO> getAll();

    GroupInfoDTO get(final Long id);

    GroupInfoDTO update(final Long id, final UpdateGroupDTO request);

    void delete(final Long id);

    GroupEntity findById(final Long id);
}

