package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResolvedBoard is a Querydsl query type for ResolvedBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResolvedBoard extends EntityPathBase<ResolvedBoard> {

    private static final long serialVersionUID = -562691669L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QResolvedBoard resolvedBoard = new QResolvedBoard("resolvedBoard");

    public final QAdmin admin;

    public final QBoard board;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QResolvedBoard(String variable) {
        this(ResolvedBoard.class, forVariable(variable), INITS);
    }

    public QResolvedBoard(Path<? extends ResolvedBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QResolvedBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QResolvedBoard(PathMetadata metadata, PathInits inits) {
        this(ResolvedBoard.class, metadata, inits);
    }

    public QResolvedBoard(Class<? extends ResolvedBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.admin = inits.isInitialized("admin") ? new QAdmin(forProperty("admin")) : null;
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
    }

}

