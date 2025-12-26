package com.splitmate.controller;

import com.splitmate.business.service.GroupMemberService;
import com.splitmate.db.dto.group_member.CreateGroupMemberDTO;
import com.splitmate.db.dto.group_member.GroupMemberInfoDTO;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/members")
@AllArgsConstructor
public class GroupMemberController
{

    private final GroupMemberService groupMemberService;

    @PostMapping("/group/{groupId}/member")
    public GroupMemberInfoDTO addMember(@PathVariable final Long groupId, @RequestBody final CreateGroupMemberDTO request)
    {
        return groupMemberService.add(groupId, request);
    }

    @GetMapping("/group/{groupId}/member")
    public List<GroupMemberInfoDTO> getMembers(@PathVariable final Long groupId)
    {
        return groupMemberService.get(groupId);
    }

    @DeleteMapping("/group/{groupId}/member/{memberId}")
    public void removeMember(@PathVariable final Long groupId, @PathVariable final Long memberId)
    {
        groupMemberService.remove(groupId, memberId);
    }
}

