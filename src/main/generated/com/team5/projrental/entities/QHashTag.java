package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHashTag is a Querydsl query type for HashTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHashTag extends EntityPathBase<HashTag> {

    private static final long serialVersionUID = 2124394825L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHashTag hashTag = new QHashTag("hashTag");

    public final com.team5.projrental.entities.ids.QHashTagIds hashTagIds;

    public final QProduct product;

    public QHashTag(String variable) {
        this(HashTag.class, forVariable(variable), INITS);
    }

    public QHashTag(Path<? extends HashTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHashTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHashTag(PathMetadata metadata, PathInits inits) {
        this(HashTag.class, metadata, inits);
    }

    public QHashTag(Class<? extends HashTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hashTagIds = inits.isInitialized("hashTagIds") ? new com.team5.projrental.entities.ids.QHashTagIds(forProperty("hashTagIds")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

