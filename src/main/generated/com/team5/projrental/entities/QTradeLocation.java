package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTradeLocation is a Querydsl query type for TradeLocation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTradeLocation extends EntityPathBase<TradeLocation> {

    private static final long serialVersionUID = 544560406L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTradeLocation tradeLocation = new QTradeLocation("tradeLocation");

    public final com.team5.projrental.entities.embeddable.QAddress address;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.team5.projrental.entities.inheritance.QUsers users;

    public QTradeLocation(String variable) {
        this(TradeLocation.class, forVariable(variable), INITS);
    }

    public QTradeLocation(Path<? extends TradeLocation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTradeLocation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTradeLocation(PathMetadata metadata, PathInits inits) {
        this(TradeLocation.class, metadata, inits);
    }

    public QTradeLocation(Class<? extends TradeLocation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new com.team5.projrental.entities.embeddable.QAddress(forProperty("address")) : null;
        this.users = inits.isInitialized("users") ? new com.team5.projrental.entities.inheritance.QUsers(forProperty("users")) : null;
    }

}

