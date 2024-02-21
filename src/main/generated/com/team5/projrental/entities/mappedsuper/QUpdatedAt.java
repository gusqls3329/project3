package com.team5.projrental.entities.mappedsuper;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUpdatedAt is a Querydsl query type for UpdatedAt
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QUpdatedAt extends EntityPathBase<UpdatedAt> {

    private static final long serialVersionUID = -412293147L;

    public static final QUpdatedAt updatedAt1 = new QUpdatedAt("updatedAt1");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QUpdatedAt(String variable) {
        super(UpdatedAt.class, forVariable(variable));
    }

    public QUpdatedAt(Path<? extends UpdatedAt> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUpdatedAt(PathMetadata metadata) {
        super(UpdatedAt.class, metadata);
    }

}

