package com.team5.projrental.entities.embeddable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRentalDates is a Querydsl query type for RentalDates
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QRentalDates extends BeanPath<RentalDates> {

    private static final long serialVersionUID = -2079318949L;

    public static final QRentalDates rentalDates = new QRentalDates("rentalDates");

    public final DateTimePath<java.time.LocalDateTime> rentalEndDate = createDateTime("rentalEndDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> rentalStartDate = createDateTime("rentalStartDate", java.time.LocalDateTime.class);

    public QRentalDates(String variable) {
        super(RentalDates.class, forVariable(variable));
    }

    public QRentalDates(Path<? extends RentalDates> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRentalDates(PathMetadata metadata) {
        super(RentalDates.class, metadata);
    }

}

