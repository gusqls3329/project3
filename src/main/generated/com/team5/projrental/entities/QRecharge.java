package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecharge is a Querydsl query type for Recharge
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecharge extends EntityPathBase<Recharge> {

    private static final long serialVersionUID = 477349034L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecharge recharge = new QRecharge("recharge");

    public final com.team5.projrental.entities.mappedsuper.QCreatedAt _super = new com.team5.projrental.entities.mappedsuper.QCreatedAt(this);

    public final NumberPath<Integer> cash = createNumber("cash", Integer.class);

    public final QComp comp;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.team5.projrental.entities.enums.PaymentMethod> method = createEnum("method", com.team5.projrental.entities.enums.PaymentMethod.class);

    public QRecharge(String variable) {
        this(Recharge.class, forVariable(variable), INITS);
    }

    public QRecharge(Path<? extends Recharge> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecharge(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecharge(PathMetadata metadata, PathInits inits) {
        this(Recharge.class, metadata, inits);
    }

    public QRecharge(Class<? extends Recharge> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.comp = inits.isInitialized("comp") ? new QComp(forProperty("comp"), inits.get("comp")) : null;
    }

}

