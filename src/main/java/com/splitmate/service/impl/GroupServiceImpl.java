package com.splitmate.service.impl;

import com.splitmate.dto.group.CreateGroupDto;
import com.splitmate.dto.group.GroupInfoDto;
import com.splitmate.dto.group.UpdateGroupDto;
import com.splitmate.entity.Group;
import com.splitmate.dao.GroupDao;
import com.splitmate.enums.ErrorCodes;
import com.splitmate.exception.error.ResourceNotFoundException;
import com.splitmate.mapper.GroupMapper;
import com.splitmate.service.GroupService;
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

        Group group = groupMapper.dtoToEntity(createGroupDto);
        groupDao.save(group);
        return groupMapper.entityToDto(group);
    }

    @Override
    public List<GroupInfoDto> getAll() {
        return groupDao.findAll().stream()
                .map(groupMapper::entityToDto)
                .toList();
    }

    @Override
    public GroupInfoDto get(final Long id) {
        Group group = groupDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.GROUP_NOT_FOUND.getMessage()));
        return groupMapper.entityToDto(group);
    }

    @Override
    public GroupInfoDto update(final Long id, final UpdateGroupDto updateGroupDto) {
        Group group = groupDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.GROUP_NOT_FOUND.getMessage()));

        groupMapper.updateDtoToEntity(updateGroupDto, group);
        groupDao.save(group);

        return groupMapper.entityToDto(group);
    }

    @Override
    public void delete(final Long id) {
        groupDao.deleteById(id);
    }

    @Override
    public Group findById(final Long id) {
        return groupDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.GROUP_NOT_FOUND.getMessage()));
    }

}
