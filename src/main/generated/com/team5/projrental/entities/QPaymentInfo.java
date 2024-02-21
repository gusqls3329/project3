package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPaymentInfo is a Querydsl query type for PaymentInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPaymentInfo extends EntityPathBase<PaymentInfo> {

    private static final long serialVersionUID = -1680019727L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPaymentInfo paymentInfo = new QPaymentInfo("paymentInfo");

    public final com.team5.projrental.entities.mappedsuper.QUpdatedAt _super = new com.team5.projrental.entities.mappedsuper.QUpdatedAt(this);

    public final StringPath code = createString("code");

    public final QPayment payment;

    public final com.team5.projrental.entities.ids.QPaymentInfoIds paymentInfoIds;

    public final EnumPath<com.team5.projrental.entities.enums.PaymentInfoStatus> status = createEnum("status", com.team5.projrental.entities.enums.PaymentInfoStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.team5.projrental.entities.inheritance.QUsers users;

    public QPaymentInfo(String variable) {
        this(PaymentInfo.class, forVariable(variable), INITS);
    }

    public QPaymentInfo(Path<? extends PaymentInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPaymentInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPaymentInfo(PathMetadata metadata, PathInits inits) {
        this(PaymentInfo.class, metadata, inits);
    }

    public QPaymentInfo(Class<? extends PaymentInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.payment = inits.isInitialized("payment") ? new QPayment(forProperty("payment"), inits.get("payment")) : null;
        this.paymentInfoIds = inits.isInitialized("paymentInfoIds") ? new com.team5.projrental.entities.ids.QPaymentInfoIds(forProperty("paymentInfoIds")) : null;
        this.users = inits.isInitialized("users") ? new com.team5.projrental.entities.inheritance.QUsers(forProperty("users")) : null;
    }

}

