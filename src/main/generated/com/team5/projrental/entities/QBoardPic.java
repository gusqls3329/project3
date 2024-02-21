package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardPic is a Querydsl query type for BoardPic
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardPic extends EntityPathBase<BoardPic> {

    private static final long serialVersionUID = -1400676665L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardPic boardPic = new QBoardPic("boardPic");

    public final com.team5.projrental.entities.mappedsuper.QCreatedAt _super = new com.team5.projrental.entities.mappedsuper.QCreatedAt(this);

    public final QBoard board;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath storedPic = createString("storedPic");

    public QBoardPic(String variable) {
        this(BoardPic.class, forVariable(variable), INITS);
    }

    public QBoardPic(Path<? extends BoardPic> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardPic(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardPic(PathMetadata metadata, PathInits inits) {
        this(BoardPic.class, metadata, inits);
    }

    public QBoardPic(Class<? extends BoardPic> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
    }

}

