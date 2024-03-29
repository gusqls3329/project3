package com.team5.projrental.entities;

import com.team5.projrental.entities.inheritance.Users;
import com.team5.projrental.entities.mappedsuper.BaseAt;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BoardComment extends BaseAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iboard_comment",nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "iboard", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Board board;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "iusers", nullable = false)
    private Users users;

    @Column(nullable = false)
    private String comment;

}
