package org.example.crud.controller;

import lombok.RequiredArgsConstructor;
import org.example.crud.dto.MemoRequest;
import org.example.crud.dto.MemoResponse;
import org.example.crud.service.MemberService;
import org.example.crud.service.MemoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {
    private final MemberService memberService;
    private final MemoService memoService;

    // 메모 등록
    @PostMapping("/memos")
    public MemoResponse createMemo(
            @RequestBody MemoRequest memoRequest
    ) {
        return memoService.createMemo(memoRequest);
    }

    // 메모 전체 조회
    @GetMapping("/memos")
    public List<MemoResponse> getMemos() {
        return memoService.findMemos();
    }

    // 메모 단건 조회
    @GetMapping("/memos/{memoId}")
    public MemoResponse getMemo(
            @PathVariable  Long memoId
    ) {
        return memoService.findMemo(memoId);
    }

    // 메모 단건 수정
    @PutMapping("memos/{memoId}")
    public MemoResponse updateMemo(
            @PathVariable Long memoId,
            @RequestBody MemoRequest memoRequest
    ) {
        return memoService.updateMemo(memoId, memoRequest);
    }

    // 메모 단건 삭제
    @DeleteMapping("memos/{memoId}")
    public void deleteMemo(
            @PathVariable Long memoId
    ) {
        memoService.deleteMemo(memoId);
    }
}
