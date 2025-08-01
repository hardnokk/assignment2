package org.example.crud.controller;

import lombok.RequiredArgsConstructor;
import org.example.crud.dto.UserRequest;
import org.example.crud.dto.UserResponse;
import org.example.crud.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 유저 생성
    @PostMapping("/users")
    public UserResponse createUser(
            @RequestBody UserRequest userRequest
    ) {
        return userService.createUser(userRequest);
    }

    // 유저 전체
    @GetMapping("/users")
    public List<UserResponse> getUsers() {
        return userService.findUsers();
    }

    // 유저 단일 조회
    @GetMapping("/users/{userId}")
    public UserResponse getUser(
            @PathVariable Long userId
    ) {
        return userService.findUser(userId);
    }

    // 유저 수정
    @PutMapping("/users/{userId}")
    public UserResponse updateUser(
            @PathVariable Long userId,
            @RequestBody UserRequest userRequest
    ) {
        return userService.updateUser(userId, userRequest);
    }

    // 유저 삭제
    @DeleteMapping("/users/{userId}")
    public void deleteUser(
            @PathVariable Long userId
    ) {
        userService.deleteUser(userId);
    }
}
