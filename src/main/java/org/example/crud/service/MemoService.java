package org.example.crud.service;

import lombok.RequiredArgsConstructor;
import org.example.crud.dto.MemoRequest;
import org.example.crud.dto.MemoResponse;
import org.example.crud.entity.Memo;
import org.example.crud.repository.MemoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponse createMemo(MemoRequest memoRequest) {
        Memo createMemo = memoRepository.save(new Memo(memoRequest.getContent()));
        return new MemoResponse(createMemo.getId(), createMemo.getContent());
    }

    @Transactional(readOnly = true)
    public List<MemoResponse> findMemos() {
        List<Memo> memos = memoRepository.findAll();
        List<MemoResponse> dtos = new ArrayList<>();

        for (Memo memo : memos) {
            MemoResponse memoResponse = new MemoResponse(
                    memo.getId(),
                    memo.getContent()
            );
            dtos.add(memoResponse);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public MemoResponse findMemo(Long memoId) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalArgumentException("그런 메모는 존재하지 않습니다.")
        );
        return new MemoResponse(
                memo.getId(),
                memo.getContent()
        );
    }

    @Transactional
    public MemoResponse updateMemo(Long memoId,  MemoRequest memoRequest) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalArgumentException("그런 메모는 존재하지 않습니다.")
        );
        memo.updateMemo(memoRequest.getContent());
        return new MemoResponse(
                memo.getId(),
                memo.getContent()
        );
    }

    @Transactional
    public void deleteMemo(Long memoId) {
        boolean b = memoRepository.existsById(memoId);
        if (!b) {
            throw new IllegalArgumentException("그런 메모는 존재하지 않습니다.");
        }
        memoRepository.deleteById(memoId);
    }
}
