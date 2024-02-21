package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDisputeChat is a Querydsl query type for DisputeChat
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDisputeChat extends EntityPathBase<DisputeChat> {

    private static final long serialVersionUID = 1497602393L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDisputeChat disputeChat = new QDisputeChat("disputeChat");

    public final QChatUser chatUser;

    public final QDispute dispute;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QDisputeChat(String variable) {
        this(DisputeChat.class, forVariable(variable), INITS);
    }

    public QDisputeChat(Path<? extends DisputeChat> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDisputeChat(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDisputeChat(PathMetadata metadata, PathInits inits) {
        this(DisputeChat.class, metadata, inits);
    }

    public QDisputeChat(Class<? extends DisputeChat> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chatUser = inits.isInitialized("chatUser") ? new QChatUser(forProperty("chatUser"), inits.get("chatUser")) : null;
        this.dispute = inits.isInitialized("dispute") ? new QDispute(forProperty("dispute"), inits.get("dispute")) : null;
    }

}

