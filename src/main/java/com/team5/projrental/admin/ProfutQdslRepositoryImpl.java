package com.team5.projrental.admin;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team5.projrental.admin.model.ProfitDto;
import com.team5.projrental.entities.Admin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ProfutQdslRepositoryImpl implements ProfitQdslRepository{
    private final JPAQueryFactory jpaQueryFactory;
//
//    @Override
//    public List<ProfitDto> getProfit(ProfitDto dto, Pageable pageable) {
//       // List<Admin> list = jpaQueryFactory.select().from()
//        return null;
//    }
}
