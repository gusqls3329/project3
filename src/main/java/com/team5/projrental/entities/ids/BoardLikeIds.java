package com.team5.projrental.entities.ids;


import com.team5.projrental.entities.inheritance.Users;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Embeddable
@EqualsAndHashCode
public class BoardLikeIds implements Serializable {
    private Long iuser;
    private Long iboard;


}
