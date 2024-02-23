package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRecharge is a Querydsl query type for Recharge
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecharge extends EntityPathBase<Recharge> {

    private static final long serialVersionUID = 477349034L;

    public static final QRecharge recharge = new QRecharge("recharge");

    public final com.team5.projrental.entities.mappedsuper.QCreatedAt _super = new com.team5.projrental.entities.mappedsuper.QCreatedAt(this);

    public final NumberPath<Integer> cash = createNumber("cash", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.team5.projrental.entities.enums.PaymentMethod> method = createEnum("method", com.team5.projrental.entities.enums.PaymentMethod.class);

    public QRecharge(String variable) {
        super(Recharge.class, forVariable(variable));
    }

    public QRecharge(Path<? extends Recharge> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecharge(PathMetadata metadata) {
        super(Recharge.class, metadata);
    }

}

