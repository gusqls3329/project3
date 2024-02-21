package com.team5.projrental.entities.ids;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPaymentInfoIds is a Querydsl query type for PaymentInfoIds
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QPaymentInfoIds extends BeanPath<PaymentInfoIds> {

    private static final long serialVersionUID = -770527619L;

    public static final QPaymentInfoIds paymentInfoIds = new QPaymentInfoIds("paymentInfoIds");

    public final NumberPath<Long> ipayment = createNumber("ipayment", Long.class);

    public final NumberPath<Long> iusers = createNumber("iusers", Long.class);

    public QPaymentInfoIds(String variable) {
        super(PaymentInfoIds.class, forVariable(variable));
    }

    public QPaymentInfoIds(Path<? extends PaymentInfoIds> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPaymentInfoIds(PathMetadata metadata) {
        super(PaymentInfoIds.class, metadata);
    }

}

