package com.team5.projrental.entities.mappedsuper;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseAt is a Querydsl query type for BaseAt
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseAt extends EntityPathBase<BaseAt> {

    private static final long serialVersionUID = -582312787L;

    public static final QBaseAt baseAt = new QBaseAt("baseAt");

    public final QCreatedAt _super = new QCreatedAt(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QBaseAt(String variable) {
        super(BaseAt.class, forVariable(variable));
    }

    public QBaseAt(Path<? extends BaseAt> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseAt(PathMetadata metadata) {
        super(BaseAt.class, metadata);
    }

}

