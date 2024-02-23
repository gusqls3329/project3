package com.team5.projrental.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 1117403788L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final com.team5.projrental.entities.mappedsuper.QBaseAt _super = new com.team5.projrental.entities.mappedsuper.QBaseAt(this);

    public final com.team5.projrental.entities.embeddable.QAddress address;

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.team5.projrental.entities.enums.ProductMainCategory> mainCategory = createEnum("mainCategory", com.team5.projrental.entities.enums.ProductMainCategory.class);

    public final ListPath<ProdLike, QProdLike> prodLikes = this.<ProdLike, QProdLike>createList("prodLikes", ProdLike.class, QProdLike.class, PathInits.DIRECT2);

    public final com.team5.projrental.entities.embeddable.QRentalDates rentalDates;

    public final NumberPath<Integer> rentalPrice = createNumber("rentalPrice", Integer.class);

    public final EnumPath<com.team5.projrental.entities.enums.ProductStatus> status = createEnum("status", com.team5.projrental.entities.enums.ProductStatus.class);

    public final StringPath storedPic = createString("storedPic");

    public final EnumPath<com.team5.projrental.entities.enums.ProductSubCategory> subCategory = createEnum("subCategory", com.team5.projrental.entities.enums.ProductSubCategory.class);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUser user;

    public final NumberPath<Long> view = createNumber("view", Long.class);

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new com.team5.projrental.entities.embeddable.QAddress(forProperty("address")) : null;
        this.rentalDates = inits.isInitialized("rentalDates") ? new com.team5.projrental.entities.embeddable.QRentalDates(forProperty("rentalDates")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

