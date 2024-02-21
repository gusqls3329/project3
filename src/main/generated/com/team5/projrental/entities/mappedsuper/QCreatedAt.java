package com.team5.projrental.entities.mappedsuper;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCreatedAt is a Querydsl query type for CreatedAt
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QCreatedAt extends EntityPathBase<CreatedAt> {

    private static final long serialVersionUID = 2135273170L;

    public static final QCreatedAt createdAt1 = new QCreatedAt("createdAt1");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public QCreatedAt(String variable) {
        super(CreatedAt.class, forVariable(variable));
    }

    public QCreatedAt(Path<? extends CreatedAt> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCreatedAt(PathMetadata metadata) {
        super(CreatedAt.class, metadata);
    }

}

