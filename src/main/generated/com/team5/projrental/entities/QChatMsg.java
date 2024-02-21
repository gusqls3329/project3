package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatMsg is a Querydsl query type for ChatMsg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatMsg extends EntityPathBase<ChatMsg> {

    private static final long serialVersionUID = -2128991578L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatMsg chatMsg = new QChatMsg("chatMsg");

    public final com.team5.projrental.entities.mappedsuper.QCreatedAt _super = new com.team5.projrental.entities.mappedsuper.QCreatedAt(this);

    public final com.team5.projrental.entities.ids.QChatMsgIds chatMsgIds;

    public final QChatUser chatUser;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath msg = createString("msg");

    public QChatMsg(String variable) {
        this(ChatMsg.class, forVariable(variable), INITS);
    }

    public QChatMsg(Path<? extends ChatMsg> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatMsg(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatMsg(PathMetadata metadata, PathInits inits) {
        this(ChatMsg.class, metadata, inits);
    }

    public QChatMsg(Class<? extends ChatMsg> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chatMsgIds = inits.isInitialized("chatMsgIds") ? new com.team5.projrental.entities.ids.QChatMsgIds(forProperty("chatMsgIds")) : null;
        this.chatUser = inits.isInitialized("chatUser") ? new QChatUser(forProperty("chatUser"), inits.get("chatUser")) : null;
    }

}

