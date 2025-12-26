package com.splitmate.business.service.impl;

import com.splitmate.business.mapper.GroupMapper;
import com.splitmate.business.service.GroupService;
import com.splitmate.db.dao.GroupDAO;
import com.splitmate.db.dto.group.CreateGroupDTO;
import com.splitmate.db.dto.group.GroupInfoDTO;
import com.splitmate.db.dto.group.UpdateGroupDTO;
import com.splitmate.db.entity.GroupEntity;
import com.splitmate.enums.ErrorCodes;
import com.splitmate.exception.error.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService
{

    private final GroupDAO groupDao;

    private final GroupMapper groupMapper;

    @Override
    public GroupInfoDTO create(final CreateGroupDTO createGroupDto)
    {

        GroupEntity groupEntity = groupMapper.mapDTOToEntity(createGroupDto);
        groupDao.save(groupEntity);
        return groupMapper.mapEntityToDTO(groupEntity);
    }

    @Override
    public List<GroupInfoDTO> getAll()
    {
        return groupDao.findAll().stream().map(groupMapper::mapEntityToDTO).toList();
    }

    @Override
    public GroupInfoDTO get(final Long id)
    {
        GroupEntity groupEntity = groupDao.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.GROUP_NOT_FOUND.getMessage()));
        return groupMapper.mapEntityToDTO(groupEntity);
    }

    @Override
    public GroupInfoDTO update(final Long id, final UpdateGroupDTO updateGroupDto)
    {
        GroupEntity groupEntity = groupDao.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.GROUP_NOT_FOUND.getMessage()));

        groupMapper.mapUpdateDTOToEntity(updateGroupDto, groupEntity);
        groupDao.save(groupEntity);

        return groupMapper.mapEntityToDTO(groupEntity);
    }

    @Override
    public void delete(final Long id)
    {
        groupDao.deleteById(id);
    }

    @Override
    public GroupEntity findById(final Long id)
    {
        return groupDao.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.GROUP_NOT_FOUND.getMessage()));
    }

}
