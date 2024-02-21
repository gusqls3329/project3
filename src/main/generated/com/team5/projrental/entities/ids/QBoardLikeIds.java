package com.team5.projrental.entities.ids;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardLikeIds is a Querydsl query type for BoardLikeIds
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBoardLikeIds extends BeanPath<BoardLikeIds> {

    private static final long serialVersionUID = -194195372L;

    public static final QBoardLikeIds boardLikeIds = new QBoardLikeIds("boardLikeIds");

    public final NumberPath<Long> iboard = createNumber("iboard", Long.class);

    public final NumberPath<Long> iuser = createNumber("iuser", Long.class);

    public QBoardLikeIds(String variable) {
        super(BoardLikeIds.class, forVariable(variable));
    }

    public QBoardLikeIds(Path<? extends BoardLikeIds> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardLikeIds(PathMetadata metadata) {
        super(BoardLikeIds.class, metadata);
    }

}

