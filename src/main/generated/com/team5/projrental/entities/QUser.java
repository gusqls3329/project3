package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -218518706L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final com.team5.projrental.entities.inheritance.QUsers _super = new com.team5.projrental.entities.inheritance.QUsers(this);

    //inherited
    public final EnumPath<com.team5.projrental.entities.enums.Auth> auth = _super.auth;

    public final com.team5.projrental.entities.mappedsuper.QBaseUser baseUser;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath email = _super.email;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nick = createString("nick");

    //inherited
    public final StringPath phone = _super.phone;

    public final EnumPath<com.team5.projrental.entities.enums.ProvideType> provideType = createEnum("provideType", com.team5.projrental.entities.enums.ProvideType.class);

    public final EnumPath<com.team5.projrental.entities.enums.UserStatus> status = createEnum("status", com.team5.projrental.entities.enums.UserStatus.class);

    //inherited
    public final StringPath uid = _super.uid;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath upw = _super.upw;

    public final QVerificationInfo verificationInfo;

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.baseUser = inits.isInitialized("baseUser") ? new com.team5.projrental.entities.mappedsuper.QBaseUser(forProperty("baseUser"), inits.get("baseUser")) : null;
        this.verificationInfo = inits.isInitialized("verificationInfo") ? new QVerificationInfo(forProperty("verificationInfo")) : null;
    }

}

