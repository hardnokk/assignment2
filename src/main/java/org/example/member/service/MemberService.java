package org.example.member.service;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.member.dto.MemberRequest;
import org.example.member.dto.MemberResponse;
import org.example.member.entity.Member;
import org.example.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor // final 필드나 @NonNull 어노테이션이 붙은 필드를 인자로 받는 생성자를 자동으로 생성
public class MemberService {
    private final MemberRepository memberRepository;
    // MemberRepository 타입의 memberRepository 필드를 선언
    // private : 클래스 외부에서 접근 불가(캡슐화)
    // final : 해당 필드를 외부에서 변경 불가, 불변성을 보장하며 예상치 못한 변경을 방지

    @Transactional // 작업을 하나의 단위로 묶어 처리
    public MemberResponse createMember(MemberRequest memberRequest) {
        // 멤버를 생성(저장)하기 위해 리퀘스트 형식의 데이터를 가져 와 리스폰스 해줌
        Member createMember = memberRepository.save(new Member(memberRequest.getName()));
        // Repository.save : 엔티티 저장, Spring Data JPA의 규칙
        return new MemberResponse(createMember.getId(), createMember.getName());
    }

    @Transactional(readOnly = true) // 해당 트랜잭션이 데이터를 변경하지 않는다’를 명시
    public List<MemberResponse> findMembers() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponse> dtos = new ArrayList<>();

        for (Member member : members) {
            MemberResponse memberResponse = new MemberResponse(
                    member.getId(),
                    member.getName()
            ); // Member 객체에서 ID와 이름만 추출해 응답 형식에 맞게 포장
            dtos.add(memberResponse);
        }
        return dtos;
    }

    @Transactional
    public MemberResponse updateMember(Long memberId, MemberRequest memberRequest) {
        // 해당하는 1명의 멤버를 찾기 위한 ID(Long 타입)와 수정 내용을 리퀘스트에 지정된 형태로 받아옴
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("해당 멤버 ID는 존재하지 않습니다.")
        );
        member.updateName(memberRequest.getName());
        return new MemberResponse(
                member.getId(),
                member.getName()
        );
    }
    
    @Transactional
    public void deleteMember(Long memberId) { // void : 아무런 값을 반환하지 않기 때문에 작성
        boolean b = memberRepository.existsById(memberId);
        if (!b) {
            throw new IllegalArgumentException("해당 멤버 ID는 존재하지 않습니다.");
        }
        memberRepository.deleteById(memberId);
    }

    @Transactional
    public MemberResponse findMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("해당 멤버 ID는 존재하지 않습니다.")
        );
        return new MemberResponse(member.getId(), member.getName());
    }
}