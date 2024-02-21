package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDispute is a Querydsl query type for Dispute
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDispute extends EntityPathBase<Dispute> {

    private static final long serialVersionUID = -1196316063L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDispute dispute = new QDispute("dispute");

    public final QAdmin admin;

    public final EnumPath<com.team5.projrental.entities.enums.DisputeCategory> category = createEnum("category", com.team5.projrental.entities.enums.DisputeCategory.class);

    public final StringPath details = createString("details");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Byte> penalty = createNumber("penalty", Byte.class);

    public final com.team5.projrental.entities.inheritance.QUsers reportedUsers;

    public final EnumPath<com.team5.projrental.entities.enums.DisputeStatus> status = createEnum("status", com.team5.projrental.entities.enums.DisputeStatus.class);

    public final com.team5.projrental.entities.inheritance.QUsers users;

    public QDispute(String variable) {
        this(Dispute.class, forVariable(variable), INITS);
    }

    public QDispute(Path<? extends Dispute> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDispute(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDispute(PathMetadata metadata, PathInits inits) {
        this(Dispute.class, metadata, inits);
    }

    public QDispute(Class<? extends Dispute> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.admin = inits.isInitialized("admin") ? new QAdmin(forProperty("admin")) : null;
        this.reportedUsers = inits.isInitialized("reportedUsers") ? new com.team5.projrental.entities.inheritance.QUsers(forProperty("reportedUsers")) : null;
        this.users = inits.isInitialized("users") ? new com.team5.projrental.entities.inheritance.QUsers(forProperty("users")) : null;
    }

}

