package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProfitPerMonth is a Querydsl query type for ProfitPerMonth
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProfitPerMonth extends EntityPathBase<ProfitPerMonth> {

    private static final long serialVersionUID = -394444406L;

    public static final QProfitPerMonth profitPerMonth = new QProfitPerMonth("profitPerMonth");

    public final com.team5.projrental.entities.mappedsuper.QCreatedAt _super = new com.team5.projrental.entities.mappedsuper.QCreatedAt(this);

    public final DateTimePath<java.time.LocalDateTime> calculatedRef = createDateTime("calculatedRef", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> profit = createNumber("profit", Integer.class);

    public QProfitPerMonth(String variable) {
        super(ProfitPerMonth.class, forVariable(variable));
    }

    public QProfitPerMonth(Path<? extends ProfitPerMonth> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProfitPerMonth(PathMetadata metadata) {
        super(ProfitPerMonth.class, metadata);
    }

}

