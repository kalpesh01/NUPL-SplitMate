package com.splitmate.controller;

import com.splitmate.dto.group_member.AddGroupMemberDto;
import com.splitmate.dto.group_member.GroupMemberInfoDto;
import com.splitmate.service.GroupMemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class GroupMemberController {

    private final GroupMemberService groupMemberService;

    public GroupMemberController(GroupMemberService groupMemberService) {
        this.groupMemberService = groupMemberService;
    }

    @PostMapping("/group/{groupId}/member")
    public GroupMemberInfoDto addMember(
            @PathVariable Long groupId,
            @RequestBody AddGroupMemberDto request) {
        return groupMemberService.add(groupId, request);
    }

    @GetMapping("/group/{groupId}/member")
    public List<GroupMemberInfoDto> getMembers(@PathVariable Long groupId) {
        return groupMemberService.get(groupId);
    }

    @DeleteMapping("/group/{groupId}/member/{memberId}")
    public void removeMember(
            @PathVariable Long groupId,
            @PathVariable Long memberId) {
        groupMemberService.remove(groupId, memberId);
    }
}

