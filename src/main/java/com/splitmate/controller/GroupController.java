package com.splitmate.controller;

import com.splitmate.db.dto.group.CreateGroupDTO;
import com.splitmate.db.dto.group.GroupInfoDTO;
import com.splitmate.db.dto.group.UpdateGroupDTO;
import com.splitmate.business.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@AllArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public GroupInfoDTO create(@RequestBody final CreateGroupDTO request) {
        return groupService.create(request);
    }

    @GetMapping
    public List<GroupInfoDTO> getAll() {
        return groupService.getAll();
    }

    @GetMapping("/{groupId}")
    public GroupInfoDTO getById(@PathVariable final Long groupId) {
        return groupService.get(groupId);
    }

    @PutMapping("/{groupId}")
    public GroupInfoDTO update(
            @PathVariable final Long groupId,
            @RequestBody final UpdateGroupDTO request) {
        return groupService.update(groupId, request);
    }

    @DeleteMapping("/{groupId}")
    public void delete(@PathVariable final Long groupId) {
        groupService.delete(groupId);
    }
}
