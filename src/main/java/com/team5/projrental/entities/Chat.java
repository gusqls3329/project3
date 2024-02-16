package com.team5.projrental.entities;

import com.team5.projrental.entities.mappedsuper.CreatedAt;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@Table(name = "t_chat")
public class Chat extends CreatedAt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long ichat;

    @ManyToOne
    @MapsId("iproduct")
    @JoinColumn(columnDefinition = "BIGINT UNSIGNED")
    private Product product;

    @Column(length = 2000, name = "last_msg")
    private String lastMsg;

    @UpdateTimestamp
    private String lastMsgAt;
}
