package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPush is a Querydsl query type for Push
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPush extends EntityPathBase<Push> {

    private static final long serialVersionUID = -218665315L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPush push = new QPush("push");

    public final EnumPath<com.team5.projrental.entities.enums.PushCategory> category = createEnum("category", com.team5.projrental.entities.enums.PushCategory.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.team5.projrental.entities.inheritance.QUsers users;

    public QPush(String variable) {
        this(Push.class, forVariable(variable), INITS);
    }

    public QPush(Path<? extends Push> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPush(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPush(PathMetadata metadata, PathInits inits) {
        this(Push.class, metadata, inits);
    }

    public QPush(Class<? extends Push> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.users = inits.isInitialized("users") ? new com.team5.projrental.entities.inheritance.QUsers(forProperty("users")) : null;
    }

}

