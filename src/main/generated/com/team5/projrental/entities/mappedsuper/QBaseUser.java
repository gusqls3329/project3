package com.team5.projrental.entities.mappedsuper;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBaseUser is a Querydsl query type for BaseUser
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBaseUser extends BeanPath<BaseUser> {

    private static final long serialVersionUID = -1256241723L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBaseUser baseUser = new QBaseUser("baseUser");

    public final com.team5.projrental.entities.embeddable.QAddress address;

    public final BooleanPath rating = createBoolean("rating");

    public final StringPath storedPic = createString("storedPic");

    public final StringPath verification = createString("verification");

    public QBaseUser(String variable) {
        this(BaseUser.class, forVariable(variable), INITS);
    }

    public QBaseUser(Path<? extends BaseUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBaseUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBaseUser(PathMetadata metadata, PathInits inits) {
        this(BaseUser.class, metadata, inits);
    }

    public QBaseUser(Class<? extends BaseUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new com.team5.projrental.entities.embeddable.QAddress(forProperty("address")) : null;
    }

}

