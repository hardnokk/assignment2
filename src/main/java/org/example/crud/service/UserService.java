package org.example.crud.service;

import lombok.RequiredArgsConstructor;
import org.example.crud.dto.UserRequest;
import org.example.crud.dto.UserResponse;
import org.example.crud.entity.User;
import org.example.crud.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        User createUser = userRepository.save(new User(userRequest.getName(), userRequest.getPassword()));
        return new UserResponse(createUser.getId(), createUser.getName(), createUser.getPassword());
    }

    @Transactional(readOnly = true)
    public List<UserResponse> findUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> dtos = new ArrayList<>();

        for(User user : users) {
            UserResponse userResponse = new UserResponse(
                    user.getId(),
                    user.getName(),
                    user.getPassword()
            );
            dtos.add(userResponse);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public UserResponse findUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        ) ;
        return new UserResponse(user.getId(), user.getName(), user.getPassword());
    }

    @Transactional
    public UserResponse updateUser(Long userId, UserRequest userRequest) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );
        user.updateUser(userRequest.getName(), userRequest.getPassword());
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getPassword()
        );
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );
        userRepository.deleteById(userId);
    }
}
