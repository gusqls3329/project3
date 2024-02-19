package com.team5.projrental.user.verification.comp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CompCodeRequestDto {

    List<Businesses> businesses;

}
