package com.team5.projrental.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BoardComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED", nullable = false)
    private String iboardComment;
}
