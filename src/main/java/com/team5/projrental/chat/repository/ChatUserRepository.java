package com.team5.projrental.chat.repository;

import com.team5.projrental.entities.ChatUser;
import com.team5.projrental.entities.inheritance.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatUserRepository extends JpaRepository <ChatUser, Long> {
    Optional<ChatUser> findChatUserByUsers(Users LoginedUser);
}
