package com.splitmate.controller;

import com.splitmate.db.dto.group_member.AddGroupMemberDto;
import com.splitmate.db.dto.group_member.GroupMemberInfoDto;
import com.splitmate.buissness.service.GroupMemberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@AllArgsConstructor
public class GroupMemberController {

    private final GroupMemberService groupMemberService;

    @PostMapping("/group/{groupId}/member")
    public GroupMemberInfoDto addMember(
            @PathVariable final Long groupId,
            @RequestBody final AddGroupMemberDto request) {
        return groupMemberService.add(groupId, request);
    }

    @GetMapping("/group/{groupId}/member")
    public List<GroupMemberInfoDto> getMembers(@PathVariable final Long groupId) {
        return groupMemberService.get(groupId);
    }

    @DeleteMapping("/group/{groupId}/member/{memberId}")
    public void removeMember(
            @PathVariable final Long groupId,
            @PathVariable final Long memberId) {
        groupMemberService.remove(groupId, memberId);
    }
}

