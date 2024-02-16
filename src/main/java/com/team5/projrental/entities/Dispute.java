package com.team5.projrental.entities;

import com.team5.projrental.entities.enums.DisputeCategory;
import com.team5.projrental.entities.enums.DisputeStatus;
import com.team5.projrental.entities.inheritance.Users;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Dispute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idispute;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "iadmin")
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "iuser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ireporter")
    private Users users;

    private DisputeCategory category;
    private String details;
    private byte penalty;
    private DisputeStatus status;

}
