package com.team5.projrental.entities;


import com.team5.projrental.entities.inheritance.Users;
import com.team5.projrental.entities.mappedsuper.CreatedAt;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/*@Getter
@Setter
@Entity
public class BoardLike extends CreatedAt {
    @EmbeddedId
    private BoardLikeIds boardLikeIds;

    @ManyToOne
    @MapsId("iuser")
    @JoinColumn(name = "iuser", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Users users;

    @ManyToOne
    @MapsId("iboard")
    @JoinColumn(name = "iboard", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Board board;
}*/
