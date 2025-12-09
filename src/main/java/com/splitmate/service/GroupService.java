package com.splitmate.service;

import com.splitmate.dto.request.GroupRequest;
import com.splitmate.dto.response.GroupResponse;
import com.splitmate.entity.Group;

import java.util.List;

public interface GroupService {
    GroupResponse createGroup(GroupRequest request);
    List<GroupResponse> getAllGroups();
    GroupResponse getGroupById(Long id);
    GroupResponse updateGroup(Long id, GroupRequest request);
    void deleteGroup(Long id);
}

