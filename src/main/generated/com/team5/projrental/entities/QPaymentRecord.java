package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPaymentRecord is a Querydsl query type for PaymentRecord
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPaymentRecord extends EntityPathBase<PaymentRecord> {

    private static final long serialVersionUID = 658010580L;

    public static final QPaymentRecord paymentRecord = new QPaymentRecord("paymentRecord");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.team5.projrental.entities.enums.PaymentMethod> method = createEnum("method", com.team5.projrental.entities.enums.PaymentMethod.class);

    public final StringPath tid = createString("tid");

    public QPaymentRecord(String variable) {
        super(PaymentRecord.class, forVariable(variable));
    }

    public QPaymentRecord(Path<? extends PaymentRecord> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPaymentRecord(PathMetadata metadata) {
        super(PaymentRecord.class, metadata);
    }

}

