package com.splitmate.controller;

import com.splitmate.dto.group.CreateGroupDto;
import com.splitmate.dto.group.GroupInfoDto;
import com.splitmate.dto.group.UpdateGroupDto;
import com.splitmate.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService service) {
        this.groupService = service;
    }

    @PostMapping
    public GroupInfoDto create(@RequestBody CreateGroupDto request) {
        return groupService.create(request);
    }

    @GetMapping
    public List<GroupInfoDto> getAll() {
        return groupService.getAll();
    }

    @GetMapping("/{groupId}")
    public GroupInfoDto getById(@PathVariable Long groupId) {
        return groupService.get(groupId);
    }

    @PutMapping("/{groupId}")
    public GroupInfoDto update(
            @PathVariable Long groupId,
            @RequestBody UpdateGroupDto request) {
        return groupService.update(groupId, request);
    }

    @DeleteMapping("/{groupId}")
    public void delete(@PathVariable Long groupId) {
        groupService.delete(groupId);
    }
}
