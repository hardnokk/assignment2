package org.example.member.dto;

import lombok.Getter;

@Getter
public class MemberResponse { // 데이터를 반환할 때 사용되는 객체
    private final Long id;
    private final String name;

    // 생성자 매개변수
    public MemberResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
