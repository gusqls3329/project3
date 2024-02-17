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
    @JoinColumn(name = "ireported_user")
    private Users reportedUsers;

    @ManyToOne
    @JoinColumn(name = "ireporter")
    private Users users;

    @Enumerated(EnumType.STRING)
    private DisputeCategory category;

    private String details;
    private byte penalty;

    @Enumerated(EnumType.STRING)
    private DisputeStatus status;

}
