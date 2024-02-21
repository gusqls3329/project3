package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPayment is a Querydsl query type for Payment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPayment extends EntityPathBase<Payment> {

    private static final long serialVersionUID = 640196515L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPayment payment = new QPayment("payment");

    public final com.team5.projrental.entities.mappedsuper.QCreatedAt _super = new com.team5.projrental.entities.mappedsuper.QCreatedAt(this);

    public final StringPath code = createString("code");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> deposit = createNumber("deposit", Integer.class);

    public final NumberPath<Integer> duration = createNumber("duration", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPaymentRecord paymentRecord;

    public final QProduct product;

    public final com.team5.projrental.entities.embeddable.QRentalDates rentalDates;

    public final QStock stock;

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

    public final com.team5.projrental.entities.inheritance.QUsers users;

    public QPayment(String variable) {
        this(Payment.class, forVariable(variable), INITS);
    }

    public QPayment(Path<? extends Payment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPayment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPayment(PathMetadata metadata, PathInits inits) {
        this(Payment.class, metadata, inits);
    }

    public QPayment(Class<? extends Payment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.paymentRecord = inits.isInitialized("paymentRecord") ? new QPaymentRecord(forProperty("paymentRecord")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
        this.rentalDates = inits.isInitialized("rentalDates") ? new com.team5.projrental.entities.embeddable.QRentalDates(forProperty("rentalDates")) : null;
        this.stock = inits.isInitialized("stock") ? new QStock(forProperty("stock"), inits.get("stock")) : null;
        this.users = inits.isInitialized("users") ? new com.team5.projrental.entities.inheritance.QUsers(forProperty("users")) : null;
    }

}

