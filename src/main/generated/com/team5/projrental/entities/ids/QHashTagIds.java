package com.team5.projrental.entities.ids;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHashTagIds is a Querydsl query type for HashTagIds
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QHashTagIds extends BeanPath<HashTagIds> {

    private static final long serialVersionUID = -1701881819L;

    public static final QHashTagIds hashTagIds = new QHashTagIds("hashTagIds");

    public final NumberPath<Long> iproduct = createNumber("iproduct", Long.class);

    public final StringPath tag = createString("tag");

    public QHashTagIds(String variable) {
        super(HashTagIds.class, forVariable(variable));
    }

    public QHashTagIds(Path<? extends HashTagIds> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHashTagIds(PathMetadata metadata) {
        super(HashTagIds.class, metadata);
    }

}

