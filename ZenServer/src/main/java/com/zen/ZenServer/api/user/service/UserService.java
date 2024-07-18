package com.zen.ZenServer.api.user.service;

import com.zen.ZenServer.api.user.domain.User;
import com.zen.ZenServer.api.user.dto.UserDto;
import com.zen.ZenServer.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public UserDto.ProvideInfo getUserInfo() {
        User user = userRepository.findById(1L).get();
        return UserDto.ProvideInfo.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .leaf(user.getLeaf())
                .background_id(user.getBackground_id())
                .lastTestAt(user.getLastTestAt())
                .build();
    }

    public void changeBackground(Long backgroundId) {
        User user = userRepository.findById(1L).get();
        user.setBackground_id(backgroundId);
        userRepository.save(user);
    }

    public void addLeaf(Long leaf) {
        User user = userRepository.findById(1L).get();
        user.setLeaf(user.getLeaf() + leaf);
        userRepository.save(user);
    }
}