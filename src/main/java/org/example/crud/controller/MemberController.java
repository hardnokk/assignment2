package org.example.crud.controller;

import lombok.RequiredArgsConstructor;
import org.example.crud.dto.MemberRequest;
import org.example.crud.dto.MemberResponse;
import org.example.crud.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller와 @ResponseBody를 합친 것
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 멤버 생성
    @PostMapping("/members")
    public MemberResponse creatMember(
            @RequestBody MemberRequest memberRequest
            // @RequestBody : JSON, XML 등 다양한 형식의 데이터를 서버에서 사용 가능한 객체로 자동 변환
    ) {
        return memberService.createMember(memberRequest);
    }

    // 멤버 전체 조회
    @GetMapping("/members")
    public List<MemberResponse> getMembers() {
        return memberService.findMembers();
    }

    // 멤버 단일 조회
    @GetMapping("/members/{memberId}")
    public MemberResponse getMember(
            @PathVariable Long memberId
            // @PathVariable : URI 경로에 포함된 변수 값을 메서드 매개변수에 바인딩
    ) {
        return memberService.findMember(memberId);
    }

    // 멤버 단일 수정
    @PutMapping("/members/{memberId}")
    public MemberResponse updateMember(
            @PathVariable Long memberId,
            @RequestBody MemberRequest memberRequest
    ) {
        return memberService.updateMember(memberId, memberRequest);
    }

    // 멤버 단일 삭제
    @DeleteMapping("/members/{memberId}")
    public void deleteMember(
            @PathVariable Long memberId
    ) {
        memberService.deleteMember(memberId);
    }
}
