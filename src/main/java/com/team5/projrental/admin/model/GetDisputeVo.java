package com.team5.projrental.admin.model;

import com.team5.projrental.entities.enums.DisputeStatus;
import com.team5.projrental.entities.inheritance.Users;
import com.team5.projrental.product.model.Categories;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GetDisputeVo {
    private long idispute;
    private long ireportedUser;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "ireporter")
    private Users users;
    private String uid;
    private String nm;
    private Categories category;
    private LocalDate createdAt;
    private long penalty;
    private long disputeStatus;
    private int kind;
    private Long pk;
}
