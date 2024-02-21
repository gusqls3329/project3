package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAdmin is a Querydsl query type for Admin
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAdmin extends EntityPathBase<Admin> {

    private static final long serialVersionUID = 1796944940L;

    public static final QAdmin admin = new QAdmin("admin");

    public final com.team5.projrental.entities.inheritance.QUsers _super = new com.team5.projrental.entities.inheritance.QUsers(this);

    //inherited
    public final EnumPath<com.team5.projrental.entities.enums.Auth> auth = _super.auth;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath email = _super.email;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nick = createString("nick");

    //inherited
    public final StringPath phone = _super.phone;

    public final EnumPath<com.team5.projrental.entities.enums.AdminStatus> status = createEnum("status", com.team5.projrental.entities.enums.AdminStatus.class);

    public final StringPath storedAdminPic = createString("storedAdminPic");

    //inherited
    public final StringPath uid = _super.uid;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath upw = _super.upw;

    public QAdmin(String variable) {
        super(Admin.class, forVariable(variable));
    }

    public QAdmin(Path<? extends Admin> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdmin(PathMetadata metadata) {
        super(Admin.class, metadata);
    }

}

