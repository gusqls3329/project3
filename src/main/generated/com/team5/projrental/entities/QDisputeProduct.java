package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDisputeProduct is a Querydsl query type for DisputeProduct
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDisputeProduct extends EntityPathBase<DisputeProduct> {

    private static final long serialVersionUID = -2095875250L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDisputeProduct disputeProduct = new QDisputeProduct("disputeProduct");

    public final QDispute dispute;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProduct product;

    public QDisputeProduct(String variable) {
        this(DisputeProduct.class, forVariable(variable), INITS);
    }

    public QDisputeProduct(Path<? extends DisputeProduct> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDisputeProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDisputeProduct(PathMetadata metadata, PathInits inits) {
        this(DisputeProduct.class, metadata, inits);
    }

    public QDisputeProduct(Class<? extends DisputeProduct> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dispute = inits.isInitialized("dispute") ? new QDispute(forProperty("dispute"), inits.get("dispute")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

