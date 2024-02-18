package com.team5.projrental.entities;

import com.team5.projrental.entities.mappedsuper.CreatedAt;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BoardPic extends CreatedAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ipics")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "iboard", columnDefinition = "BIGINT UNSIGNED")
    private Board board;

    @Column(length = 100, nullable = false)
    private String storedPic;

}
