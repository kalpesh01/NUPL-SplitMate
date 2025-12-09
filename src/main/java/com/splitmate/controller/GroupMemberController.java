package com.splitmate.controller;

import com.splitmate.dto.request.AddMemberRequest;
import com.splitmate.dto.response.MemberResponse;
import com.splitmate.service.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class GroupMemberController {

    @Autowired
    private GroupMemberService service;

    @PostMapping("/group/{groupId}/member")
    public ResponseEntity<MemberResponse> addMember(
            @PathVariable Long groupId,
            @RequestBody AddMemberRequest request) {
        return ResponseEntity.ok(service.addMember(groupId, request));
    }

    @GetMapping("/group/{groupId}/member")
    public ResponseEntity<List<MemberResponse>> getMembers(@PathVariable Long groupId) {
        return ResponseEntity.ok(service.getMembers(groupId));
    }

    @DeleteMapping("/group/{groupId}/member/{memberId}")
    public ResponseEntity<Void> removeMember(
            @PathVariable Long groupId,
            @PathVariable Long memberId) {
        service.removeMember(groupId, memberId);
        return ResponseEntity.noContent().build();
    }
}

