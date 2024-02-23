package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVerificationInfo is a Querydsl query type for VerificationInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVerificationInfo extends EntityPathBase<VerificationInfo> {

    private static final long serialVersionUID = -722289556L;

    public static final QVerificationInfo verificationInfo = new QVerificationInfo("verificationInfo");

    public final com.team5.projrental.entities.mappedsuper.QCreatedAt _super = new com.team5.projrental.entities.mappedsuper.QCreatedAt(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath txId = createString("txId");

    public final StringPath userBirthday = createString("userBirthday");

    public final StringPath userName = createString("userName");

    public final StringPath userPhone = createString("userPhone");

    public QVerificationInfo(String variable) {
        super(VerificationInfo.class, forVariable(variable));
    }

    public QVerificationInfo(Path<? extends VerificationInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVerificationInfo(PathMetadata metadata) {
        super(VerificationInfo.class, metadata);
    }

}

