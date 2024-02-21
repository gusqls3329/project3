package com.team5.projrental.entities.ids;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QChatMsgIds is a Querydsl query type for ChatMsgIds
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QChatMsgIds extends BeanPath<ChatMsgIds> {

    private static final long serialVersionUID = 83920296L;

    public static final QChatMsgIds chatMsgIds = new QChatMsgIds("chatMsgIds");

    public final NumberPath<Long> ichatUser = createNumber("ichatUser", Long.class);

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public QChatMsgIds(String variable) {
        super(ChatMsgIds.class, forVariable(variable));
    }

    public QChatMsgIds(Path<? extends ChatMsgIds> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChatMsgIds(PathMetadata metadata) {
        super(ChatMsgIds.class, metadata);
    }

}

