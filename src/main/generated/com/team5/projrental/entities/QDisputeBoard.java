package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDisputeBoard is a Querydsl query type for DisputeBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDisputeBoard extends EntityPathBase<DisputeBoard> {

    private static final long serialVersionUID = -819681019L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDisputeBoard disputeBoard = new QDisputeBoard("disputeBoard");

    public final QBoard board;

    public final QDispute dispute;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QDisputeBoard(String variable) {
        this(DisputeBoard.class, forVariable(variable), INITS);
    }

    public QDisputeBoard(Path<? extends DisputeBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDisputeBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDisputeBoard(PathMetadata metadata, PathInits inits) {
        this(DisputeBoard.class, metadata, inits);
    }

    public QDisputeBoard(Class<? extends DisputeBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
        this.dispute = inits.isInitialized("dispute") ? new QDispute(forProperty("dispute"), inits.get("dispute")) : null;
    }

}

