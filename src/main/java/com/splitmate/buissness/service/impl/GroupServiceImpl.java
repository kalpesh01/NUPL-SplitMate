package com.splitmate.buissness.service.impl;

import com.splitmate.db.dto.group.CreateGroupDto;
import com.splitmate.db.dto.group.GroupInfoDto;
import com.splitmate.db.dto.group.UpdateGroupDto;
import com.splitmate.db.entity.GroupEntity;
import com.splitmate.db.dao.GroupDao;
import com.splitmate.enums.ErrorCodes;
import com.splitmate.exception.error.ResourceNotFoundException;
import com.splitmate.mapper.GroupMapper;
import com.splitmate.buissness.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;

    private final GroupMapper groupMapper;

    @Override
    public GroupInfoDto create(final CreateGroupDto createGroupDto) {

        GroupEntity groupEntity = groupMapper.dtoToEntity(createGroupDto);
        groupDao.save(groupEntity);
        return groupMapper.entityToDto(groupEntity);
    }

    @Override
    public List<GroupInfoDto> getAll() {
        return groupDao.findAll().stream()
                .map(groupMapper::entityToDto)
                .toList();
    }

    @Override
    public GroupInfoDto get(final Long id) {
        GroupEntity groupEntity = groupDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.GROUP_NOT_FOUND.getMessage()));
        return groupMapper.entityToDto(groupEntity);
    }

    @Override
    public GroupInfoDto update(final Long id, final UpdateGroupDto updateGroupDto) {
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
