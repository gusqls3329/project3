package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResolvedProduct is a Querydsl query type for ResolvedProduct
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResolvedProduct extends EntityPathBase<ResolvedProduct> {

    private static final long serialVersionUID = 57754228L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QResolvedProduct resolvedProduct = new QResolvedProduct("resolvedProduct");

    public final QAdmin admin;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProduct product;

    public QResolvedProduct(String variable) {
        this(ResolvedProduct.class, forVariable(variable), INITS);
    }

    public QResolvedProduct(Path<? extends ResolvedProduct> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QResolvedProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QResolvedProduct(PathMetadata metadata, PathInits inits) {
        this(ResolvedProduct.class, metadata, inits);
    }

    public QResolvedProduct(Class<? extends ResolvedProduct> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.admin = inits.isInitialized("admin") ? new QAdmin(forProperty("admin")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

