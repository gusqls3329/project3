package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProdLike is a Querydsl query type for ProdLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProdLike extends EntityPathBase<ProdLike> {

    private static final long serialVersionUID = 278563217L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProdLike prodLike = new QProdLike("prodLike");

    public final com.team5.projrental.entities.mappedsuper.QCreatedAt _super = new com.team5.projrental.entities.mappedsuper.QCreatedAt(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.team5.projrental.entities.ids.QProdLikeIds prodLikeIds;

    public final QProduct product;

    public final com.team5.projrental.entities.inheritance.QUsers users;

    public QProdLike(String variable) {
        this(ProdLike.class, forVariable(variable), INITS);
    }

    public QProdLike(Path<? extends ProdLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProdLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProdLike(PathMetadata metadata, PathInits inits) {
        this(ProdLike.class, metadata, inits);
    }

    public QProdLike(Class<? extends ProdLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.prodLikeIds = inits.isInitialized("prodLikeIds") ? new com.team5.projrental.entities.ids.QProdLikeIds(forProperty("prodLikeIds")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
        this.users = inits.isInitialized("users") ? new com.team5.projrental.entities.inheritance.QUsers(forProperty("users")) : null;
    }

}

