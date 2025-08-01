package org.example.crud.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor // 기본 생성자를 생성
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키(PK) 생성을 데이터베이스에 위임
    private Long id;
    private String name;

    public Member(String name) {
        this.name = name; // id는 자동 생성되지만 name은 입력 값을 받아와야 함
        // 객체 자신의 name 속성에 매개변수 name의 값을 할당
    }

    public void updateName(String name) {
        this.name = name; // 수정(갱신)할 값을 할당
    }
}
