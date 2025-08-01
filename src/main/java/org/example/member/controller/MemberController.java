package org.example.member.controller;

import lombok.RequiredArgsConstructor;
import org.example.member.dto.MemberRequest;
import org.example.member.dto.MemberResponse;
import org.example.member.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller와 @ResponseBody를 합친 것
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members")
    public MemberResponse creatMember(
            @RequestBody MemberRequest memberRequest
    ) {
        return memberService.createMember(memberRequest);
    }

    @GetMapping("/members")
    public List<MemberResponse> getMembers() {
        return memberService.findMembers();
    }

    @GetMapping("/members/{memberId}")
    public MemberResponse getMember(
            @PathVariable Long memberId
    ) {
        return memberService.findMember(memberId);
    }

    @PutMapping("/members/{memberId}")
    public MemberResponse updateMember(
            @PathVariable Long memberId,
            @RequestBody MemberRequest memberRequest
    ) {
        return memberService.updateMember(memberId, memberRequest);
    }

    @DeleteMapping("/members/{memberId}")
    public void deleteMember(
            @PathVariable Long memberId
    ) {
        memberService.deleteMember(memberId);
    }
}
