package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResolvedUser is a Querydsl query type for ResolvedUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResolvedUser extends EntityPathBase<ResolvedUser> {

    private static final long serialVersionUID = 398060646L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QResolvedUser resolvedUser = new QResolvedUser("resolvedUser");

    public final QAdmin admin;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.team5.projrental.entities.inheritance.QUsers users;

    public QResolvedUser(String variable) {
        this(ResolvedUser.class, forVariable(variable), INITS);
    }

    public QResolvedUser(Path<? extends ResolvedUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QResolvedUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QResolvedUser(PathMetadata metadata, PathInits inits) {
        this(ResolvedUser.class, metadata, inits);
    }

    public QResolvedUser(Class<? extends ResolvedUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.admin = inits.isInitialized("admin") ? new QAdmin(forProperty("admin")) : null;
        this.users = inits.isInitialized("users") ? new com.team5.projrental.entities.inheritance.QUsers(forProperty("users")) : null;
    }

}

