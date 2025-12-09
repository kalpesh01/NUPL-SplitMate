package com.splitmate.service.impl;

import com.splitmate.dto.request.GroupRequest;
import com.splitmate.dto.response.GroupResponse;
import com.splitmate.entity.Group;
import com.splitmate.entity.GroupMember;
import com.splitmate.entity.User;
import com.splitmate.dao.GroupMemberRepository;
import com.splitmate.dao.GroupRepository;
import com.splitmate.dao.UserRepository;
import com.splitmate.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public GroupResponse createGroup(GroupRequest request) {
        Group group = new Group();
        group.setName(request.getName());
        groupRepository.save(group);
        return toResponse(group);
    }

    @Override
    public List<GroupResponse> getAllGroups() {
        return groupRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public GroupResponse getGroupById(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        return toResponse(group);
    }

    @Override
    public GroupResponse updateGroup(Long id, GroupRequest request) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        group.setName(request.getName());
        groupRepository.save(group);

        return toResponse(group);
    }

    @Override
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    private GroupResponse toResponse(Group group) {
        GroupResponse res = new GroupResponse();
        res.setId(group.getId());
        res.setName(group.getName());
        return res;
    }
}
