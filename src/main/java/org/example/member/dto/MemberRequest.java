package org.example.member.dto;

import lombok.Getter;

@Getter
public class MemberRequest { // 서버로 데이터를 전달할 때 사용되는 객체
    private String name;
    // *id는 DB에서 자동생성 되기때문에 넘어오는(지정해줄) 값은 name뿐이다
}
