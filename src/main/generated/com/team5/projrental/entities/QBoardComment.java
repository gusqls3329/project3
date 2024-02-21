package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardComment is a Querydsl query type for BoardComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardComment extends EntityPathBase<BoardComment> {

    private static final long serialVersionUID = 1172616476L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardComment boardComment = new QBoardComment("boardComment");

    public final com.team5.projrental.entities.mappedsuper.QBaseAt _super = new com.team5.projrental.entities.mappedsuper.QBaseAt(this);

    public final QBoard board;

    public final StringPath comment = createString("comment");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.team5.projrental.entities.inheritance.QUsers users;

    public QBoardComment(String variable) {
        this(BoardComment.class, forVariable(variable), INITS);
    }

    public QBoardComment(Path<? extends BoardComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardComment(PathMetadata metadata, PathInits inits) {
        this(BoardComment.class, metadata, inits);
    }

    public QBoardComment(Class<? extends BoardComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
        this.users = inits.isInitialized("users") ? new com.team5.projrental.entities.inheritance.QUsers(forProperty("users")) : null;
    }

}

