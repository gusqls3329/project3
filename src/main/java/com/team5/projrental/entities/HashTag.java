package com.team5.projrental.entities;

import com.team5.projrental.entities.ids.HashTagIds;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class HashTag {

    @EmbeddedId
    private HashTagIds hashTagIds;


    @MapsId("iproduct")
    @ManyToOne
    @JoinColumn(name = "iproduct")
    private Product product;

}
