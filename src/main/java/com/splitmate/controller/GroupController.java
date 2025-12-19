package com.splitmate.controller;

import com.splitmate.dto.group.CreateGroupDto;
import com.splitmate.dto.group.GroupInfoDto;
import com.splitmate.dto.group.UpdateGroupDto;
import com.splitmate.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@AllArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public GroupInfoDto create(@RequestBody final CreateGroupDto request) {
        return groupService.create(request);
    }

    @GetMapping
    public List<GroupInfoDto> getAll() {
        return groupService.getAll();
    }

    @GetMapping("/{groupId}")
    public GroupInfoDto getById(@PathVariable final Long groupId) {
        return groupService.get(groupId);
    }

    @PutMapping("/{groupId}")
    public GroupInfoDto update(
            @PathVariable final Long groupId,
            @RequestBody final UpdateGroupDto request) {
        return groupService.update(groupId, request);
    }

    @DeleteMapping("/{groupId}")
    public void delete(@PathVariable final Long groupId) {
        groupService.delete(groupId);
    }
}
