package com.splitmate.service.impl;

import com.splitmate.dto.group.CreateGroupDto;
import com.splitmate.dto.group.GroupInfoDto;
import com.splitmate.dto.group.UpdateGroupDto;
import com.splitmate.entity.Group;
import com.splitmate.dao.GroupDao;
import com.splitmate.service.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;

    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public GroupInfoDto create(CreateGroupDto request) {
        Group group = new Group();
        group.setName(request.getName());
        groupDao.save(group);
        return toResponse(group);
    }

    @Override
    public List<GroupInfoDto> getAll() {
        return groupDao.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public GroupInfoDto get(Long id) {
        Group group = groupDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        return toResponse(group);
    }

    @Override
    public GroupInfoDto update(Long id, UpdateGroupDto request) {
        Group group = groupDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        group.setName(request.getName());
        groupDao.save(group);

        return toResponse(group);
    }

    @Override
    public void delete(Long id) {
        groupDao.deleteById(id);
    }

    private GroupInfoDto toResponse(Group group) {
        GroupInfoDto res = new GroupInfoDto();
        res.setId(group.getId());
        res.setName(group.getName());
        return res;
    }
    @Override
    public Group findById(Long id) {
        return groupDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));
    }

}
