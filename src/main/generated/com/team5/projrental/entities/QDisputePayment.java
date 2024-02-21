package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDisputePayment is a Querydsl query type for DisputePayment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDisputePayment extends EntityPathBase<DisputePayment> {

    private static final long serialVersionUID = 1721884773L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDisputePayment disputePayment = new QDisputePayment("disputePayment");

    public final QDispute dispute;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPayment payment;

    public QDisputePayment(String variable) {
        this(DisputePayment.class, forVariable(variable), INITS);
    }

    public QDisputePayment(Path<? extends DisputePayment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDisputePayment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDisputePayment(PathMetadata metadata, PathInits inits) {
        this(DisputePayment.class, metadata, inits);
    }

    public QDisputePayment(Class<? extends DisputePayment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dispute = inits.isInitialized("dispute") ? new QDispute(forProperty("dispute"), inits.get("dispute")) : null;
        this.payment = inits.isInitialized("payment") ? new QPayment(forProperty("payment"), inits.get("payment")) : null;
    }

}

