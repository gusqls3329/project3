package com.team5.projrental.entities.ids;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProdLikeIds is a Querydsl query type for ProdLikeIds
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QProdLikeIds extends BeanPath<ProdLikeIds> {

    private static final long serialVersionUID = 226816689L;

    public static final QProdLikeIds prodLikeIds = new QProdLikeIds("prodLikeIds");

    public final NumberPath<Long> iproduct = createNumber("iproduct", Long.class);

    public final NumberPath<Long> iusers = createNumber("iusers", Long.class);

    public QProdLikeIds(String variable) {
        super(ProdLikeIds.class, forVariable(variable));
    }

    public QProdLikeIds(Path<? extends ProdLikeIds> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProdLikeIds(PathMetadata metadata) {
        super(ProdLikeIds.class, metadata);
    }

}

