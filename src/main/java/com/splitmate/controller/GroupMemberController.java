package com.splitmate.controller;

import com.splitmate.db.dto.group_member.AddGroupMemberDTO;
import com.splitmate.db.dto.group_member.GroupMemberInfoDTO;
import com.splitmate.business.service.GroupMemberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@AllArgsConstructor
public class GroupMemberController {

    private final GroupMemberService groupMemberService;

    @PostMapping("/group/{groupId}/member")
    public GroupMemberInfoDTO addMember(
            @PathVariable final Long groupId,
            @RequestBody final AddGroupMemberDTO request) {
        return groupMemberService.add(groupId, request);
    }

    @GetMapping("/group/{groupId}/member")
    public List<GroupMemberInfoDTO> getMembers(@PathVariable final Long groupId) {
        return groupMemberService.get(groupId);
    }

    @DeleteMapping("/group/{groupId}/member/{memberId}")
    public void removeMember(
            @PathVariable final Long groupId,
            @PathVariable final Long memberId) {
        groupMemberService.remove(groupId, memberId);
    }
}

