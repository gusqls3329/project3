/*
package com.team5.projrental.chat.repository;

import com.team5.projrental.entities.Chat;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface ChatRepository extends JpaRepository {
    @EntityGraph(attributePaths = {"chat"})
    List<Chat> findAllOrderByLastMsgAtDesc(Pageable pageable);
}
*/
