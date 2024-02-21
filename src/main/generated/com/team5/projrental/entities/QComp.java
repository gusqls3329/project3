package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QComp is a Querydsl query type for Comp
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComp extends EntityPathBase<Comp> {

    private static final long serialVersionUID = -219058542L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComp comp = new QComp("comp");

    public final com.team5.projrental.entities.inheritance.QUsers _super = new com.team5.projrental.entities.inheritance.QUsers(this);

    public final QAdmin admin;

    //inherited
    public final EnumPath<com.team5.projrental.entities.enums.Auth> auth = _super.auth;

    public final com.team5.projrental.entities.mappedsuper.QBaseUser baseUser;

    public final NumberPath<Long> cash = createNumber("cash", Long.class);

    public final StringPath compCeo = createString("compCeo");

    public final NumberPath<Long> compCode = createNumber("compCode", Long.class);

    public final StringPath compNm = createString("compNm");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath email = _super.email;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<com.team5.projrental.entities.enums.JoinStatus> joinStatus = createEnum("joinStatus", com.team5.projrental.entities.enums.JoinStatus.class);

    public final StringPath nick = createString("nick");

    //inherited
    public final StringPath phone = _super.phone;

    public final DatePath<java.time.LocalDate> staredAt = createDate("staredAt", java.time.LocalDate.class);

    public final EnumPath<com.team5.projrental.entities.enums.CompStatus> status = createEnum("status", com.team5.projrental.entities.enums.CompStatus.class);

    //inherited
    public final StringPath uid = _super.uid;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath upw = _super.upw;

    public final QVerificationInfo verificationInfo;

    public QComp(String variable) {
        this(Comp.class, forVariable(variable), INITS);
    }

    public QComp(Path<? extends Comp> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QComp(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QComp(PathMetadata metadata, PathInits inits) {
        this(Comp.class, metadata, inits);
    }

    public QComp(Class<? extends Comp> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.admin = inits.isInitialized("admin") ? new QAdmin(forProperty("admin")) : null;
        this.baseUser = inits.isInitialized("baseUser") ? new com.team5.projrental.entities.mappedsuper.QBaseUser(forProperty("baseUser"), inits.get("baseUser")) : null;
        this.verificationInfo = inits.isInitialized("verificationInfo") ? new QVerificationInfo(forProperty("verificationInfo")) : null;
    }

}

