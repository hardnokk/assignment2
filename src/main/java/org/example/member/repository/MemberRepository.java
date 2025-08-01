package org.example.member.repository;

import org.example.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 인터페이스를 상속받아 Member 엔티티에 대한 데이터 접근(CRUD) 기능을 사용
    // 엔티티 타입으로 Member 클래스와 ID는 엔티티의 기본 키 타입 (Long - Member 엔티티의 ID 필드 타입) 지정
}
