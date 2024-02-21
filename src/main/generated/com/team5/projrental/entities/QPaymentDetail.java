package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPaymentDetail is a Querydsl query type for PaymentDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPaymentDetail extends EntityPathBase<PaymentDetail> {

    private static final long serialVersionUID = 257695188L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPaymentDetail paymentDetail = new QPaymentDetail("paymentDetail");

    public final com.team5.projrental.entities.mappedsuper.QCreatedAt _super = new com.team5.projrental.entities.mappedsuper.QCreatedAt(this);

    public final EnumPath<com.team5.projrental.entities.enums.PaymentDetailCategory> category = createEnum("category", com.team5.projrental.entities.enums.PaymentDetailCategory.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath id = createString("id");

    public final StringPath tid = createString("tid");

    public final com.team5.projrental.entities.inheritance.QUsers users;

    public QPaymentDetail(String variable) {
        this(PaymentDetail.class, forVariable(variable), INITS);
    }

    public QPaymentDetail(Path<? extends PaymentDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPaymentDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPaymentDetail(PathMetadata metadata, PathInits inits) {
        this(PaymentDetail.class, metadata, inits);
    }

    public QPaymentDetail(Class<? extends PaymentDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.users = inits.isInitialized("users") ? new com.team5.projrental.entities.inheritance.QUsers(forProperty("users")) : null;
    }

}

