package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAd is a Querydsl query type for Ad
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAd extends EntityPathBase<Ad> {

    private static final long serialVersionUID = -2141007770L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAd ad = new QAd("ad");

    public final com.team5.projrental.entities.mappedsuper.QBaseAt _super = new com.team5.projrental.entities.mappedsuper.QBaseAt(this);

    public final NumberPath<Integer> balance = createNumber("balance", Integer.class);

    public final NumberPath<Long> beforeView = createNumber("beforeView", Long.class);

    public final DateTimePath<java.time.LocalDateTime> calculatedRef = createDateTime("calculatedRef", java.time.LocalDateTime.class);

    public final NumberPath<Long> cash = createNumber("cash", Long.class);

    public final QComp comp;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> exposureCount = createNumber("exposureCount", Integer.class);

    public final NumberPath<Long> finalView = createNumber("finalView", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProduct product;

    public final NumberPath<Integer> profit = createNumber("profit", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final NumberPath<Integer> viewOfMonth = createNumber("viewOfMonth", Integer.class);

    public QAd(String variable) {
        this(Ad.class, forVariable(variable), INITS);
    }

    public QAd(Path<? extends Ad> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAd(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAd(PathMetadata metadata, PathInits inits) {
        this(Ad.class, metadata, inits);
    }

    public QAd(Class<? extends Ad> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.comp = inits.isInitialized("comp") ? new QComp(forProperty("comp"), inits.get("comp")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

