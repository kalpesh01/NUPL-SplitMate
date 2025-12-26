package com.splitmate.business.service.impl;

import com.splitmate.db.dto.group.CreateGroupDTO;
import com.splitmate.db.dto.group.GroupInfoDTO;
import com.splitmate.db.dto.group.UpdateGroupDTO;
import com.splitmate.db.entity.GroupEntity;
import com.splitmate.db.dao.GroupDAO;
import com.splitmate.enums.ErrorCodes;
import com.splitmate.exception.error.ResourceNotFoundException;
import com.splitmate.mapper.GroupMapper;
import com.splitmate.business.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupDAO groupDao;

    private final GroupMapper groupMapper;

    @Override
    public GroupInfoDTO create(final CreateGroupDTO createGroupDto) {

        GroupEntity groupEntity = groupMapper.dtoToEntity(createGroupDto);
        groupDao.save(groupEntity);
        return groupMapper.entityToDto(groupEntity);
    }

    @Override
    public List<GroupInfoDTO> getAll() {
        return groupDao.findAll().stream()
                .map(groupMapper::entityToDto)
                .toList();
    }

    @Override
    public GroupInfoDTO get(final Long id) {
        GroupEntity groupEntity = groupDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.GROUP_NOT_FOUND.getMessage()));
        return groupMapper.entityToDto(groupEntity);
    }

    @Override
    public GroupInfoDTO update(final Long id, final UpdateGroupDTO updateGroupDto) {
        GroupEntity groupEntity = groupDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.GROUP_NOT_FOUND.getMessage()));

        groupMapper.updateDtoToEntity(updateGroupDto, groupEntity);
        groupDao.save(groupEntity);

        return groupMapper.entityToDto(groupEntity);
    }

    @Override
    public void delete(final Long id) {
        groupDao.deleteById(id);
    }

    @Override
    public GroupEntity findById(final Long id) {
        return groupDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.GROUP_NOT_FOUND.getMessage()));
    }

}
